package model.am.views;

import model.shared.extensions.ExtViewObjectImpl;

import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jul 17 14:19:22 AST 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class empAttach2Impl extends ExtViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public empAttach2Impl() {
    }

    /**
     * Returns the variable value for emAttachIdParam.
     * @return variable value for emAttachIdParam
     */
    public Number getemAttachIdParam() {
        return (Number) ensureVariableManager().getVariableValue("emAttachIdParam");
    }

    /**
     * Sets <code>value</code> for variable emAttachIdParam.
     * @param value value to bind as emAttachIdParam
     */
    public void setemAttachIdParam(Number value) {
        ensureVariableManager().setVariableValue("emAttachIdParam", value);
    }
}

