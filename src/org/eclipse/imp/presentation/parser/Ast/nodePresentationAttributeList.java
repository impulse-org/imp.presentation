package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 65:  nodePresentationAttributes ::= $Empty
 *<li>Rule 66:  nodePresentationAttributes ::= nodePresentationAttributes nodePresentationAttribute
 *</b>
 */
public class nodePresentationAttributeList extends AbstractASTNodeList implements InodePresentationAttributes
{
    public InodePresentationAttribute getnodePresentationAttributeAt(int i) { return (InodePresentationAttribute) getElementAt(i); }

    public nodePresentationAttributeList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public nodePresentationAttributeList(InodePresentationAttribute _nodePresentationAttribute, boolean leftRecursive)
    {
        super((ASTNode) _nodePresentationAttribute, leftRecursive);
        ((ASTNode) _nodePresentationAttribute).setParent(this);
    }

    public void add(InodePresentationAttribute _nodePresentationAttribute)
    {
        super.add((ASTNode) _nodePresentationAttribute);
        ((ASTNode) _nodePresentationAttribute).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof nodePresentationAttributeList)) return false;
        if (! super.equals(o)) return false;
        nodePresentationAttributeList other = (nodePresentationAttributeList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            InodePresentationAttribute element = getnodePresentationAttributeAt(i);
            if (! element.equals(other.getnodePresentationAttributeAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getnodePresentationAttributeAt(i).hashCode());
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
                InodePresentationAttribute element = getnodePresentationAttributeAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


