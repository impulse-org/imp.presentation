package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 47:  token_decl_attributes ::= $Empty
 *<li>Rule 48:  token_decl_attributes ::= token_decl_attributes text_attribute_decl
 *</b>
 */
public class text_attribute_declList extends AbstractASTNodeList implements Itoken_decl_attributes
{
    public Itext_attribute_decl gettext_attribute_declAt(int i) { return (Itext_attribute_decl) getElementAt(i); }

    public text_attribute_declList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public text_attribute_declList(Itext_attribute_decl _text_attribute_decl, boolean leftRecursive)
    {
        super((ASTNode) _text_attribute_decl, leftRecursive);
        ((ASTNode) _text_attribute_decl).setParent(this);
    }

    public void add(Itext_attribute_decl _text_attribute_decl)
    {
        super.add((ASTNode) _text_attribute_decl);
        ((ASTNode) _text_attribute_decl).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof text_attribute_declList)) return false;
        text_attribute_declList other = (text_attribute_declList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            Itext_attribute_decl element = gettext_attribute_declAt(i);
            if (! element.equals(other.gettext_attribute_declAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (gettext_attribute_declAt(i).hashCode());
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
                Itext_attribute_decl element = gettext_attribute_declAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


