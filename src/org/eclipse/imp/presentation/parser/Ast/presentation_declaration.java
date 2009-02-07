package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 12:  presentation_declaration ::= PRESENTATION$ IDENTIFIER$name super_opt$sup presentation_body$body
 *</b>
 */
public class presentation_declaration extends ASTNode implements Ipresentation_declaration
{
    private ASTNodeToken _name;
    private super_opt _sup;
    private presentation_body _body;

    public ASTNodeToken getname() { return _name; }
    /**
     * The value returned by <b>getsup</b> may be <b>null</b>
     */
    public super_opt getsup() { return _sup; }
    public presentation_body getbody() { return _body; }

    public presentation_declaration(IToken leftIToken, IToken rightIToken,
                                    ASTNodeToken _name,
                                    super_opt _sup,
                                    presentation_body _body)
    {
        super(leftIToken, rightIToken);

        this._name = _name;
        ((ASTNode) _name).setParent(this);
        this._sup = _sup;
        if (_sup != null) ((ASTNode) _sup).setParent(this);
        this._body = _body;
        ((ASTNode) _body).setParent(this);
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
        list.add(_body);
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
        if (! (o instanceof presentation_declaration)) return false;
        presentation_declaration other = (presentation_declaration) o;
        if (! _name.equals(other._name)) return false;
        if (_sup == null)
            if (other._sup != null) return false;
            else; // continue
        else if (! _sup.equals(other._sup)) return false;
        if (! _body.equals(other._body)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_name.hashCode());
        hash = hash * 31 + (_sup == null ? 0 : _sup.hashCode());
        hash = hash * 31 + (_body.hashCode());
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
            _body.accept(v);
        }
        v.endVisit(this);
    }
}


