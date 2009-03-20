package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 89:  alternativeList ::= alternative
 *<li>Rule 90:  alternativeList ::= alternativeList ,$ alternative
 *</b>
 */
public class alternativeList extends AbstractASTNodeList implements IalternativeList
{
    public alternative getalternativeAt(int i) { return (alternative) getElementAt(i); }

    public alternativeList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public alternativeList(alternative _alternative, boolean leftRecursive)
    {
        super((ASTNode) _alternative, leftRecursive);
        ((ASTNode) _alternative).setParent(this);
    }

    public void add(alternative _alternative)
    {
        super.add((ASTNode) _alternative);
        ((ASTNode) _alternative).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof alternativeList)) return false;
        if (! super.equals(o)) return false;
        alternativeList other = (alternativeList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            alternative element = getalternativeAt(i);
            if (! element.equals(other.getalternativeAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getalternativeAt(i).hashCode());
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
                alternative element = getalternativeAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


