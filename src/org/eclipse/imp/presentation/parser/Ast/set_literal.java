package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 122:  set_literal ::= {:$ set_element_list$list :}$
 *</b>
 */
public class set_literal extends ASTNode implements Iset_literal
{
    private Iset_element_list _list;

    public Iset_element_list getlist() { return _list; }

    public set_literal(IToken leftIToken, IToken rightIToken,
                       Iset_element_list _list)
    {
        super(leftIToken, rightIToken);

        this._list = _list;
        ((ASTNode) _list).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_list);
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
        if (! (o instanceof set_literal)) return false;
        set_literal other = (set_literal) o;
        if (! _list.equals(other._list)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_list.hashCode());
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
            _list.accept(v);
        v.endVisit(this);
    }
}


