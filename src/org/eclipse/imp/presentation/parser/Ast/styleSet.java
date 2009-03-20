package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 41:  styleSet ::= {$ styleList }$
 *</b>
 */
public class styleSet extends ASTNode implements IstyleSet
{
    private styleKeyList _styleList;

    public styleKeyList getstyleList() { return _styleList; }

    public styleSet(IToken leftIToken, IToken rightIToken,
                    styleKeyList _styleList)
    {
        super(leftIToken, rightIToken);

        this._styleList = _styleList;
        ((ASTNode) _styleList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_styleList);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof styleSet)) return false;
        if (! super.equals(o)) return false;
        styleSet other = (styleSet) o;
        if (! _styleList.equals(other._styleList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_styleList.hashCode());
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
            _styleList.accept(v);
        v.endVisit(this);
    }
}


