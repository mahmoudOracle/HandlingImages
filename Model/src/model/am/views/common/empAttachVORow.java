package model.am.views.common;

import oracle.jbo.Row;
import oracle.jbo.domain.BlobDomain;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jul 17 12:44:10 AST 2019
// ---------------------------------------------------------------------
public interface empAttachVORow extends Row {
    BlobDomain getAttachedFile();

    oracle.jbo.domain.Number getAttachmentType();

    oracle.jbo.domain.Number getEmpAttachmentId();

    void setAttachedFile(BlobDomain value);

    void setAttachmentType(oracle.jbo.domain.Number value);

    void setEmpAttachmentId(oracle.jbo.domain.Number value);
}
