package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<em>
 *<li>Rule 81:  expression ::= literal
 *<li>Rule 83:  expression ::= binary_op
 *<li>Rule 84:  expression ::= unary_op
 *<li>Rule 85:  expression ::= member_ref
 *<li>Rule 86:  expression ::= method_call
 *<li>Rule 87:  expression ::= set_expression
 *</em>
 *<p>
 *<b>
 *<li>Rule 82:  expression ::= IDENTIFIER
 *</b>
 */
public class expression extends ASTNodeToken implements Iexpression
{
    public IToken getIDENTIFIER() { return leftIToken; }

    public expression(IToken token) { super(token); initialize(); }

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


