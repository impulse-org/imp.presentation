package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 3:  importSpecs ::= $Empty
 *<li>Rule 4:  importSpecs ::= importSpecs importSpec
 *</b>
 */
public class importSpecList extends AbstractASTNodeList implements IimportSpecs
{
    public importSpec getimportSpecAt(int i) { return (importSpec) getElementAt(i); }

    public importSpecList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public importSpecList(importSpec _importSpec, boolean leftRecursive)
    {
        super((ASTNode) _importSpec, leftRecursive);
        ((ASTNode) _importSpec).setParent(this);
    }

    public void add(importSpec _importSpec)
    {
        super.add((ASTNode) _importSpec);
        ((ASTNode) _importSpec).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof importSpecList)) return false;
        if (! super.equals(o)) return false;
        importSpecList other = (importSpecList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            importSpec element = getimportSpecAt(i);
            if (! element.equals(other.getimportSpecAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getimportSpecAt(i).hashCode());
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
                importSpec element = getimportSpecAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


