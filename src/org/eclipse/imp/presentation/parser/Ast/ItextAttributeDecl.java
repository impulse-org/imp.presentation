package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 * is implemented by:
 *<b>
 *<ul>
 *<li>fontAttributeDecl
 *<li>colorAttributeDecl
 *<li>styleAttributesDecl
 *</ul>
 *</b>
 */
public interface ItextAttributeDecl
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


