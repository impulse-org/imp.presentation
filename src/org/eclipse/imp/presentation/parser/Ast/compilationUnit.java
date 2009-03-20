package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 1:  compilationUnit ::= packageSpec importSpecs languageSpecList
 *</b>
 */
public class compilationUnit extends ASTNode implements IcompilationUnit
{
    private packageSpec _packageSpec;
    private importSpecList _importSpecs;
    private languageSpecList _languageSpecList;

    public packageSpec getpackageSpec() { return _packageSpec; }
    public importSpecList getimportSpecs() { return _importSpecs; }
    public languageSpecList getlanguageSpecList() { return _languageSpecList; }

    public compilationUnit(IToken leftIToken, IToken rightIToken,
                           packageSpec _packageSpec,
                           importSpecList _importSpecs,
                           languageSpecList _languageSpecList)
    {
        super(leftIToken, rightIToken);

        this._packageSpec = _packageSpec;
        ((ASTNode) _packageSpec).setParent(this);
        this._importSpecs = _importSpecs;
        ((ASTNode) _importSpecs).setParent(this);
        this._languageSpecList = _languageSpecList;
        ((ASTNode) _languageSpecList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_packageSpec);
        list.add(_importSpecs);
        list.add(_languageSpecList);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof compilationUnit)) return false;
        if (! super.equals(o)) return false;
        compilationUnit other = (compilationUnit) o;
        if (! _packageSpec.equals(other._packageSpec)) return false;
        if (! _importSpecs.equals(other._importSpecs)) return false;
        if (! _languageSpecList.equals(other._languageSpecList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_packageSpec.hashCode());
        hash = hash * 31 + (_importSpecs.hashCode());
        hash = hash * 31 + (_languageSpecList.hashCode());
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
            _packageSpec.accept(v);
            _importSpecs.accept(v);
            _languageSpecList.accept(v);
        }
        v.endVisit(this);
    }
}


