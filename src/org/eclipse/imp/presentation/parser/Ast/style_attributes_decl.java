package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 36:  style_attributes_decl ::= STYLE$ =$ style_set ;$
 *</b>
 */
public class style_attributes_decl extends ASTNode implements Istyle_attributes_decl
{
    private style_set _style_set;

    public style_set getstyle_set() { return _style_set; }

    public style_attributes_decl(IToken leftIToken, IToken rightIToken,
                                 style_set _style_set)
    {
        super(leftIToken, rightIToken);

        this._style_set = _style_set;
        ((ASTNode) _style_set).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_style_set);
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
        if (! (o instanceof style_attributes_decl)) return false;
        style_attributes_decl other = (style_attributes_decl) o;
        if (! _style_set.equals(other._style_set)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_style_set.hashCode());
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
            _style_set.accept(v);
        v.endVisit(this);
    }
}


