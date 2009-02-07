package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 117:  parameter_list ::= $Empty
 *<li>Rule 118:  parameter_list ::= expression
 *<li>Rule 119:  parameter_list ::= parameter_list ,$ expression
 *</b>
 */
public class expressionList extends AbstractASTNodeList implements Iparameter_list
{
    public Iexpression getexpressionAt(int i) { return (Iexpression) getElementAt(i); }

    public expressionList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public expressionList(Iexpression _expression, boolean leftRecursive)
    {
        super((ASTNode) _expression, leftRecursive);
        ((ASTNode) _expression).setParent(this);
    }

    public void add(Iexpression _expression)
    {
        super.add((ASTNode) _expression);
        ((ASTNode) _expression).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof expressionList)) return false;
        expressionList other = (expressionList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            Iexpression element = getexpressionAt(i);
            if (! element.equals(other.getexpressionAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getexpressionAt(i).hashCode());
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
            for (int i = 0; i < size(); i++)
            {
                Iexpression element = getexpressionAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


