package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 70:  iconPresentation ::= ICON$ =$ expression ;$
 *</b>
 */
public class iconPresentation extends ASTNode implements IiconPresentation
{
    private Iexpression _expression;

    public Iexpression getexpression() { return _expression; }

    public iconPresentation(IToken leftIToken, IToken rightIToken,
                            Iexpression _expression)
    {
        super(leftIToken, rightIToken);

        this._expression = _expression;
        ((ASTNode) _expression).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_expression);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof iconPresentation)) return false;
        if (! super.equals(o)) return false;
        iconPresentation other = (iconPresentation) o;
        if (! _expression.equals(other._expression)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_expression.hashCode());
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
            _expression.accept(v);
        v.endVisit(this);
    }
}


