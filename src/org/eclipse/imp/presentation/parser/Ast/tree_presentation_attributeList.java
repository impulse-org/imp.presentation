package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 53:  tree_presentation_attributes ::= $Empty
 *<li>Rule 54:  tree_presentation_attributes ::= tree_presentation_attributes tree_presentation_attribute
 *</b>
 */
public class tree_presentation_attributeList extends AbstractASTNodeList implements Itree_presentation_attributes
{
    public Itree_presentation_attribute gettree_presentation_attributeAt(int i) { return (Itree_presentation_attribute) getElementAt(i); }

    public tree_presentation_attributeList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public tree_presentation_attributeList(Itree_presentation_attribute _tree_presentation_attribute, boolean leftRecursive)
    {
        super((ASTNode) _tree_presentation_attribute, leftRecursive);
        ((ASTNode) _tree_presentation_attribute).setParent(this);
    }

    public void add(Itree_presentation_attribute _tree_presentation_attribute)
    {
        super.add((ASTNode) _tree_presentation_attribute);
        ((ASTNode) _tree_presentation_attribute).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof tree_presentation_attributeList)) return false;
        tree_presentation_attributeList other = (tree_presentation_attributeList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            Itree_presentation_attribute element = gettree_presentation_attributeAt(i);
            if (! element.equals(other.gettree_presentation_attributeAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (gettree_presentation_attributeAt(i).hashCode());
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
                Itree_presentation_attribute element = gettree_presentation_attributeAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


