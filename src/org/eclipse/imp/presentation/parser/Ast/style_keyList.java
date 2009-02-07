package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 38:  style_list ::= style_key
 *<li>Rule 39:  style_list ::= style_list style_key
 *</b>
 */
public class style_keyList extends AbstractASTNodeList implements Istyle_list
{
    public Istyle_key getstyle_keyAt(int i) { return (Istyle_key) getElementAt(i); }

    public style_keyList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public style_keyList(Istyle_key _style_key, boolean leftRecursive)
    {
        super((ASTNode) _style_key, leftRecursive);
        ((ASTNode) _style_key).setParent(this);
    }

    public void add(Istyle_key _style_key)
    {
        super.add((ASTNode) _style_key);
        ((ASTNode) _style_key).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof style_keyList)) return false;
        style_keyList other = (style_keyList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            Istyle_key element = getstyle_keyAt(i);
            if (! element.equals(other.getstyle_keyAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getstyle_keyAt(i).hashCode());
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
                Istyle_key element = getstyle_keyAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


