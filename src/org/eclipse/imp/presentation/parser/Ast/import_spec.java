package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 5:  import_spec ::= IMPORT$ qualified_name ;$
 *</b>
 */
public class import_spec extends ASTNode implements Iimport_spec
{
    private simple_nameList _qualified_name;

    public simple_nameList getqualified_name() { return _qualified_name; }

    public import_spec(IToken leftIToken, IToken rightIToken,
                       simple_nameList _qualified_name)
    {
        super(leftIToken, rightIToken);

        this._qualified_name = _qualified_name;
        ((ASTNode) _qualified_name).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_qualified_name);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        //
        // The super call test is not required for now because an Ast node
        // can only extend the root Ast, AstToken and AstList and none of
        // these nodes contain additional children.
        //
        // if (! super.equals(o)) return false;
        //
        if (! (o instanceof import_spec)) return false;
        import_spec other = (import_spec) o;
        if (! _qualified_name.equals(other._qualified_name)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_qualified_name.hashCode());
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
            _qualified_name.accept(v);
        v.endVisit(this);
    }
}


