package org.eclipse.imp.presentation.outliner;

import org.eclipse.imp.presentation.parser.Ast.*;
import org.eclipse.imp.services.base.OutlinerBase;


/**
 * This file provides a skeletal implementation of the language-dependent aspects
 * of a source-program outliner.  This implementation is generated from a template
 * that is parameterized with respect to the name of the language, the package
 * containing the language-specific types for AST nodes and AbstractVisitors, and
 * the name of the outliner package and class.
 * 
 * @author suttons@us.ibm.com
 *
 */
public class ImppOutliner extends OutlinerBase {
    /*
     * A visitor for ASTs.  Its purpose is to create outline-tree items
     * for AST node types that are to appear in the outline view.
     */
    private final class OutlineVisitor extends AbstractVisitor {

	public OutlineVisitor() {
	    super();
	    // TODO:  Replace the language name here with the name of your
	    // own language (provided that your language isn't called "Leg"
	    // and you want a heading like this at the top of your outline)
	    pushTopItem("impp program", null);
	}

	public void unimplementedVisitor(String s) {
	    // Sometimes useful for debugging
	    //System.out.println(s);
	}

	// START_HERE
	// Include visit(..) and endVisit(..) methods for each AST node
	// type that is to appear in the outline tree.  Typically, the
	// visit(..) method is used to create an outline item and to
	// push it onto a stack that contains the current ancestor items
	// (the top member of which will be the parent of the current item).
	// The  endVisit(..) method is used to pop items from the stack.
	// To aid in the implementation of these methods, methods to push
	// and pop top-level and sub-level outline items are provided.  The
	// method addSubItem(..) allows you to add an outline item at the
	// current level of indentation without changing the level.  Some
	// examples follow ...

	public boolean visit(presentation_declaration n) {
	    pushSubItem(n.getname().toString(), n);
	    return true;
	}

	public void endVisit(presentation_declaration n) {
	    popSubItem();
	}

	public boolean visit(tree_presentation_spec n) {
	    pushSubItem("tree " + n.getname().toString(), n);
	    return true;
	}

	@Override
	public void endVisit(tree_presentation_spec n) {
	    popSubItem();
	}

	public boolean visit(text_presentation_spec n) {
	    pushSubItem("text " + n.getname().toString(), n);
	    return true;
	}

	@Override
	public void endVisit(text_presentation_spec n) {
	    popSubItem();
	}

	@Override
	public boolean visit(outline_decl n) {
	    pushSubItem("outline " + n.getid().toString(), n);
	    return true;
	}

	@Override
	public void endVisit(outline_decl n) {
	    popSubItem();
	}

	@Override
	public boolean visit(editor_decl n) {
	    pushSubItem("editor " + n.getid().toString(), n);
	    return true;
	}

	@Override
	public void endVisit(editor_decl n) {
	    popSubItem();
	}

//	public boolean visit(assignmentStmt n) {
//	    addSubItem(n.getidentifier().toString() + "=" + n.getexpression().toString(), n);
//	    return true;
//	}
    }

    protected void sendVisitorToAST(Object node) {
	ASTNode root= (ASTNode) node;
	root.accept(new OutlineVisitor());
    }

}
