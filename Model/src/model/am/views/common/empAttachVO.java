package model.am.views.common;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jul 17 12:42:57 AST 2019
// ---------------------------------------------------------------------
public interface empAttachVO extends ViewObject {
    Row getImageById(oracle.jbo.domain.Number aId);

    oracle.jbo.domain.Number getbindEmpAttachmentId();

    void setbindEmpAttachmentId(oracle.jbo.domain.Number value);
}
