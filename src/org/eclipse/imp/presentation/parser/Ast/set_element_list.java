package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<em>
 *<li>Rule 123:  set_element_list ::= set_element$elt
 *</em>
 *<p>
 *<b>
 *<li>Rule 124:  set_element_list ::= set_element_list$list ,$ set_element$elt
 *</b>
 */
public class set_element_list extends ASTNode implements Iset_element_list
{
    private Iset_element_list _list;
    private set_element _elt;

    public Iset_element_list getlist() { return _list; }
    public set_element getelt() { return _elt; }

    public set_element_list(IToken leftIToken, IToken rightIToken,
                            Iset_element_list _list,
                            set_element _elt)
    {
        super(leftIToken, rightIToken);

        this._list = _list;
        ((ASTNode) _list).setParent(this);
        this._elt = _elt;
        ((ASTNode) _elt).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_list);
        list.add(_elt);
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
        if (! (o instanceof set_element_list)) return false;
        set_element_list other = (set_element_list) o;
        if (! _list.equals(other._list)) return false;
        if (! _elt.equals(other._elt)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_list.hashCode());
        hash = hash * 31 + (_elt.hashCode());
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
            _list.accept(v);
            _elt.accept(v);
        }
        v.endVisit(this);
    }
}


