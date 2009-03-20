package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<em>
 *<li>Rule 32:  typeName ::= primitiveType
 *</em>
 *<p>
 *<b>
 *<li>Rule 33:  typeName ::= IDENTIFIER$type
 *</b>
 */
public class typeName extends ASTNodeToken implements ItypeName
{
    public IToken gettype() { return leftIToken; }

    public typeName(IToken token) { super(token); initialize(); }

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


