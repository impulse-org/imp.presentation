package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 6:  qualified_name ::= simple_name
 *<li>Rule 7:  qualified_name ::= qualified_name .$ simple_name
 *</b>
 */
public class simple_nameList extends AbstractASTNodeList implements Iqualified_name
{
    public Isimple_name getsimple_nameAt(int i) { return (Isimple_name) getElementAt(i); }

    public simple_nameList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public simple_nameList(Isimple_name _simple_name, boolean leftRecursive)
    {
        super((ASTNode) _simple_name, leftRecursive);
        ((ASTNode) _simple_name).setParent(this);
    }

    public void add(Isimple_name _simple_name)
    {
        super.add((ASTNode) _simple_name);
        ((ASTNode) _simple_name).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof simple_nameList)) return false;
        simple_nameList other = (simple_nameList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            Isimple_name element = getsimple_nameAt(i);
            if (! element.equals(other.getsimple_nameAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getsimple_nameAt(i).hashCode());
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
                Isimple_name element = getsimple_nameAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


