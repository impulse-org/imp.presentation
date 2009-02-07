package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 115:  method_call ::= IDENTIFIER callSuffix
 *</b>
 */
public class method_call extends ASTNode implements Imethod_call
{
    private ASTNodeToken _IDENTIFIER;
    private callSuffix _callSuffix;

    public ASTNodeToken getIDENTIFIER() { return _IDENTIFIER; }
    public callSuffix getcallSuffix() { return _callSuffix; }

    public method_call(IToken leftIToken, IToken rightIToken,
                       ASTNodeToken _IDENTIFIER,
                       callSuffix _callSuffix)
    {
        super(leftIToken, rightIToken);

        this._IDENTIFIER = _IDENTIFIER;
        ((ASTNode) _IDENTIFIER).setParent(this);
        this._callSuffix = _callSuffix;
        ((ASTNode) _callSuffix).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_IDENTIFIER);
        list.add(_callSuffix);
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
        if (! (o instanceof method_call)) return false;
        method_call other = (method_call) o;
        if (! _IDENTIFIER.equals(other._IDENTIFIER)) return false;
        if (! _callSuffix.equals(other._callSuffix)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_IDENTIFIER.hashCode());
        hash = hash * 31 + (_callSuffix.hashCode());
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
            _IDENTIFIER.accept(v);
            _callSuffix.accept(v);
        }
        v.endVisit(this);
    }
}


