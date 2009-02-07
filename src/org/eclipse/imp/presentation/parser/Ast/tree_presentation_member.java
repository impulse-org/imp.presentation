package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 52:  tree_presentation_member ::= NODE$ IDENTIFIER$name =$ {$ tree_presentation_attributes$attrs }$
 *</b>
 */
public class tree_presentation_member extends ASTNode implements Itree_presentation_member
{
    private ASTNodeToken _name;
    private tree_presentation_attributeList _attrs;

    public ASTNodeToken getname() { return _name; }
    public tree_presentation_attributeList getattrs() { return _attrs; }

    public tree_presentation_member(IToken leftIToken, IToken rightIToken,
                                    ASTNodeToken _name,
                                    tree_presentation_attributeList _attrs)
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
        //
        // The super call test is not required for now because an Ast node
        // can only extend the root Ast, AstToken and AstList and none of
        // these nodes contain additional children.
        //
        // if (! super.equals(o)) return false;
        //
        if (! (o instanceof tree_presentation_member)) return false;
        tree_presentation_member other = (tree_presentation_member) o;
        if (! _name.equals(other._name)) return false;
        if (! _attrs.equals(other._attrs)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
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


