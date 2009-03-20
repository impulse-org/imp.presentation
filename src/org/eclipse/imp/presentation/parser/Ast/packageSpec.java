package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 2:  packageSpec ::= PACKAGE$ qualifiedName ;$
 *</b>
 */
public class packageSpec extends ASTNode implements IpackageSpec
{
    private simpleNameList _qualifiedName;

    public simpleNameList getqualifiedName() { return _qualifiedName; }

    public packageSpec(IToken leftIToken, IToken rightIToken,
                       simpleNameList _qualifiedName)
    {
        super(leftIToken, rightIToken);

        this._qualifiedName = _qualifiedName;
        ((ASTNode) _qualifiedName).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_qualifiedName);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof packageSpec)) return false;
        if (! super.equals(o)) return false;
        packageSpec other = (packageSpec) o;
        if (! _qualifiedName.equals(other._qualifiedName)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_qualifiedName.hashCode());
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
            _qualifiedName.accept(v);
        v.endVisit(this);
    }
}


