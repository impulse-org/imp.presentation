package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 70:  editor_body ::= {$ editor_body_members$members }$
 *</b>
 */
public class editor_body extends ASTNode implements Ieditor_body
{
    private editor_body_memberList _members;

    public editor_body_memberList getmembers() { return _members; }

    public editor_body(IToken leftIToken, IToken rightIToken,
                       editor_body_memberList _members)
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
        //
        // The super call test is not required for now because an Ast node
        // can only extend the root Ast, AstToken and AstList and none of
        // these nodes contain additional children.
        //
        // if (! super.equals(o)) return false;
        //
        if (! (o instanceof editor_body)) return false;
        editor_body other = (editor_body) o;
        if (! _members.equals(other._members)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
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


