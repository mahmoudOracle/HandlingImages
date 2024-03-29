package view.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.util.Map;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.am.views.AppModuleAMImpl;
import model.am.views.common.empAttachVO;

import model.am.views.common.empAttachVORow;

import model.am.views.empAttachVOImpl;

import model.am.views.empAttachVORowImpl;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.ApplicationModule;
import oracle.jbo.ViewObject;
import oracle.jbo.client.Configuration;
import oracle.jbo.domain.BlobDomain;

import org.apache.commons.io.IOUtils;

@WebServlet(name = "ImageServletTimoAM", urlPatterns = { "/imageservlettimoam" })
public class ImageServletTimoAM extends HttpServlet {
    protected transient ADFLogger mLogger = ADFLogger.createADFLogger(ImageServletTimoAM.class);

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mLogger.info("Image servlet via RootApplicationModule init");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this) {


            StringBuilder sb = new StringBuilder(100);
            String appModuleName = "model.am.views.AppModuleAM";
            String appModuleConfig = "AppModuleAMLocal";
            ApplicationModule am = null;
            ViewObject vo = null;
            try {
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX              XXXXXXXXXXXXXXXXXXXXXXXXX   TIMO FROM APP MODULE");

                am = Configuration.createRootApplicationModule(appModuleName, appModuleConfig);
                sb.append("ImageServletTimoAM ").append(appModuleName);
                vo = am.findViewObject("empAttach1");
                if (vo == null) {
                    throw new Exception("EmployeeAttachments1 not found!");
                }
                empAttachVOImpl imageView = (empAttachVOImpl) vo;

                // get parameter from request
                Map paramMap = request.getParameterMap();
                oracle.jbo.domain.Number id = null;
                if (paramMap.containsKey("id")) {
                    String[] pVal = (String[]) paramMap.get("id");
                    id = new oracle.jbo.domain.Number(pVal[0]);
                    sb.append(" id=").append(pVal[0]);
                }

                // Get the result (only the first row is taken into account
                empAttachVORowImpl imageRow = (empAttachVORowImpl) imageView.getImageById(id);
                BlobDomain image = null;
                String mimeType = null;
                // Check if a row has been found
                if (imageRow != null) {
                    // We assume the Blob to be the first a field
                    image = imageRow.getAttachedFile();
                    mimeType = "image/jpg";
                } else {
                    mLogger.warning("No row found to get image from !!! (id = " + id + ")");
                    return;
                }
                sb.append(" ")
                  .append(mimeType)
                  .append(" ...");
                mLogger.info(sb.toString());

                //Set the content-type. Only images are taken into account
                response.setContentType(mimeType + "; charset=utf8");
                OutputStream outputStream = response.getOutputStream();
                IOUtils.copy(image.getInputStream(), outputStream);
                // cloase the blob to release the recources
                image.closeInputStream();
                // flush the outout stream
                outputStream.flush();
            } catch (Exception e) {
                mLogger.warning("Fehler bei der Ausführung: " + e.getMessage());
            } finally {

                if (am != null) {
                    //Release the appModule
                    Configuration.releaseRootApplicationModule(am, true);
                }
            }

            mLogger.info("...done!");
        }
    }
}
