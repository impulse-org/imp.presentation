package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 42:  styleList ::= styleKey
 *<li>Rule 43:  styleList ::= styleList ,$ styleKey
 *</b>
 */
public class styleKeyList extends AbstractASTNodeList implements IstyleList
{
    public IstyleKey getstyleKeyAt(int i) { return (IstyleKey) getElementAt(i); }

    public styleKeyList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public styleKeyList(IstyleKey _styleKey, boolean leftRecursive)
    {
        super((ASTNode) _styleKey, leftRecursive);
        ((ASTNode) _styleKey).setParent(this);
    }

    public void add(IstyleKey _styleKey)
    {
        super.add((ASTNode) _styleKey);
        ((ASTNode) _styleKey).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof styleKeyList)) return false;
        if (! super.equals(o)) return false;
        styleKeyList other = (styleKeyList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            IstyleKey element = getstyleKeyAt(i);
            if (! element.equals(other.getstyleKeyAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getstyleKeyAt(i).hashCode());
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
                IstyleKey element = getstyleKeyAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


