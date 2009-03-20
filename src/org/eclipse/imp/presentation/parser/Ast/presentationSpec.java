package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 59:  presentationSpec ::= PRESENTATION$ {$ presentationMembers$members }$
 *</b>
 */
public class presentationSpec extends ASTNode implements IpresentationSpec
{
    private presentationMemberList _members;

    public presentationMemberList getmembers() { return _members; }

    public presentationSpec(IToken leftIToken, IToken rightIToken,
                            presentationMemberList _members)
    {
        super(leftIToken, rightIToken);

        this._members = _members;
        ((ASTNode) _members).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_members);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof presentationSpec)) return false;
        if (! super.equals(o)) return false;
        presentationSpec other = (presentationSpec) o;
        if (! _members.equals(other._members)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_members.hashCode());
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
            _members.accept(v);
        v.endVisit(this);
    }
}


