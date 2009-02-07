package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 24:  foldingSpec ::= FOLDABLE$ {$ foldableNodes }$
 *</b>
 */
public class foldingSpec extends ASTNode implements IfoldingSpec
{
    private foldableNodeList _foldableNodes;

    public foldableNodeList getfoldableNodes() { return _foldableNodes; }

    public foldingSpec(IToken leftIToken, IToken rightIToken,
                       foldableNodeList _foldableNodes)
    {
        super(leftIToken, rightIToken);

        this._foldableNodes = _foldableNodes;
        ((ASTNode) _foldableNodes).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_foldableNodes);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof foldingSpec)) return false;
        if (! super.equals(o)) return false;
        foldingSpec other = (foldingSpec) o;
        if (! _foldableNodes.equals(other._foldableNodes)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_foldableNodes.hashCode());
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
            _foldableNodes.accept(v);
        v.endVisit(this);
    }
}


