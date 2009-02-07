package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 91:  alternative ::= JAVA_EXPR$cond ->$ expression
 *</b>
 */
public class alternative extends ASTNode implements Ialternative
{
    private ASTNodeToken _cond;
    private Iexpression _expression;

    public ASTNodeToken getcond() { return _cond; }
    public Iexpression getexpression() { return _expression; }

    public alternative(IToken leftIToken, IToken rightIToken,
                       ASTNodeToken _cond,
                       Iexpression _expression)
    {
        super(leftIToken, rightIToken);

        this._cond = _cond;
        ((ASTNode) _cond).setParent(this);
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
        list.add(_cond);
        list.add(_expression);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof alternative)) return false;
        if (! super.equals(o)) return false;
        alternative other = (alternative) o;
        if (! _cond.equals(other._cond)) return false;
        if (! _expression.equals(other._expression)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_cond.hashCode());
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
            _cond.accept(v);
            _expression.accept(v);
        }
        v.endVisit(this);
    }
}


