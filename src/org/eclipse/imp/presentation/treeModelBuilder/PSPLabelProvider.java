/*
 * (C) Copyright IBM Corporation 2007
 * 
 * This file is part of the Eclipse IMP.
 */
/*
 * Created on Jul 7, 2006
 */
package org.eclipse.imp.presentation.treeModelBuilder;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.imp.editor.ModelTreeNode;
import org.eclipse.imp.services.ILabelProvider;
import org.eclipse.imp.language.ILanguageService;
import org.eclipse.imp.presentation.PSPActivator;
import org.eclipse.imp.presentation.IPSPResources;
import org.eclipse.imp.utils.MarkerUtils;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import org.eclipse.imp.presentation.parser.Ast.*;

public class PSPLabelProvider implements ILabelProvider
{
    private Set<ILabelProviderListener> fListeners= new HashSet<ILabelProviderListener>();

    private static ImageRegistry sImageRegistry= PSPActivator.getInstance().getImageRegistry();
    
    private static Image DEFAULT_IMAGE = sImageRegistry.get(IPSPResources.IMPP_DEFAULT_IMAGE);
    private static Image FILE_IMAGE = sImageRegistry.get(IPSPResources.IMPP_FILE);
    private static Image FILE_WITH_WARNING_IMAGE = sImageRegistry.get(IPSPResources.IMPP_FILE_WARNING);
    private static Image FILE_WITH_ERROR_IMAGE = sImageRegistry.get(IPSPResources.IMPP_FILE_ERROR);
    

    public Image getImage(Object element) {
        if (element instanceof IFile) {
            // TODO:  rewrite to provide more appropriate images
            IFile file= (IFile) element;
            int sev= MarkerUtils.getMaxProblemMarkerSeverity(file, IResource.DEPTH_ONE);
    
            switch(sev) {
            case IMarker.SEVERITY_ERROR: return FILE_WITH_ERROR_IMAGE;
            case IMarker.SEVERITY_WARNING: return FILE_WITH_WARNING_IMAGE;
            default:
            return FILE_IMAGE;
            }
        }
        ASTNode n= (element instanceof ModelTreeNode) ?
            (ASTNode) ((ModelTreeNode) element).getASTNode() : (ASTNode) element;
        return getImageFor(n);
    }

    public static Image getImageFor(ASTNode n) {
        // TODO:  return specific images for specific node
        // types, as images are available and appropriate
        return DEFAULT_IMAGE;
    }

    public String getText(Object element) {
        ASTNode n= (element instanceof ModelTreeNode) ?
            (ASTNode) ((ModelTreeNode) element).getASTNode() :
            (ASTNode) element;

        return getLabelFor(n);
    }

    public static String getLabelFor(ASTNode n) {
        if (n instanceof IcompilationUnit)
            return "Compilation unit";
        if (n instanceof languageSpec) {
            languageSpec spec= (languageSpec) n;
            return spec.getlangName().toString();
        }
        if (n instanceof presentationSpec) {
            return "presentation";
        }
        if (n instanceof textColoringSpec) {
            return "text coloring";
        }
        if (n instanceof outlineSpec) {
            return "outline";
        }
        if (n instanceof presentationMember) {
            return ((presentationMember) n).gettype().toString();
        }
        if (n instanceof foldingSpec) {
            return "foldable";
        }
        if (n instanceof foldableNode) {
            return ((foldableNode) n).getnodeName().toString();
        }
        if (n instanceof tokenDecl) {
            return ((tokenDecl) n).getname().toString();
        }
        if (n instanceof resourceDecl) {
            resourceDecl rd= (resourceDecl) n;
            return rd.getprimitiveType().toString() + " " + (rd).getident().toString();
        }
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
