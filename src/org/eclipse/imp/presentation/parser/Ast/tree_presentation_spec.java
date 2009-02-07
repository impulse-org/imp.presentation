package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 49:  tree_presentation_spec ::= TREE$ IDENTIFIER$name {$ tree_presentation_members$members }$
 *</b>
 */
public class tree_presentation_spec extends ASTNode implements Itree_presentation_spec
{
    private ASTNodeToken _name;
    private tree_presentation_memberList _members;

    public ASTNodeToken getname() { return _name; }
    public tree_presentation_memberList getmembers() { return _members; }

    public tree_presentation_spec(IToken leftIToken, IToken rightIToken,
                                  ASTNodeToken _name,
                                  tree_presentation_memberList _members)
    {
        super(leftIToken, rightIToken);

        this._name = _name;
        ((ASTNode) _name).setParent(this);
        this._members = _members;
        ((ASTNode) _members).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_name);
        list.add(_members);
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
        if (! (o instanceof tree_presentation_spec)) return false;
        tree_presentation_spec other = (tree_presentation_spec) o;
        if (! _name.equals(other._name)) return false;
        if (! _members.equals(other._members)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_name.hashCode());
        hash = hash * 31 + (_members.hashCode());
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
            _members.accept(v);
        }
        v.endVisit(this);
    }
}


