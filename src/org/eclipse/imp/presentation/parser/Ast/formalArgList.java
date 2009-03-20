package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<em>
 *<li>Rule 29:  formalArgList ::= formalArg
 *</em>
 *<p>
 *<b>
 *<li>Rule 30:  formalArgList ::= formalArgList ,$ formalArg
 *</b>
 */
public class formalArgList extends ASTNode implements IformalArgList
{
    private IformalArgList _formalArgList;
    private formalArg _formalArg;

    public IformalArgList getformalArgList() { return _formalArgList; }
    public formalArg getformalArg() { return _formalArg; }

    public formalArgList(IToken leftIToken, IToken rightIToken,
                         IformalArgList _formalArgList,
                         formalArg _formalArg)
    {
        super(leftIToken, rightIToken);

        this._formalArgList = _formalArgList;
        ((ASTNode) _formalArgList).setParent(this);
        this._formalArg = _formalArg;
        ((ASTNode) _formalArg).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_formalArgList);
        list.add(_formalArg);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof formalArgList)) return false;
        if (! super.equals(o)) return false;
        formalArgList other = (formalArgList) o;
        if (! _formalArgList.equals(other._formalArgList)) return false;
        if (! _formalArg.equals(other._formalArg)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_formalArgList.hashCode());
        hash = hash * 31 + (_formalArg.hashCode());
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
            _formalArgList.accept(v);
            _formalArg.accept(v);
        }
        v.endVisit(this);
    }
}


