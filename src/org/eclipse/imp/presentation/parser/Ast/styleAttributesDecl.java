package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 58:  styleAttributesDecl ::= STYLE$ =$ styleSet ;$
 *</b>
 */
public class styleAttributesDecl extends ASTNode implements IstyleAttributesDecl
{
    private styleSet _styleSet;

    public styleSet getstyleSet() { return _styleSet; }

    public styleAttributesDecl(IToken leftIToken, IToken rightIToken,
                               styleSet _styleSet)
    {
        super(leftIToken, rightIToken);

        this._styleSet = _styleSet;
        ((ASTNode) _styleSet).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_styleSet);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof styleAttributesDecl)) return false;
        if (! super.equals(o)) return false;
        styleAttributesDecl other = (styleAttributesDecl) o;
        if (! _styleSet.equals(other._styleSet)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_styleSet.hashCode());
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
            _styleSet.accept(v);
        v.endVisit(this);
    }
}


