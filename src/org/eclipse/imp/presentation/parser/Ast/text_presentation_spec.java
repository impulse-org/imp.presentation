package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 26:  text_presentation_spec ::= TEXT$ IDENTIFIER$name super_opt$sup {$ text_presentation_members }$
 *</b>
 */
public class text_presentation_spec extends ASTNode implements Itext_presentation_spec
{
    private ASTNodeToken _name;
    private super_opt _sup;
    private text_presentation_memberList _text_presentation_members;

    public ASTNodeToken getname() { return _name; }
    /**
     * The value returned by <b>getsup</b> may be <b>null</b>
     */
    public super_opt getsup() { return _sup; }
    public text_presentation_memberList gettext_presentation_members() { return _text_presentation_members; }

    public text_presentation_spec(IToken leftIToken, IToken rightIToken,
                                  ASTNodeToken _name,
                                  super_opt _sup,
                                  text_presentation_memberList _text_presentation_members)
    {
        super(leftIToken, rightIToken);

        this._name = _name;
        ((ASTNode) _name).setParent(this);
        this._sup = _sup;
        if (_sup != null) ((ASTNode) _sup).setParent(this);
        this._text_presentation_members = _text_presentation_members;
        ((ASTNode) _text_presentation_members).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_name);
        list.add(_sup);
        list.add(_text_presentation_members);
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
        if (! (o instanceof text_presentation_spec)) return false;
        text_presentation_spec other = (text_presentation_spec) o;
        if (! _name.equals(other._name)) return false;
        if (_sup == null)
            if (other._sup != null) return false;
            else; // continue
        else if (! _sup.equals(other._sup)) return false;
        if (! _text_presentation_members.equals(other._text_presentation_members)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_name.hashCode());
        hash = hash * 31 + (_sup == null ? 0 : _sup.hashCode());
        hash = hash * 31 + (_text_presentation_members.hashCode());
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
            _name.accept(v);
            if (_sup != null) _sup.accept(v);
            _text_presentation_members.accept(v);
        }
        v.endVisit(this);
    }
}


