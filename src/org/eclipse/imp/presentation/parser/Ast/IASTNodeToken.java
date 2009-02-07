package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 * is always implemented by <b>ASTNodeToken</b>. It is also implemented by:
 *<b>
 *<ul>
 *<li>typeName
 *<li>caseExpression
 *<li>simpleName0
 *<li>simpleName1
 *<li>styleKey0
 *<li>styleKey1
 *<li>styleKey2
 *<li>styleKey3
 *<li>resourceValue0
 *<li>resourceValue1
 *<li>primitiveType0
 *<li>primitiveType1
 *<li>primitiveType2
 *<li>primitiveType3
 *<li>primitiveType4
 *<li>primitiveType5
 *<li>expression0
 *<li>expression1
 *<li>literal0
 *<li>literal1
 *</ul>
 *</b>
 */
public interface IASTNodeToken
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


