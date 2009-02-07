package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 35:  color_attribute_decl ::= COLOR$ =$ resource_value ;$
 *</b>
 */
public class color_attribute_decl extends ASTNode implements Icolor_attribute_decl
{
    private Iresource_value _resource_value;

    public Iresource_value getresource_value() { return _resource_value; }

    public color_attribute_decl(IToken leftIToken, IToken rightIToken,
                                Iresource_value _resource_value)
    {
        super(leftIToken, rightIToken);

        this._resource_value = _resource_value;
        ((ASTNode) _resource_value).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_resource_value);
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
        if (! (o instanceof color_attribute_decl)) return false;
        color_attribute_decl other = (color_attribute_decl) o;
        if (! _resource_value.equals(other._resource_value)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_resource_value.hashCode());
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
            _resource_value.accept(v);
        v.endVisit(this);
    }
}


