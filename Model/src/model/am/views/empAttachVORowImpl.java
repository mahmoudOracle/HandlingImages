package model.am.views;

import model.am.views.common.empAttachVORow;

import model.shared.extensions.ExtEntityImpl;
import model.shared.extensions.ExtViewRowImpl;

import oracle.jbo.domain.BlobDomain;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jul 17 12:41:16 AST 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class empAttachVORowImpl extends ExtViewRowImpl implements empAttachVORow {

    public static final int ENTITY_EMPLOYEEATTACHMENTSEO = 0;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        EmpAttachmentId,
        EmployeeId,
        AttachedFile,
        AttachmentType,
        CreatedBy,
        CreatedOn,
        ModifiedBy,
        ModifiedOn,
        Version;
        private static AttributesEnum[] vals = null;
        ;
        private static final int firstIndex = 0;

        protected int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        protected static final int firstIndex() {
            return firstIndex;
        }

        protected static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        protected static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }

    public static final int EMPATTACHMENTID = AttributesEnum.EmpAttachmentId.index();
    public static final int EMPLOYEEID = AttributesEnum.EmployeeId.index();
    public static final int ATTACHEDFILE = AttributesEnum.AttachedFile.index();
    public static final int ATTACHMENTTYPE = AttributesEnum.AttachmentType.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int CREATEDON = AttributesEnum.CreatedOn.index();
    public static final int MODIFIEDBY = AttributesEnum.ModifiedBy.index();
    public static final int MODIFIEDON = AttributesEnum.ModifiedOn.index();
    public static final int VERSION = AttributesEnum.Version.index();

    /**
     * This is the default constructor (do not remove).
     */
    public empAttachVORowImpl() {
    }

    /**
     * Gets EmployeeAttachmentsEO entity object.
     * @return the EmployeeAttachmentsEO
     */
    public ExtEntityImpl getEmployeeAttachmentsEO() {
        return (ExtEntityImpl) getEntity(ENTITY_EMPLOYEEATTACHMENTSEO);
    }

    /**
     * Gets the attribute value for EMP_ATTACHMENT_ID using the alias name EmpAttachmentId.
     * @return the EMP_ATTACHMENT_ID
     */
    public Number getEmpAttachmentId() {
        return (Number) getAttributeInternal(EMPATTACHMENTID);
    }

    /**
     * Sets <code>value</code> as attribute value for EMP_ATTACHMENT_ID using the alias name EmpAttachmentId.
     * @param value value to set the EMP_ATTACHMENT_ID
     */
    public void setEmpAttachmentId(Number value) {
        setAttributeInternal(EMPATTACHMENTID, value);
    }

    /**
     * Gets the attribute value for EMPLOYEE_ID using the alias name EmployeeId.
     * @return the EMPLOYEE_ID
     */
    public Number getEmployeeId() {
        return (Number) getAttributeInternal(EMPLOYEEID);
    }

    /**
     * Sets <code>value</code> as attribute value for EMPLOYEE_ID using the alias name EmployeeId.
     * @param value value to set the EMPLOYEE_ID
     */
    public void setEmployeeId(Number value) {
        setAttributeInternal(EMPLOYEEID, value);
    }

    /**
     * Gets the attribute value for ATTACHED_FILE using the alias name AttachedFile.
     * @return the ATTACHED_FILE
     */
    public BlobDomain getAttachedFile() {
        return (BlobDomain) getAttributeInternal(ATTACHEDFILE);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTACHED_FILE using the alias name AttachedFile.
     * @param value value to set the ATTACHED_FILE
     */
    public void setAttachedFile(BlobDomain value) {
        setAttributeInternal(ATTACHEDFILE, value);
    }

    /**
     * Gets the attribute value for ATTACHMENT_TYPE using the alias name AttachmentType.
     * @return the ATTACHMENT_TYPE
     */
    public Number getAttachmentType() {
        return (Number) getAttributeInternal(ATTACHMENTTYPE);
    }

    /**
     * Sets <code>value</code> as attribute value for ATTACHMENT_TYPE using the alias name AttachmentType.
     * @param value value to set the ATTACHMENT_TYPE
     */
    public void setAttachmentType(Number value) {
        setAttributeInternal(ATTACHMENTTYPE, value);
    }

    /**
     * Gets the attribute value for CREATED_BY using the alias name CreatedBy.
     * @return the CREATED_BY
     */
    public String getCreatedBy() {
        return (String) getAttributeInternal(CREATEDBY);
    }

    /**
     * Sets <code>value</code> as attribute value for CREATED_BY using the alias name CreatedBy.
     * @param value value to set the CREATED_BY
     */
    public void setCreatedBy(String value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**
     * Gets the attribute value for CREATED_ON using the alias name CreatedOn.
     * @return the CREATED_ON
     */
    public Date getCreatedOn() {
        return (Date) getAttributeInternal(CREATEDON);
    }

    /**
     * Sets <code>value</code> as attribute value for CREATED_ON using the alias name CreatedOn.
     * @param value value to set the CREATED_ON
     */
    public void setCreatedOn(Date value) {
        setAttributeInternal(CREATEDON, value);
    }

    /**
     * Gets the attribute value for MODIFIED_BY using the alias name ModifiedBy.
     * @return the MODIFIED_BY
     */
    public String getModifiedBy() {
        return (String) getAttributeInternal(MODIFIEDBY);
    }

    /**
     * Sets <code>value</code> as attribute value for MODIFIED_BY using the alias name ModifiedBy.
     * @param value value to set the MODIFIED_BY
     */
    public void setModifiedBy(String value) {
        setAttributeInternal(MODIFIEDBY, value);
    }

    /**
     * Gets the attribute value for MODIFIED_ON using the alias name ModifiedOn.
     * @return the MODIFIED_ON
     */
    public Date getModifiedOn() {
        return (Date) getAttributeInternal(MODIFIEDON);
    }

    /**
     * Sets <code>value</code> as attribute value for MODIFIED_ON using the alias name ModifiedOn.
     * @param value value to set the MODIFIED_ON
     */
    public void setModifiedOn(Date value) {
        setAttributeInternal(MODIFIEDON, value);
    }

    /**
     * Gets the attribute value for VERSION using the alias name Version.
     * @return the VERSION
     */
    public Number getVersion() {
        return (Number) getAttributeInternal(VERSION);
    }

    /**
     * Sets <code>value</code> as attribute value for VERSION using the alias name Version.
     * @param value value to set the VERSION
     */
    public void setVersion(Number value) {
        setAttributeInternal(VERSION, value);
    }
}
