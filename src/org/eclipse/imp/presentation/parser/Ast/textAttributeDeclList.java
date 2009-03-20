package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 51:  tokenDeclAttributes ::= $Empty
 *<li>Rule 52:  tokenDeclAttributes ::= tokenDeclAttributes textAttributeDecl
 *</b>
 */
public class textAttributeDeclList extends AbstractASTNodeList implements ItokenDeclAttributes
{
    public ItextAttributeDecl gettextAttributeDeclAt(int i) { return (ItextAttributeDecl) getElementAt(i); }

    public textAttributeDeclList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public textAttributeDeclList(ItextAttributeDecl _textAttributeDecl, boolean leftRecursive)
    {
        super((ASTNode) _textAttributeDecl, leftRecursive);
        ((ASTNode) _textAttributeDecl).setParent(this);
    }

    public void add(ItextAttributeDecl _textAttributeDecl)
    {
        super.add((ASTNode) _textAttributeDecl);
        ((ASTNode) _textAttributeDecl).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof textAttributeDeclList)) return false;
        if (! super.equals(o)) return false;
        textAttributeDeclList other = (textAttributeDeclList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            ItextAttributeDecl element = gettextAttributeDeclAt(i);
            if (! element.equals(other.gettextAttributeDeclAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (gettextAttributeDeclAt(i).hashCode());
        return hash;
    }

    public void accept(IAstVisitor v)
    {
        if (! v.preVisit(this)) return;
        enter((Visitor) v);
        v.postVisit(this);
    }
    public void enter(Visitor v)
    {
        boolean checkChildren = v.visit(this);
        if (checkChildren)
        {
            for (int i = 0; i < size(); i++)
            {
                ItextAttributeDecl element = gettextAttributeDeclAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


