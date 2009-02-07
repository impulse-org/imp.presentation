package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 106:  unary_op ::= expression postfix_unary_op
 *</b>
 */
public class unary_op1 extends ASTNode implements Iunary_op
{
    private Iexpression _expression;
    private Ipostfix_unary_op _postfix_unary_op;

    public Iexpression getexpression() { return _expression; }
    public Ipostfix_unary_op getpostfix_unary_op() { return _postfix_unary_op; }

    public unary_op1(IToken leftIToken, IToken rightIToken,
                     Iexpression _expression,
                     Ipostfix_unary_op _postfix_unary_op)
    {
        super(leftIToken, rightIToken);

        this._expression = _expression;
        ((ASTNode) _expression).setParent(this);
        this._postfix_unary_op = _postfix_unary_op;
        ((ASTNode) _postfix_unary_op).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_expression);
        list.add(_postfix_unary_op);
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
        if (! (o instanceof unary_op1)) return false;
        unary_op1 other = (unary_op1) o;
        if (! _expression.equals(other._expression)) return false;
        if (! _postfix_unary_op.equals(other._postfix_unary_op)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_expression.hashCode());
        hash = hash * 31 + (_postfix_unary_op.hashCode());
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
            _expression.accept(v);
            _postfix_unary_op.accept(v);
        }
        v.endVisit(this);
    }
}


