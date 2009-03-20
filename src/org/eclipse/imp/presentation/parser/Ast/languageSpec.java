package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 12:  languageSpec ::= LANGUAGE$ IDENTIFIER$langName super_opt$sup languageBody
 *</b>
 */
public class languageSpec extends ASTNode implements IlanguageSpec
{
    private ASTNodeToken _langName;
    private super_opt _sup;
    private languageBody _languageBody;

    public ASTNodeToken getlangName() { return _langName; }
    /**
     * The value returned by <b>getsup</b> may be <b>null</b>
     */
    public super_opt getsup() { return _sup; }
    public languageBody getlanguageBody() { return _languageBody; }

    public languageSpec(IToken leftIToken, IToken rightIToken,
                        ASTNodeToken _langName,
                        super_opt _sup,
                        languageBody _languageBody)
    {
        super(leftIToken, rightIToken);

        this._langName = _langName;
        ((ASTNode) _langName).setParent(this);
        this._sup = _sup;
        if (_sup != null) ((ASTNode) _sup).setParent(this);
        this._languageBody = _languageBody;
        ((ASTNode) _languageBody).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_langName);
        list.add(_sup);
        list.add(_languageBody);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof languageSpec)) return false;
        if (! super.equals(o)) return false;
        languageSpec other = (languageSpec) o;
        if (! _langName.equals(other._langName)) return false;
        if (_sup == null)
            if (other._sup != null) return false;
            else; // continue
        else if (! _sup.equals(other._sup)) return false;
        if (! _languageBody.equals(other._languageBody)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_langName.hashCode());
        hash = hash * 31 + (_sup == null ? 0 : _sup.hashCode());
        hash = hash * 31 + (_languageBody.hashCode());
        return hash;
    }

    public void accept(IAstVisitor v)
    {
        if (! v.preVisit(this)) return;
        enter((Visitor) v);
        v.postVisit(this);
    }

    public void enter(Visitor v)
    {
        boolean checkChildren = v.visit(this);
        if (checkChildren)
        {
            _langName.accept(v);
            if (_sup != null) _sup.accept(v);
            _languageBody.accept(v);
        }
        v.endVisit(this);
    }
}


