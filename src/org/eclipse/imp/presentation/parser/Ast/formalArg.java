package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 31:  formalArg ::= typeName IDENTIFIER$name
 *</b>
 */
public class formalArg extends ASTNode implements IformalArg
{
    private ItypeName _typeName;
    private ASTNodeToken _name;

    public ItypeName gettypeName() { return _typeName; }
    public ASTNodeToken getname() { return _name; }

    public formalArg(IToken leftIToken, IToken rightIToken,
                     ItypeName _typeName,
                     ASTNodeToken _name)
    {
        super(leftIToken, rightIToken);

        this._typeName = _typeName;
        ((ASTNode) _typeName).setParent(this);
        this._name = _name;
        ((ASTNode) _name).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_typeName);
        list.add(_name);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof formalArg)) return false;
        if (! super.equals(o)) return false;
        formalArg other = (formalArg) o;
        if (! _typeName.equals(other._typeName)) return false;
        if (! _name.equals(other._name)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_typeName.hashCode());
        hash = hash * 31 + (_name.hashCode());
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
            _typeName.accept(v);
            _name.accept(v);
        }
        v.endVisit(this);
    }
}


