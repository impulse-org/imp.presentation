package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 * is implemented by:
 *<b>
 *<ul>
 *<li>font_attribute_decl
 *<li>color_attribute_decl
 *<li>style_attributes_decl
 *</ul>
 *</b>
 */
public interface Itext_attribute_decl
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


