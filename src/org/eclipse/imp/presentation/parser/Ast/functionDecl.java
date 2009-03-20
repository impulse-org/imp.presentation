package org.eclipse.imp.presentation.parser.Ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 28:  functionDecl ::= primitiveType IDENTIFIER$name ($ formalArgList )$ functionBody
 *</b>
 */
public class functionDecl extends ASTNode implements IfunctionDecl
{
    private IprimitiveType _primitiveType;
    private ASTNodeToken _name;
    private IformalArgList _formalArgList;
    private functionBody _functionBody;

    public IprimitiveType getprimitiveType() { return _primitiveType; }
    public ASTNodeToken getname() { return _name; }
    public IformalArgList getformalArgList() { return _formalArgList; }
    public functionBody getfunctionBody() { return _functionBody; }

    public functionDecl(IToken leftIToken, IToken rightIToken,
                        IprimitiveType _primitiveType,
                        ASTNodeToken _name,
                        IformalArgList _formalArgList,
                        functionBody _functionBody)
    {
        super(leftIToken, rightIToken);

        this._primitiveType = _primitiveType;
        ((ASTNode) _primitiveType).setParent(this);
        this._name = _name;
        ((ASTNode) _name).setParent(this);
        this._formalArgList = _formalArgList;
        ((ASTNode) _formalArgList).setParent(this);
        this._functionBody = _functionBody;
        ((ASTNode) _functionBody).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_primitiveType);
        list.add(_name);
        list.add(_formalArgList);
        list.add(_functionBody);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof functionDecl)) return false;
        if (! super.equals(o)) return false;
        functionDecl other = (functionDecl) o;
        if (! _primitiveType.equals(other._primitiveType)) return false;
        if (! _name.equals(other._name)) return false;
        if (! _formalArgList.equals(other._formalArgList)) return false;
        if (! _functionBody.equals(other._functionBody)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_primitiveType.hashCode());
        hash = hash * 31 + (_name.hashCode());
        hash = hash * 31 + (_formalArgList.hashCode());
        hash = hash * 31 + (_functionBody.hashCode());
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
            _primitiveType.accept(v);
            _name.accept(v);
            _formalArgList.accept(v);
            _functionBody.accept(v);
        }
        v.endVisit(this);
    }
}


