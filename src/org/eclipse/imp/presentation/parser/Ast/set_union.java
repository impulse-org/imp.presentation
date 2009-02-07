package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 126:  set_union ::= set_expression$e1 +$ set_expression$e2
 *</b>
 */
public class set_union extends ASTNode implements Iset_union
{
    private Iset_expression _e1;
    private Iset_expression _e2;

    public Iset_expression gete1() { return _e1; }
    public Iset_expression gete2() { return _e2; }

    public set_union(IToken leftIToken, IToken rightIToken,
                     Iset_expression _e1,
                     Iset_expression _e2)
    {
        super(leftIToken, rightIToken);

        this._e1 = _e1;
        ((ASTNode) _e1).setParent(this);
        this._e2 = _e2;
        ((ASTNode) _e2).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_e1);
        list.add(_e2);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        //
        // The super call test is not required for now because an Ast node
        // can only extend the root Ast, AstToken and AstList and none of
        // these nodes contain additional children.
        //
        // if (! super.equals(o)) return false;
        //
        if (! (o instanceof set_union)) return false;
        set_union other = (set_union) o;
        if (! _e1.equals(other._e1)) return false;
        if (! _e2.equals(other._e2)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_e1.hashCode());
        hash = hash * 31 + (_e2.hashCode());
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
            _e1.accept(v);
            _e2.accept(v);
        }
        v.endVisit(this);
    }
}


