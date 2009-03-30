package org.eclipse.imp.presentation.builders;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.builder.BuilderBase;
import org.eclipse.imp.language.Language;
import org.eclipse.imp.language.LanguageRegistry;
import org.eclipse.imp.presentation.PSPActivator;
import org.eclipse.imp.presentation.compiler.PSPCompiler;
import org.eclipse.imp.runtime.PluginBase;

/**
 * @author
 */
public class PSPBuilder extends BuilderBase {
    /**
     * Extension ID of the PSP builder. Must match the ID in the corresponding extension definition in plugin.xml.
     */
    public static final String BUILDER_ID= PSPActivator.kPluginID + ".builder";

    public static final String PROBLEM_MARKER_ID= PSPActivator.kPluginID + ".problem";

    public static final String LANGUAGE_NAME= "psp";

    public static final Language LANGUAGE= LanguageRegistry.findLanguage(LANGUAGE_NAME);

    public static final Collection<String> EXTENSIONS= LANGUAGE.getFilenameExtensions();

    protected PluginBase getPlugin() {
        return PSPActivator.getInstance();
    }

    protected String getErrorMarkerID() {
        return PROBLEM_MARKER_ID;
    }

    protected String getWarningMarkerID() {
        return PROBLEM_MARKER_ID;
    }

    protected String getInfoMarkerID() {
        return PROBLEM_MARKER_ID;
    }

    protected boolean isSourceFile(IFile file) {
        IPath path= file.getRawLocation();
        if (path == null)
            return false;

        String pathString= path.toString();
        if (pathString.indexOf("/bin/") != -1)
            return false;

        for(String ext : EXTENSIONS) {
            if (ext.equals(path.getFileExtension()))
                return true;
        }
        return false;
    }

    /**
     * @return true iff the given file is a source file that this builder should scan for dependencies, but not compile
     *         as a top-level compilation unit.<br>
     *         <code>isNonRootSourceFile()</code> and <code>isSourceFile()</code> should never return true for the
     *         same file.
     */
    protected boolean isNonRootSourceFile(IFile resource) {
        return false;
    }

    /**
     * Collects compilation-unit dependencies for the given file, and records them via calls to
     * <code>fDependency.addDependency()</code>.
     */
    protected void collectDependencies(IFile file) {
        return;
    }

    protected boolean isOutputFolder(IResource resource) {
        return resource.getFullPath().lastSegment().equals("bin");
    }

    protected void compile(final IFile file, IProgressMonitor monitor) {
        try {
            // START_HERE
            getConsoleStream().println("Builder.compile with file = " + file.getName());
            PSPCompiler compiler= new PSPCompiler(this, PROBLEM_MARKER_ID);
            compiler.compile(file, monitor);
            doRefresh(file.getParent());
        } catch (Exception e) {
            getPlugin().writeErrorMsg(e.getMessage());

            e.printStackTrace();
        }
    }
}
