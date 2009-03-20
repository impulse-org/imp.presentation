package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 10:  languageSpecList ::= languageSpec
 *<li>Rule 11:  languageSpecList ::= languageSpecList languageSpec
 *</b>
 */
public class languageSpecList extends AbstractASTNodeList implements IlanguageSpecList
{
    public languageSpec getlanguageSpecAt(int i) { return (languageSpec) getElementAt(i); }

    public languageSpecList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public languageSpecList(languageSpec _languageSpec, boolean leftRecursive)
    {
        super((ASTNode) _languageSpec, leftRecursive);
        ((ASTNode) _languageSpec).setParent(this);
    }

    public void add(languageSpec _languageSpec)
    {
        super.add((ASTNode) _languageSpec);
        ((ASTNode) _languageSpec).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof languageSpecList)) return false;
        if (! super.equals(o)) return false;
        languageSpecList other = (languageSpecList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            languageSpec element = getlanguageSpecAt(i);
            if (! element.equals(other.getlanguageSpecAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getlanguageSpecAt(i).hashCode());
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
                languageSpec element = getlanguageSpecAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


