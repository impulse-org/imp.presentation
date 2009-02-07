package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 37:  textColoringMembers ::= $Empty
 *<li>Rule 38:  textColoringMembers ::= textColoringMembers textColoringMember
 *</b>
 */
public class textColoringMemberList extends AbstractASTNodeList implements ItextColoringMembers
{
    public ItextColoringMember gettextColoringMemberAt(int i) { return (ItextColoringMember) getElementAt(i); }

    public textColoringMemberList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public textColoringMemberList(ItextColoringMember _textColoringMember, boolean leftRecursive)
    {
        super((ASTNode) _textColoringMember, leftRecursive);
        ((ASTNode) _textColoringMember).setParent(this);
    }

    public void add(ItextColoringMember _textColoringMember)
    {
        super.add((ASTNode) _textColoringMember);
        ((ASTNode) _textColoringMember).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof textColoringMemberList)) return false;
        if (! super.equals(o)) return false;
        textColoringMemberList other = (textColoringMemberList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            ItextColoringMember element = gettextColoringMemberAt(i);
            if (! element.equals(other.gettextColoringMemberAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (gettextColoringMemberAt(i).hashCode());
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
                ItextColoringMember element = gettextColoringMemberAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


