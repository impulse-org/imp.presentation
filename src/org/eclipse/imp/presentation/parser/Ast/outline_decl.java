package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 59:  outline_decl ::= OUTLINE$ IDENTIFIER$id super_opt$sup outline_body$body
 *</b>
 */
public class outline_decl extends ASTNode implements Ioutline_decl
{
    private ASTNodeToken _id;
    private super_opt _sup;
    private outline_body _body;

    public ASTNodeToken getid() { return _id; }
    /**
     * The value returned by <b>getsup</b> may be <b>null</b>
     */
    public super_opt getsup() { return _sup; }
    public outline_body getbody() { return _body; }

    public outline_decl(IToken leftIToken, IToken rightIToken,
                        ASTNodeToken _id,
                        super_opt _sup,
                        outline_body _body)
    {
        super(leftIToken, rightIToken);

        this._id = _id;
        ((ASTNode) _id).setParent(this);
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
        list.add(_id);
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
        if (! (o instanceof outline_decl)) return false;
        outline_decl other = (outline_decl) o;
        if (! _id.equals(other._id)) return false;
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
        hash = hash * 31 + (_id.hashCode());
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
            _id.accept(v);
            if (_sup != null) _sup.accept(v);
            _body.accept(v);
        }
        v.endVisit(this);
    }
}


