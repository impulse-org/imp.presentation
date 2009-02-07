package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 88:  caseExpression ::= CASE$ {$ alternativeList }$
 *</b>
 */
public class caseExpression extends ASTNode implements IcaseExpression
{
    private alternativeList _alternativeList;

    public alternativeList getalternativeList() { return _alternativeList; }

    public caseExpression(IToken leftIToken, IToken rightIToken,
                          alternativeList _alternativeList)
    {
        super(leftIToken, rightIToken);

        this._alternativeList = _alternativeList;
        ((ASTNode) _alternativeList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_alternativeList);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof caseExpression)) return false;
        if (! super.equals(o)) return false;
        caseExpression other = (caseExpression) o;
        if (! _alternativeList.equals(other._alternativeList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_alternativeList.hashCode());
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
            _alternativeList.accept(v);
        v.endVisit(this);
    }
}


