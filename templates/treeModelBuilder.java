package $PACKAGE_NAME$;

import org.eclipse.imp.services.base.TreeModelBuilderBase;

$EXTRA_IMPORTS$

/**
 * !!!!!!!!!!!!!!!!!!!!!!
 * !!! GENERATED FILE !!!
 * !!! DO NOT EDIT    !!!
 * !!!!!!!!!!!!!!!!!!!!!!
 * 
 * This file provides an implementation of the language-dependent aspects of a
 * tree model builder for $LANG_NAME$. This implementation was generated from
 * specification file $SPEC_NAME$ at $DATE_TIME$.
 */
public class $TREE_MODEL_BUILDER_CLASS_NAME$ extends TreeModelBuilderBase
{
    @Override
    public void visitTree(Object root) {
        if (root == null)
            return;
        ASTNode rootNode= (ASTNode) root;
        ModelVisitor visitor= new ModelVisitor();

        rootNode.accept(visitor);
    }

    private class ModelVisitor extends AbstractVisitor {
        @Override
        public void unimplementedVisitor(String s) { }

        $VISITOR_METHODS$
    }
}
