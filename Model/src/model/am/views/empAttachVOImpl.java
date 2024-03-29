package model.am.views;

import model.am.views.common.empAttachVO;

import model.shared.extensions.ExtViewObjectImpl;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jul 17 12:41:16 AST 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class empAttachVOImpl extends ExtViewObjectImpl implements empAttachVO {
    /**
     * This is the default constructor (do not remove).
     */
    public empAttachVOImpl() {
    }

    /**
     * Returns the bind variable value for bindEmpAttachmentId.
     * @return bind variable value for bindEmpAttachmentId
     */
    public Number getbindEmpAttachmentId() {
        return (Number) getNamedWhereClauseParam("bindEmpAttachmentId");
    }

    /**
     * Sets <code>value</code> for bind variable bindEmpAttachmentId.
     * @param value value to bind as bindEmpAttachmentId
     */
    public void setbindEmpAttachmentId(Number value) {
        setNamedWhereClauseParam("bindEmpAttachmentId", value);
    }

    public Row getImageById(Number aId) {
        setbindEmpAttachmentId(aId);
        executeQuery();
        Row row = first();
        return row;
    }
}

