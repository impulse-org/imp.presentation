package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 23:  resource_decl ::= primitive_type IDENTIFIER$ident optInitializer$initializer ;$
 *</b>
 */
public class resource_decl extends ASTNode implements Iresource_decl
{
    private Iprimitive_type _primitive_type;
    private ASTNodeToken _ident;
    private optInitializer _initializer;

    public Iprimitive_type getprimitive_type() { return _primitive_type; }
    public ASTNodeToken getident() { return _ident; }
    /**
     * The value returned by <b>getinitializer</b> may be <b>null</b>
     */
    public optInitializer getinitializer() { return _initializer; }

    public resource_decl(IToken leftIToken, IToken rightIToken,
                         Iprimitive_type _primitive_type,
                         ASTNodeToken _ident,
                         optInitializer _initializer)
    {
        super(leftIToken, rightIToken);

        this._primitive_type = _primitive_type;
        ((ASTNode) _primitive_type).setParent(this);
        this._ident = _ident;
        ((ASTNode) _ident).setParent(this);
        this._initializer = _initializer;
        if (_initializer != null) ((ASTNode) _initializer).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_primitive_type);
        list.add(_ident);
        list.add(_initializer);
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
        if (! (o instanceof resource_decl)) return false;
        resource_decl other = (resource_decl) o;
        if (! _primitive_type.equals(other._primitive_type)) return false;
        if (! _ident.equals(other._ident)) return false;
        if (_initializer == null)
            if (other._initializer != null) return false;
            else; // continue
        else if (! _initializer.equals(other._initializer)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_primitive_type.hashCode());
        hash = hash * 31 + (_ident.hashCode());
        hash = hash * 31 + (_initializer == null ? 0 : _initializer.hashCode());
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
            _primitive_type.accept(v);
            _ident.accept(v);
            if (_initializer != null) _initializer.accept(v);
        }
        v.endVisit(this);
    }
}


