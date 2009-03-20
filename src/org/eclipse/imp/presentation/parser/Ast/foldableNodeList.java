package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 25:  foldableNodes ::= foldableNode
 *<li>Rule 26:  foldableNodes ::= foldableNodes foldableNode
 *</b>
 */
public class foldableNodeList extends AbstractASTNodeList implements IfoldableNodes
{
    public foldableNode getfoldableNodeAt(int i) { return (foldableNode) getElementAt(i); }

    public foldableNodeList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public foldableNodeList(foldableNode _foldableNode, boolean leftRecursive)
    {
        super((ASTNode) _foldableNode, leftRecursive);
        ((ASTNode) _foldableNode).setParent(this);
    }

    public void add(foldableNode _foldableNode)
    {
        super.add((ASTNode) _foldableNode);
        ((ASTNode) _foldableNode).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof foldableNodeList)) return false;
        if (! super.equals(o)) return false;
        foldableNodeList other = (foldableNodeList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            foldableNode element = getfoldableNodeAt(i);
            if (! element.equals(other.getfoldableNodeAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getfoldableNodeAt(i).hashCode());
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
                foldableNode element = getfoldableNodeAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


