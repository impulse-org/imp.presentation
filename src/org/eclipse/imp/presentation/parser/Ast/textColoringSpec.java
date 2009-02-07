package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 36:  textColoringSpec ::= TEXT$ super_opt$sup {$ textColoringMembers }$
 *</b>
 */
public class textColoringSpec extends ASTNode implements ItextColoringSpec
{
    private super_opt _sup;
    private textColoringMemberList _textColoringMembers;

    /**
     * The value returned by <b>getsup</b> may be <b>null</b>
     */
    public super_opt getsup() { return _sup; }
    public textColoringMemberList gettextColoringMembers() { return _textColoringMembers; }

    public textColoringSpec(IToken leftIToken, IToken rightIToken,
                            super_opt _sup,
                            textColoringMemberList _textColoringMembers)
    {
        super(leftIToken, rightIToken);

        this._sup = _sup;
        if (_sup != null) ((ASTNode) _sup).setParent(this);
        this._textColoringMembers = _textColoringMembers;
        ((ASTNode) _textColoringMembers).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_sup);
        list.add(_textColoringMembers);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof textColoringSpec)) return false;
        if (! super.equals(o)) return false;
        textColoringSpec other = (textColoringSpec) o;
        if (_sup == null)
            if (other._sup != null) return false;
            else; // continue
        else if (! _sup.equals(other._sup)) return false;
        if (! _textColoringMembers.equals(other._textColoringMembers)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_sup == null ? 0 : _sup.hashCode());
        hash = hash * 31 + (_textColoringMembers.hashCode());
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
            if (_sup != null) _sup.accept(v);
            _textColoringMembers.accept(v);
        }
        v.endVisit(this);
    }
}


