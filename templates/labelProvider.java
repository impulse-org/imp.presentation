package $PACKAGE_NAME$;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.imp.editor.ModelTreeNode;
import org.eclipse.imp.services.ILabelProvider;
import org.eclipse.imp.utils.MarkerUtils;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

$EXTRA_IMPORTS$

/**
 * !!!!!!!!!!!!!!!!!!!!!!
 * !!! GENERATED FILE !!!
 * !!! DO NOT EDIT    !!!
 * !!!!!!!!!!!!!!!!!!!!!!
 * 
 * This file provides an implementation of the language-dependent aspects of a
 * label provider for $LANG_NAME$. This implementation was generated from
 * specification file $SPEC_NAME$ at $DATE_TIME$.
 */
public class $LABEL_PROVIDER_CLASS_NAME$ implements ILabelProvider
{
    private Set<ILabelProviderListener> fListeners= new HashSet<ILabelProviderListener>();

    public Image getImage(Object element) {
        if (element instanceof IFile) {
            // TODO:  rewrite to provide more appropriate images
            IFile file= (IFile) element;
            int sev= MarkerUtils.getMaxProblemMarkerSeverity(file, IResource.DEPTH_ONE);
    
            switch(sev) {
            case IMarker.SEVERITY_ERROR: return $LABEL_IMAGES_CLASS_NAME$.FILE_WITH_ERROR_IMAGE;
            case IMarker.SEVERITY_WARNING: return $LABEL_IMAGES_CLASS_NAME$.FILE_WITH_WARNING_IMAGE;
            default: return $LABEL_IMAGES_CLASS_NAME$.FILE_IMAGE;
            }
        }
        ASTNode n= (element instanceof ModelTreeNode) ?
            (ASTNode) ((ModelTreeNode) element).getASTNode() : (ASTNode) element;
        return getImageFor(n);
    }

    public static Image getImageFor(ASTNode n) {
        // TODO:  return specific images for specific node
        // types, as images are available and appropriate
        $NODE_IMAGE_CASES$
        return $LABEL_IMAGES_CLASS_NAME$.DEFAULT_IMAGE;
    }

    public String getText(Object element) {
        ASTNode n= (element instanceof ModelTreeNode) ?
            (ASTNode) ((ModelTreeNode) element).getASTNode() :
            (ASTNode) element;

        return getLabelFor(n);
    }

    public static String getLabelFor(ASTNode n) {
        $NODE_LABEL_CASES$
        return "<???>";
    }

    public void addListener(ILabelProviderListener listener) {
        fListeners.add(listener);
    }

    public void dispose() {}

    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    public void removeListener(ILabelProviderListener listener) {
        fListeners.remove(listener);
    }
}
