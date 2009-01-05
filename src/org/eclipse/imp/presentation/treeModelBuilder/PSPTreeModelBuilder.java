package org.eclipse.imp.presentation.treeModelBuilder;

import org.eclipse.imp.services.base.TreeModelBuilderBase;

import org.eclipse.imp.presentation.parser.Ast.*;


public class PSPTreeModelBuilder extends TreeModelBuilderBase
{
    @Override
    public void visitTree(Object root) {
        if (root == null)
            return;
        ASTNode rootNode= (ASTNode) root;
        PSPModelVisitor visitor= new PSPModelVisitor();

        rootNode.accept(visitor);
    }

    private class PSPModelVisitor extends AbstractVisitor {
        @Override
        public void unimplementedVisitor(String s) { }

        public boolean visit(languageSpec n) {
            pushSubItem(n);
            return true;
        }

        public void endVisit(languageSpec n) {
            popSubItem();
        }

        public boolean visit(presentationSpec n) {
            pushSubItem(n);
            return true;
        }

        public void endVisit(presentationSpec n) {
            popSubItem();
        }

        public boolean visit(nodePresentation n) {
            createSubItem(n);
            return true;
        }

        public boolean visit(textColoringSpec n) {
            pushSubItem(n);
            return true;
        }

        public void endVisit(textColoringSpec n) {
            popSubItem();
        }

        @Override
        public boolean visit(foldingSpec n) {
            pushSubItem(n);
            return true;
        }

        @Override
        public void endVisit(foldingSpec n) {
            popSubItem();
        }

        @Override
        public boolean visit(foldableNode n) {
            createSubItem(n);
            return false;
        }

        @Override
        public boolean visit(tokenDecl n) {
            createSubItem(n);
            return true;
        }
        
        @Override
        public boolean visit(resourceDecl n) {
            createSubItem(n);
            return true;
        }
    }
}
