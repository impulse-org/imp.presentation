package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 10:  presentation_list ::= presentation_declaration
 *<li>Rule 11:  presentation_list ::= presentation_list presentation_declaration
 *</b>
 */
public class presentation_declarationList extends AbstractASTNodeList implements Ipresentation_list
{
    public presentation_declaration getpresentation_declarationAt(int i) { return (presentation_declaration) getElementAt(i); }

    public presentation_declarationList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public presentation_declarationList(presentation_declaration _presentation_declaration, boolean leftRecursive)
    {
        super((ASTNode) _presentation_declaration, leftRecursive);
        ((ASTNode) _presentation_declaration).setParent(this);
    }

    public void add(presentation_declaration _presentation_declaration)
    {
        super.add((ASTNode) _presentation_declaration);
        ((ASTNode) _presentation_declaration).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof presentation_declarationList)) return false;
        presentation_declarationList other = (presentation_declarationList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            presentation_declaration element = getpresentation_declarationAt(i);
            if (! element.equals(other.getpresentation_declarationAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getpresentation_declarationAt(i).hashCode());
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
                presentation_declaration element = getpresentation_declarationAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


