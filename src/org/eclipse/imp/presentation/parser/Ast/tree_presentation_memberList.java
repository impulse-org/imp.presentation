package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 50:  tree_presentation_members ::= $Empty
 *<li>Rule 51:  tree_presentation_members ::= tree_presentation_members tree_presentation_member
 *</b>
 */
public class tree_presentation_memberList extends AbstractASTNodeList implements Itree_presentation_members
{
    public tree_presentation_member gettree_presentation_memberAt(int i) { return (tree_presentation_member) getElementAt(i); }

    public tree_presentation_memberList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public tree_presentation_memberList(tree_presentation_member _tree_presentation_member, boolean leftRecursive)
    {
        super((ASTNode) _tree_presentation_member, leftRecursive);
        ((ASTNode) _tree_presentation_member).setParent(this);
    }

    public void add(tree_presentation_member _tree_presentation_member)
    {
        super.add((ASTNode) _tree_presentation_member);
        ((ASTNode) _tree_presentation_member).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof tree_presentation_memberList)) return false;
        tree_presentation_memberList other = (tree_presentation_memberList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            tree_presentation_member element = gettree_presentation_memberAt(i);
            if (! element.equals(other.gettree_presentation_memberAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (gettree_presentation_memberAt(i).hashCode());
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
                tree_presentation_member element = gettree_presentation_memberAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


