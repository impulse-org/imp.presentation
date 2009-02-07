package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 * is implemented by:
 *<b>
 *<ul>
 *<li>foldingSpec
 *<li>functionDecl
 *<li>resourceDecl
 *<li>textColoringSpec
 *<li>presentationSpec
 *<li>outlineSpec
 *</ul>
 *</b>
 */
public interface IlanguageMember
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


