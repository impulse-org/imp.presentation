package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<em>
 *<li>Rule 3:  import_specs ::= $Empty
 *</em>
 *<p>
 *<b>
 *<li>Rule 4:  import_specs ::= import_specs import_spec
 *</b>
 */
public class import_specs extends ASTNode implements Iimport_specs
{
    private import_specs _import_specs;
    private import_spec _import_spec;

    /**
     * The value returned by <b>getimport_specs</b> may be <b>null</b>
     */
    public import_specs getimport_specs() { return _import_specs; }
    public import_spec getimport_spec() { return _import_spec; }

    public import_specs(IToken leftIToken, IToken rightIToken,
                        import_specs _import_specs,
                        import_spec _import_spec)
    {
        super(leftIToken, rightIToken);

        this._import_specs = _import_specs;
        if (_import_specs != null) ((ASTNode) _import_specs).setParent(this);
        this._import_spec = _import_spec;
        ((ASTNode) _import_spec).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_import_specs);
        list.add(_import_spec);
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
        if (! (o instanceof import_specs)) return false;
        import_specs other = (import_specs) o;
        if (_import_specs == null)
            if (other._import_specs != null) return false;
            else; // continue
        else if (! _import_specs.equals(other._import_specs)) return false;
        if (! _import_spec.equals(other._import_spec)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_import_specs == null ? 0 : _import_specs.hashCode());
        hash = hash * 31 + (_import_spec.hashCode());
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
            if (_import_specs != null) _import_specs.accept(v);
            _import_spec.accept(v);
        }
        v.endVisit(this);
    }
}


