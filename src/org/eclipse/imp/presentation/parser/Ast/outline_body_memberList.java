package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 61:  outline_body_members ::= outline_body_member
 *<li>Rule 62:  outline_body_members ::= outline_body_members outline_body_member
 *</b>
 */
public class outline_body_memberList extends AbstractASTNodeList implements Ioutline_body_members
{
    public Ioutline_body_member getoutline_body_memberAt(int i) { return (Ioutline_body_member) getElementAt(i); }

    public outline_body_memberList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public outline_body_memberList(Ioutline_body_member _outline_body_member, boolean leftRecursive)
    {
        super((ASTNode) _outline_body_member, leftRecursive);
        ((ASTNode) _outline_body_member).setParent(this);
    }

    public void add(Ioutline_body_member _outline_body_member)
    {
        super.add((ASTNode) _outline_body_member);
        ((ASTNode) _outline_body_member).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof outline_body_memberList)) return false;
        outline_body_memberList other = (outline_body_memberList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            Ioutline_body_member element = getoutline_body_memberAt(i);
            if (! element.equals(other.getoutline_body_memberAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getoutline_body_memberAt(i).hashCode());
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
                Ioutline_body_member element = getoutline_body_memberAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


