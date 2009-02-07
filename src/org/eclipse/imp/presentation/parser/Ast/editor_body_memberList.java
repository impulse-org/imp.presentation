package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 71:  editor_body_members ::= editor_body_member
 *<li>Rule 72:  editor_body_members ::= editor_body_members editor_body_member
 *</b>
 */
public class editor_body_memberList extends AbstractASTNodeList implements Ieditor_body_members
{
    public use_spec geteditor_body_memberAt(int i) { return (use_spec) getElementAt(i); }

    public editor_body_memberList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public editor_body_memberList(use_spec _editor_body_member, boolean leftRecursive)
    {
        super((ASTNode) _editor_body_member, leftRecursive);
        ((ASTNode) _editor_body_member).setParent(this);
    }

    public void add(use_spec _editor_body_member)
    {
        super.add((ASTNode) _editor_body_member);
        ((ASTNode) _editor_body_member).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof editor_body_memberList)) return false;
        editor_body_memberList other = (editor_body_memberList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            use_spec element = geteditor_body_memberAt(i);
            if (! element.equals(other.geteditor_body_memberAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (geteditor_body_memberAt(i).hashCode());
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
                use_spec element = geteditor_body_memberAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


