package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 * is implemented by <b>presentation_body</b>
 */
public interface Ipresentation_body
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


