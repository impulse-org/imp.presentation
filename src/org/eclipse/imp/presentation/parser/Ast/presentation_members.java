package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<em>
 *<li>Rule 16:  presentation_members ::= presentation_member
 *</em>
 *<p>
 *<b>
 *<li>Rule 17:  presentation_members ::= presentation_members presentation_member
 *</b>
 */
public class presentation_members extends ASTNode implements Ipresentation_members
{
    private Ipresentation_members _presentation_members;
    private Ipresentation_member _presentation_member;

    public Ipresentation_members getpresentation_members() { return _presentation_members; }
    public Ipresentation_member getpresentation_member() { return _presentation_member; }

    public presentation_members(IToken leftIToken, IToken rightIToken,
                                Ipresentation_members _presentation_members,
                                Ipresentation_member _presentation_member)
    {
        super(leftIToken, rightIToken);

        this._presentation_members = _presentation_members;
        ((ASTNode) _presentation_members).setParent(this);
        this._presentation_member = _presentation_member;
        ((ASTNode) _presentation_member).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_presentation_members);
        list.add(_presentation_member);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        //
        // The super call test is not required for now because an Ast node
        // can only extend the root Ast, AstToken and AstList and none of
        // these nodes contain additional children.
        //
        // if (! super.equals(o)) return false;
        //
        if (! (o instanceof presentation_members)) return false;
        presentation_members other = (presentation_members) o;
        if (! _presentation_members.equals(other._presentation_members)) return false;
        if (! _presentation_member.equals(other._presentation_member)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_presentation_members.hashCode());
        hash = hash * 31 + (_presentation_member.hashCode());
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
            _presentation_members.accept(v);
            _presentation_member.accept(v);
        }
        v.endVisit(this);
    }
}


