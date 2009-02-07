package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<em>
 *<li>Rule 25:  optInitializer ::= $Empty
 *</em>
 *<p>
 *<b>
 *<li>Rule 24:  optInitializer ::= =$ expression
 *</b>
 */
public class optInitializer extends ASTNode implements IoptInitializer
{
    private Iexpression _expression;

    public Iexpression getexpression() { return _expression; }

    public optInitializer(IToken leftIToken, IToken rightIToken,
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
        //
        // The super call test is not required for now because an Ast node
        // can only extend the root Ast, AstToken and AstList and none of
        // these nodes contain additional children.
        //
        // if (! super.equals(o)) return false;
        //
        if (! (o instanceof optInitializer)) return false;
        optInitializer other = (optInitializer) o;
        if (! _expression.equals(other._expression)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
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


