package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 35:  resourceDecl ::= primitiveType IDENTIFIER$ident =$ expression$initializer ;$
 *</b>
 */
public class resourceDecl extends ASTNode implements IresourceDecl
{
    private IprimitiveType _primitiveType;
    private ASTNodeToken _ident;
    private Iexpression _initializer;

    public IprimitiveType getprimitiveType() { return _primitiveType; }
    public ASTNodeToken getident() { return _ident; }
    public Iexpression getinitializer() { return _initializer; }

    public resourceDecl(IToken leftIToken, IToken rightIToken,
                        IprimitiveType _primitiveType,
                        ASTNodeToken _ident,
                        Iexpression _initializer)
    {
        super(leftIToken, rightIToken);

        this._primitiveType = _primitiveType;
        ((ASTNode) _primitiveType).setParent(this);
        this._ident = _ident;
        ((ASTNode) _ident).setParent(this);
        this._initializer = _initializer;
        ((ASTNode) _initializer).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_primitiveType);
        list.add(_ident);
        list.add(_initializer);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof resourceDecl)) return false;
        if (! super.equals(o)) return false;
        resourceDecl other = (resourceDecl) o;
        if (! _primitiveType.equals(other._primitiveType)) return false;
        if (! _ident.equals(other._ident)) return false;
        if (! _initializer.equals(other._initializer)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_primitiveType.hashCode());
        hash = hash * 31 + (_ident.hashCode());
        hash = hash * 31 + (_initializer.hashCode());
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
            _primitiveType.accept(v);
            _ident.accept(v);
            _initializer.accept(v);
        }
        v.endVisit(this);
    }
}


