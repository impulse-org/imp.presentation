package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 * is implemented by:
 *<b>
 *<ul>
 *<li>presentation_members
 *<li>resource_decl
 *<li>text_presentation_spec
 *<li>tree_presentation_spec
 *<li>outline_decl
 *<li>editor_decl
 *</ul>
 *</b>
 */
public interface Ipresentation_members
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


