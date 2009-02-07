package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 90:  binary_op ::= expression$left binary_operator$op expression$right
 *</b>
 */
public class binary_op extends ASTNode implements Ibinary_op
{
    private Iexpression _left;
    private Ibinary_operator _op;
    private Iexpression _right;

    public Iexpression getleft() { return _left; }
    public Ibinary_operator getop() { return _op; }
    public Iexpression getright() { return _right; }

    public binary_op(IToken leftIToken, IToken rightIToken,
                     Iexpression _left,
                     Ibinary_operator _op,
                     Iexpression _right)
    {
        super(leftIToken, rightIToken);

        this._left = _left;
        ((ASTNode) _left).setParent(this);
        this._op = _op;
        ((ASTNode) _op).setParent(this);
        this._right = _right;
        ((ASTNode) _right).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_left);
        list.add(_op);
        list.add(_right);
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
        if (! (o instanceof binary_op)) return false;
        binary_op other = (binary_op) o;
        if (! _left.equals(other._left)) return false;
        if (! _op.equals(other._op)) return false;
        if (! _right.equals(other._right)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_left.hashCode());
        hash = hash * 31 + (_op.hashCode());
        hash = hash * 31 + (_right.hashCode());
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
            _left.accept(v);
            _op.accept(v);
            _right.accept(v);
        }
        v.endVisit(this);
    }
}


