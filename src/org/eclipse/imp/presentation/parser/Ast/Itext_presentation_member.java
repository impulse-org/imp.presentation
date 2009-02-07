package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 * is implemented by:
 *<b>
 *<ul>
 *<li>resource_decl
 *<li>token_decl
 *</ul>
 *</b>
 */
public interface Itext_presentation_member
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


