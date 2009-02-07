package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 15:  languageBody ::= {$ languageMembers }$
 *</b>
 */
public class languageBody extends ASTNode implements IlanguageBody
{
    private languageMemberList _languageMembers;

    public languageMemberList getlanguageMembers() { return _languageMembers; }

    public languageBody(IToken leftIToken, IToken rightIToken,
                        languageMemberList _languageMembers)
    {
        super(leftIToken, rightIToken);

        this._languageMembers = _languageMembers;
        ((ASTNode) _languageMembers).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_languageMembers);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof languageBody)) return false;
        if (! super.equals(o)) return false;
        languageBody other = (languageBody) o;
        if (! _languageMembers.equals(other._languageMembers)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_languageMembers.hashCode());
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
            _languageMembers.accept(v);
        v.endVisit(this);
    }
}


