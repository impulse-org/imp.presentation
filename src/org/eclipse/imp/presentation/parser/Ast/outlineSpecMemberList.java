package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 72:  outlineSpecMembers ::= outlineSpecMember
 *<li>Rule 73:  outlineSpecMembers ::= outlineSpecMembers outlineSpecMember
 *</b>
 */
public class outlineSpecMemberList extends AbstractASTNodeList implements IoutlineSpecMembers
{
    public nodeSpec getoutlineSpecMemberAt(int i) { return (nodeSpec) getElementAt(i); }

    public outlineSpecMemberList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public outlineSpecMemberList(nodeSpec _outlineSpecMember, boolean leftRecursive)
    {
        super((ASTNode) _outlineSpecMember, leftRecursive);
        ((ASTNode) _outlineSpecMember).setParent(this);
    }

    public void add(nodeSpec _outlineSpecMember)
    {
        super.add((ASTNode) _outlineSpecMember);
        ((ASTNode) _outlineSpecMember).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof outlineSpecMemberList)) return false;
        if (! super.equals(o)) return false;
        outlineSpecMemberList other = (outlineSpecMemberList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            nodeSpec element = getoutlineSpecMemberAt(i);
            if (! element.equals(other.getoutlineSpecMemberAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getoutlineSpecMemberAt(i).hashCode());
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
                nodeSpec element = getoutlineSpecMemberAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


