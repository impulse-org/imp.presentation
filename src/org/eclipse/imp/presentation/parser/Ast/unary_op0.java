package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 105:  unary_op ::= prefix_unary_op expression
 *</b>
 */
public class unary_op0 extends ASTNode implements Iunary_op
{
    private Iprefix_unary_op _prefix_unary_op;
    private Iexpression _expression;

    public Iprefix_unary_op getprefix_unary_op() { return _prefix_unary_op; }
    public Iexpression getexpression() { return _expression; }

    public unary_op0(IToken leftIToken, IToken rightIToken,
                     Iprefix_unary_op _prefix_unary_op,
                     Iexpression _expression)
    {
        super(leftIToken, rightIToken);

        this._prefix_unary_op = _prefix_unary_op;
        ((ASTNode) _prefix_unary_op).setParent(this);
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
        list.add(_prefix_unary_op);
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
        if (! (o instanceof unary_op0)) return false;
        unary_op0 other = (unary_op0) o;
        if (! _prefix_unary_op.equals(other._prefix_unary_op)) return false;
        if (! _expression.equals(other._expression)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_prefix_unary_op.hashCode());
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
        {
            _prefix_unary_op.accept(v);
            _expression.accept(v);
        }
        v.endVisit(this);
    }
}


