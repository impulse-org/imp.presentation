package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 6:  qualifiedName ::= simpleName
 *<li>Rule 7:  qualifiedName ::= qualifiedName .$ simpleName
 *</b>
 */
public class simpleNameList extends AbstractASTNodeList implements IqualifiedName
{
    public IsimpleName getsimpleNameAt(int i) { return (IsimpleName) getElementAt(i); }

    public simpleNameList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public simpleNameList(IsimpleName _simpleName, boolean leftRecursive)
    {
        super((ASTNode) _simpleName, leftRecursive);
        ((ASTNode) _simpleName).setParent(this);
    }

    public void add(IsimpleName _simpleName)
    {
        super.add((ASTNode) _simpleName);
        ((ASTNode) _simpleName).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof simpleNameList)) return false;
        if (! super.equals(o)) return false;
        simpleNameList other = (simpleNameList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            IsimpleName element = getsimpleNameAt(i);
            if (! element.equals(other.getsimpleNameAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getsimpleNameAt(i).hashCode());
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
                IsimpleName element = getsimpleNameAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


