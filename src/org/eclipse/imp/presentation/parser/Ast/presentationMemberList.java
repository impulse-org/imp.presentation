package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 60:  presentationMembers ::= $Empty
 *<li>Rule 61:  presentationMembers ::= presentationMembers presentationMember
 *</b>
 */
public class presentationMemberList extends AbstractASTNodeList implements IpresentationMembers
{
    public IpresentationMember getpresentationMemberAt(int i) { return (IpresentationMember) getElementAt(i); }

    public presentationMemberList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public presentationMemberList(IpresentationMember _presentationMember, boolean leftRecursive)
    {
        super((ASTNode) _presentationMember, leftRecursive);
        ((ASTNode) _presentationMember).setParent(this);
    }

    public void add(IpresentationMember _presentationMember)
    {
        super.add((ASTNode) _presentationMember);
        ((ASTNode) _presentationMember).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof presentationMemberList)) return false;
        if (! super.equals(o)) return false;
        presentationMemberList other = (presentationMemberList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            IpresentationMember element = getpresentationMemberAt(i);
            if (! element.equals(other.getpresentationMemberAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getpresentationMemberAt(i).hashCode());
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
                IpresentationMember element = getpresentationMemberAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


