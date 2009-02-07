package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 75:  nodeSpec ::= NODE$ IDENTIFIER$id ;$
 *</b>
 */
public class nodeSpec extends ASTNode implements InodeSpec
{
    private ASTNodeToken _id;

    public ASTNodeToken getid() { return _id; }

    public nodeSpec(IToken leftIToken, IToken rightIToken,
                    ASTNodeToken _id)
    {
        super(leftIToken, rightIToken);

        this._id = _id;
        ((ASTNode) _id).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_id);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof nodeSpec)) return false;
        if (! super.equals(o)) return false;
        nodeSpec other = (nodeSpec) o;
        if (! _id.equals(other._id)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_id.hashCode());
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
            _id.accept(v);
        v.endVisit(this);
    }
}


