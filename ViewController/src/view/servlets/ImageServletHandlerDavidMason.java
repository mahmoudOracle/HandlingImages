package view.servlets;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import javax.sql.DataSource;

import model.am.views.empAttach2Impl;
import model.am.views.empAttachVOImpl;

import oracle.jbo.ApplicationModule;
import oracle.jbo.ViewObject;
import oracle.jbo.client.Configuration;
import oracle.jbo.domain.BlobDomain;

import org.apache.commons.io.IOUtils;

@WebServlet(name = "ImageServletHandlerDavidMason", urlPatterns = { "/imageservlethandlerdavidmason" })
public class ImageServletHandlerDavidMason extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    } //init

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);

        //get the id parameter passed in the request
        String imageId = request.getParameter("id");
        OutputStream os = response.getOutputStream();
        System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRR id Value is" + imageId);
        //if id starts with temp, get the image from temporary directory
        if (imageId.startsWith("temp")) {

            // get the applications temporary directory
            File tempDir =
                new File(getServletContext().getInitParameter("org.apache.myfaces.trinidad.UPLOAD_TEMP_DIR"));
            //create the temporary file (remove "temp" from id)
            File tempFile = new File(tempDir, imageId.substring(4));
            FileInputStream fis = new FileInputStream(tempFile);
            //create blob from the image file
            BlobDomain blob = createBlobDomain(fis);

            BufferedInputStream in = new BufferedInputStream(blob.getBinaryStream());
            int b;
            byte[] buffer = new byte[10240];
            while ((b = in.read(buffer, 0, 10240)) != -1) {
                os.write(buffer, 0, b);
            }
            os.close();
        } else {
            //get image from VO
            getImageFromVO(request, os);

            //OR get image from DB
            //getImageFromDB(request, os);

        }
    }


    private void getImageFromVO(HttpServletRequest request, OutputStream os) {
        System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
        String appModuleName = "model.am.views.AppModuleAM";
        String appModuleConfig = "AppModuleAMLocal";
        ApplicationModule am = null;
        ViewObject vo = null;
        try {
            am = Configuration.createRootApplicationModule(appModuleName, appModuleConfig);
            vo = am.findViewObject("empAttach2");
            if (vo == null) {
                throw new Exception("empAttach2 not found!");
            }
            empAttach2Impl imageView = (empAttach2Impl) vo;

            // get parameter from request
            Map paramMap = request.getParameterMap();
            oracle.jbo.domain.Number id = null;
            if (paramMap.containsKey("id")) {
                String[] pVal = (String[]) paramMap.get("id");
                id = new oracle.jbo.domain.Number(pVal[0]);
            }
            System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ get Row Count of the VO" +
                               imageView.getRowCount());

            BlobDomain image = null;

            //query for the id param
            imageView.applyViewCriteria(imageView.getViewCriteria("getAttachmentById"));
            imageView.setNamedWhereClauseParam("emAttachIdParam", id);
            imageView.executeQuery();
            System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQBefore Checking if the VC Retuns value or not");
            // Check if a row has been found
            if (imageView.getRowCount() > 0) {
                // We assume the Blob to be the first field
                image = (BlobDomain) (imageView.first().getAttribute("AttachedFile"));
            } else {
                //No row found to get image from.
            }

            OutputStream outputStream = os;
            IOUtils.copy(image.getInputStream(), outputStream);
            // close the blob to release the resources
            image.closeInputStream();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (am != null) {
                //Release the appModule
                Configuration.releaseRootApplicationModule(am, true);
            }
        }
    }

    private void getImageFromDB(HttpServletRequest request, OutputStream os) {


        Connection conn = null;
        String imageId = request.getParameter("id");
        try {
            //make connection to DB
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ApplicationDBDS");
            conn = ds.getConnection();

            PreparedStatement statement =
                conn.prepareStatement("SELECT image_id, image_blob " + "FROM images_tbl " + "WHERE image_id = ?");
            //apply servlets id parameter to query.
            statement.setLong(1, new Long(imageId));
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("IMAGE_BLOB");
                BufferedInputStream in = new BufferedInputStream(blob.getBinaryStream());
                int idx;
                byte[] buffer = new byte[10240];
                while ((idx = in.read(buffer, 0, 10240)) != -1) {
                    os.write(buffer, 0, idx);
                }
                os.close();

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException sqle) {
                System.out.println("SQLException error");
            }
        }
    }

    private BlobDomain createBlobDomain(FileInputStream file) {

        InputStream in = null;
        BlobDomain blobDomain = null;
        OutputStream out = null;

        try {
            in = file;
            blobDomain = new BlobDomain();

            out = blobDomain.getBinaryOutputStream();

            byte[] buffer = new byte[8192];
            int byteRead = 0;

            while ((byteRead = in.read(buffer, 0, 8192)) != -1) {
                out.write(buffer, 0, byteRead);
            }

            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.fillInStackTrace();
        }
        return blobDomain;
    }

    public static Connection getOracleConnection() throws Exception {
        //setup your database connection
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "mason";
        String password = "sysadmin";

        Class.forName(driver); // load Oracle driver
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
} //ImageServlet
