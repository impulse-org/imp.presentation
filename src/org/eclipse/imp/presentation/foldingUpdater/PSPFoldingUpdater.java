package org.eclipse.imp.presentation.foldingUpdater;

import java.util.HashMap;
import java.util.List;

import org.eclipse.imp.presentation.parser.Ast.ASTNode;
import org.eclipse.imp.presentation.parser.Ast.AbstractVisitor;
import org.eclipse.imp.presentation.parser.Ast.foldingSpec;
import org.eclipse.imp.presentation.parser.Ast.functionDecl;
import org.eclipse.imp.presentation.parser.Ast.languageSpec;
import org.eclipse.imp.presentation.parser.Ast.outlineSpec;
import org.eclipse.imp.presentation.parser.Ast.presentationSpec;
import org.eclipse.imp.presentation.parser.Ast.textColoringSpec;
import org.eclipse.imp.services.base.LPGFolderBase;

/**
 * 
 */
public class PSPFoldingUpdater extends LPGFolderBase {
    /*
     * A visitor for ASTs. Its purpose is to create ProjectionAnnotations for regions of text corresponding to various
     * types of AST node or to text ranges computed from AST nodes. Projection annotations appear in the editor as the
     * widgets that control folding.
     */
    private class FoldingVisitor extends AbstractVisitor {
        public void unimplementedVisitor(String s) { }

        // START_HERE
        //
        // Include visit(..) functions for various types of AST nodes that are
        // associated with folding. These functions should call one of the two
        // versions of makeAnnotation(..) that are defined in FolderBase. The
        // usual case is to call the version of makeAnnotation that creates a
        // folding annotation corresponding to the extent of a particular AST node.
        // The other possibility is to create an annotation with an extent that
        // is explicitly provided. An example is shown below ...

        // Create annotations for the folding of blocks (for example)
        @Override
        public boolean visit(languageSpec n) {
            makeAnnotation(n);
            return true;
        }

        @Override
        public boolean visit(presentationSpec n) {
            makeAnnotation(n);
            return true;
        }

        @Override
        public boolean visit(textColoringSpec n) {
            makeAnnotation(n);
            return true;
        }

        @Override
        public boolean visit(outlineSpec n) {
            makeAnnotation(n);
            return true;
        }

        @Override
        public boolean visit(foldingSpec n) {
            makeAnnotation(n);
            return true;
        }

        @Override
        public boolean visit(functionDecl n) {
            makeAnnotation(n);
            return true;
        }
    };

    // When instantiated will provide a concrete implementation of an abstract method
    // defined in FolderBase
    public void sendVisitorToAST(HashMap newAnnotations, List annotations, Object ast) {
        ASTNode theAST= (ASTNode) ast;
        prsStream= theAST.getLeftIToken().getPrsStream();
        AbstractVisitor abstractVisitor= new FoldingVisitor();
        theAST.accept(abstractVisitor);
        makeAdjunctAnnotations();
    }
}
