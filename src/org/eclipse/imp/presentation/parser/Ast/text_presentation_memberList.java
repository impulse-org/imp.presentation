package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 27:  text_presentation_members ::= $Empty
 *<li>Rule 28:  text_presentation_members ::= text_presentation_members text_presentation_member
 *</b>
 */
public class text_presentation_memberList extends AbstractASTNodeList implements Itext_presentation_members
{
    public Itext_presentation_member gettext_presentation_memberAt(int i) { return (Itext_presentation_member) getElementAt(i); }

    public text_presentation_memberList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public text_presentation_memberList(Itext_presentation_member _text_presentation_member, boolean leftRecursive)
    {
        super((ASTNode) _text_presentation_member, leftRecursive);
        ((ASTNode) _text_presentation_member).setParent(this);
    }

    public void add(Itext_presentation_member _text_presentation_member)
    {
        super.add((ASTNode) _text_presentation_member);
        ((ASTNode) _text_presentation_member).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof text_presentation_memberList)) return false;
        text_presentation_memberList other = (text_presentation_memberList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            Itext_presentation_member element = gettext_presentation_memberAt(i);
            if (! element.equals(other.gettext_presentation_memberAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (gettext_presentation_memberAt(i).hashCode());
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
                Itext_presentation_member element = gettext_presentation_memberAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


