package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 113:  postfix_unary_op ::= --
 *</b>
 */
public class postfix_unary_op1 extends ASTNodeToken implements Ipostfix_unary_op
{
    public IToken getMINUSMIN() { return leftIToken; }

    public postfix_unary_op1(IToken token) { super(token); initialize(); }

    public void accept(IAstVisitor v)
    {
        if (! v.preVisit(this)) return;
        enter((Visitor) v);
        v.postVisit(this);
    }

    public void enter(Visitor v)
    {
        v.visit(this);
        v.endVisit(this);
    }
}


