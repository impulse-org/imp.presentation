package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 16:  languageMembers ::= languageMember
 *<li>Rule 17:  languageMembers ::= languageMembers languageMember
 *</b>
 */
public class languageMemberList extends AbstractASTNodeList implements IlanguageMembers
{
    public IlanguageMember getlanguageMemberAt(int i) { return (IlanguageMember) getElementAt(i); }

    public languageMemberList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public languageMemberList(IlanguageMember _languageMember, boolean leftRecursive)
    {
        super((ASTNode) _languageMember, leftRecursive);
        ((ASTNode) _languageMember).setParent(this);
    }

    public void add(IlanguageMember _languageMember)
    {
        super.add((ASTNode) _languageMember);
        ((ASTNode) _languageMember).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof languageMemberList)) return false;
        if (! super.equals(o)) return false;
        languageMemberList other = (languageMemberList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            IlanguageMember element = getlanguageMemberAt(i);
            if (! element.equals(other.getlanguageMemberAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getlanguageMemberAt(i).hashCode());
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
                IlanguageMember element = getlanguageMemberAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


