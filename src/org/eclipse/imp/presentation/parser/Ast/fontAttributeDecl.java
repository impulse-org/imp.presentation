package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 56:  fontAttributeDecl ::= FONT$ =$ resourceValue ;$
 *</b>
 */
public class fontAttributeDecl extends ASTNode implements IfontAttributeDecl
{
    private IresourceValue _resourceValue;

    public IresourceValue getresourceValue() { return _resourceValue; }

    public fontAttributeDecl(IToken leftIToken, IToken rightIToken,
                             IresourceValue _resourceValue)
    {
        super(leftIToken, rightIToken);

        this._resourceValue = _resourceValue;
        ((ASTNode) _resourceValue).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_resourceValue);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof fontAttributeDecl)) return false;
        if (! super.equals(o)) return false;
        fontAttributeDecl other = (fontAttributeDecl) o;
        if (! _resourceValue.equals(other._resourceValue)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_resourceValue.hashCode());
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
            _resourceValue.accept(v);
        v.endVisit(this);
    }
}


