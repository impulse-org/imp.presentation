package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 50:  tokenDecl ::= IDENTIFIER$name =$ {$ tokenDeclAttributes$attrs }$
 *</b>
 */
public class tokenDecl extends ASTNode implements ItokenDecl
{
    private ASTNodeToken _name;
    private textAttributeDeclList _attrs;

    public ASTNodeToken getname() { return _name; }
    public textAttributeDeclList getattrs() { return _attrs; }

    public tokenDecl(IToken leftIToken, IToken rightIToken,
                     ASTNodeToken _name,
                     textAttributeDeclList _attrs)
    {
        super(leftIToken, rightIToken);

        this._name = _name;
        ((ASTNode) _name).setParent(this);
        this._attrs = _attrs;
        ((ASTNode) _attrs).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_name);
        list.add(_attrs);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof tokenDecl)) return false;
        if (! super.equals(o)) return false;
        tokenDecl other = (tokenDecl) o;
        if (! _name.equals(other._name)) return false;
        if (! _attrs.equals(other._attrs)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_name.hashCode());
        hash = hash * 31 + (_attrs.hashCode());
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
            _attrs.accept(v);
        }
        v.endVisit(this);
    }
}


