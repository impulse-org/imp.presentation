package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 37:  style_set ::= {$ style_list }$
 *</b>
 */
public class style_set extends ASTNode implements Istyle_set
{
    private style_keyList _style_list;

    public style_keyList getstyle_list() { return _style_list; }

    public style_set(IToken leftIToken, IToken rightIToken,
                     style_keyList _style_list)
    {
        super(leftIToken, rightIToken);

        this._style_list = _style_list;
        ((ASTNode) _style_list).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_style_list);
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
        if (! (o instanceof style_set)) return false;
        style_set other = (style_set) o;
        if (! _style_list.equals(other._style_list)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_style_list.hashCode());
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
            _style_list.accept(v);
        v.endVisit(this);
    }
}


