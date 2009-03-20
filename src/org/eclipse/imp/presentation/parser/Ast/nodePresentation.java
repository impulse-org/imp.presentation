package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 64:  nodePresentation ::= IDENTIFIER$type IDENTIFIER$name =$ {$ nodePresentationAttributes$attrs }$
 *</b>
 */
public class nodePresentation extends ASTNode implements InodePresentation
{
    private ASTNodeToken _type;
    private ASTNodeToken _name;
    private nodePresentationAttributeList _attrs;

    public ASTNodeToken gettype() { return _type; }
    public ASTNodeToken getname() { return _name; }
    public nodePresentationAttributeList getattrs() { return _attrs; }

    public nodePresentation(IToken leftIToken, IToken rightIToken,
                            ASTNodeToken _type,
                            ASTNodeToken _name,
                            nodePresentationAttributeList _attrs)
    {
        super(leftIToken, rightIToken);

        this._type = _type;
        ((ASTNode) _type).setParent(this);
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
        list.add(_type);
        list.add(_name);
        list.add(_attrs);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof nodePresentation)) return false;
        if (! super.equals(o)) return false;
        nodePresentation other = (nodePresentation) o;
        if (! _type.equals(other._type)) return false;
        if (! _name.equals(other._name)) return false;
        if (! _attrs.equals(other._attrs)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_type.hashCode());
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
            _type.accept(v);
            _name.accept(v);
            _attrs.accept(v);
        }
        v.endVisit(this);
    }
}


