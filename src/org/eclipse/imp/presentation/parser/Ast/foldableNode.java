package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 27:  foldableNode ::= NODE$ IDENTIFIER$nodeName ;$
 *</b>
 */
public class foldableNode extends ASTNode implements IfoldableNode
{
    private ASTNodeToken _nodeName;

    public ASTNodeToken getnodeName() { return _nodeName; }

    public foldableNode(IToken leftIToken, IToken rightIToken,
                        ASTNodeToken _nodeName)
    {
        super(leftIToken, rightIToken);

        this._nodeName = _nodeName;
        ((ASTNode) _nodeName).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_nodeName);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof foldableNode)) return false;
        if (! super.equals(o)) return false;
        foldableNode other = (foldableNode) o;
        if (! _nodeName.equals(other._nodeName)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_nodeName.hashCode());
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
            _nodeName.accept(v);
        v.endVisit(this);
    }
}


