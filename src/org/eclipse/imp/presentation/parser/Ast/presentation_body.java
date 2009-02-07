package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 15:  presentation_body ::= {$ presentation_members }$
 *</b>
 */
public class presentation_body extends ASTNode implements Ipresentation_body
{
    private Ipresentation_members _presentation_members;

    public Ipresentation_members getpresentation_members() { return _presentation_members; }

    public presentation_body(IToken leftIToken, IToken rightIToken,
                             Ipresentation_members _presentation_members)
    {
        super(leftIToken, rightIToken);

        this._presentation_members = _presentation_members;
        ((ASTNode) _presentation_members).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_presentation_members);
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
        if (! (o instanceof presentation_body)) return false;
        presentation_body other = (presentation_body) o;
        if (! _presentation_members.equals(other._presentation_members)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_presentation_members.hashCode());
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
            _presentation_members.accept(v);
        v.endVisit(this);
    }
}


