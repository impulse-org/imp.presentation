package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 116:  callSuffix ::= ($ parameter_list )$
 *</b>
 */
public class callSuffix extends ASTNode implements IcallSuffix
{
    private expressionList _parameter_list;

    public expressionList getparameter_list() { return _parameter_list; }

    public callSuffix(IToken leftIToken, IToken rightIToken,
                      expressionList _parameter_list)
    {
        super(leftIToken, rightIToken);

        this._parameter_list = _parameter_list;
        ((ASTNode) _parameter_list).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_parameter_list);
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
        if (! (o instanceof callSuffix)) return false;
        callSuffix other = (callSuffix) o;
        if (! _parameter_list.equals(other._parameter_list)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_parameter_list.hashCode());
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
            _parameter_list.accept(v);
        v.endVisit(this);
    }
}


