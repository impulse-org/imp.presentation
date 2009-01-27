package org.eclipse.imp.presentation.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.parser.ILexer;
import org.eclipse.imp.parser.IMessageHandler;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.parser.IParser;
import org.eclipse.imp.parser.ISourcePositionLocator;
import org.eclipse.imp.parser.MessageHandlerAdapter;
import org.eclipse.imp.parser.SimpleLPGParseController;
import org.eclipse.imp.presentation.PSPActivator;
import org.eclipse.imp.presentation.parser.Ast.ASTNode;
import org.eclipse.imp.services.ILanguageSyntaxProperties;
import org.eclipse.imp.utils.StreamUtils;

/**
 * @author Stan Sutton (suttons@us.ibm.com) (for the following modifications)
 * @since May 1, 2007 Addition of marker types
 * @since May 10, 2007 Conversion IProject -> ISourceProject
 * @since May 31, 2007 Adapted to extend SimpleLPGParseController
 */
public class PSPParseController extends SimpleLPGParseController implements IParseController {
    private PSPParser parser;

    private PSPLexer lexer;

    public PSPParseController() {
        super(PSPActivator.kLanguageName);
    }

    /**
     * @param filePath
     *            Project-relative path of file
     * @param project
     *            Project that contains the file
     * @param handler
     *            A message handler to receive error messages (or any others) from the parser
     */
    public void initialize(IPath filePath, ISourceProject project, IMessageHandler handler) {
        super.initialize(filePath, project, handler);
        IPath fullFilePath= project.getRawProject().getLocation().append(filePath);
        createLexerAndParser(fullFilePath);

        parser.getIPrsStream().setMessageHandler(new MessageHandlerAdapter(handler));
    }

    public IParser getParser() {
        return parser;
    }

    public ILexer getLexer() {
        return lexer;
    }

    public ISourcePositionLocator getNodeLocator() {
        return new PSPASTNodeLocator();
    }

    public ILanguageSyntaxProperties getSyntaxProperties() {
        return new PSPSyntaxProperties();
    }

    private void createLexerAndParser(IPath filePath) {
        try {
            lexer= new PSPLexer(filePath.toOSString()); // Create the lexer
            parser= new PSPParser(lexer.getLexStream() /* , project */); // Create the parser
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public Object parse(IProgressMonitor monitor) {
        try {
            File file= new File(fProject.getRawProject().getLocation().append(fFilePath).toOSString());
            String source= StreamUtils.readStreamContents(new FileInputStream(file));

            return parse(source, false, monitor);
        } catch (FileNotFoundException e) {
            PSPActivator.getInstance().logException("Unable to parse input", e);
            return null;
        }
    }

    public Object parse(String contents, boolean scanOnly, IProgressMonitor monitor) {
        PMMonitor my_monitor= new PMMonitor(monitor);
        char[] contentsArray= contents.toCharArray();

        lexer.initialize(contentsArray, fFilePath.toPortableString());
        parser.getParseStream().resetTokenStream();

        lexer.lexer(my_monitor, parser.getParseStream()); // Lex the stream to produce the token stream
        if (my_monitor.isCancelled())
            return fCurrentAst; // TODO fCurrentAst might (probably will) be inconsistent wrt the lex stream now

        fCurrentAst= (ASTNode) parser.parser(my_monitor, 0);
        parser.resolve((ASTNode) fCurrentAst);

        cacheKeywordsOnce();

        return fCurrentAst;
    }

    // public List<IParseFragment> getEmbeddedFragments() {
    //     return Collections.emptyList();
    // }
}
