package org.eclipse.imp.presentation.parser;

import java.io.IOException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.parser.IMessageHandler;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.parser.MessageHandlerAdapter;
import org.eclipse.imp.parser.SimpleLPGParseController;
import org.eclipse.imp.presentation.PSPActivator;
import org.eclipse.imp.presentation.parser.Ast.ASTNode;
import org.eclipse.imp.services.ILanguageSyntaxProperties;

/**
 * @author Stan Sutton (suttons@us.ibm.com) (for the following modifications)
 * @since May 1, 2007 Addition of marker types
 * @since May 10, 2007 Conversion IProject -> ISourceProject
 * @since May 31, 2007 Adapted to extend SimpleLPGParseController
 */
public class PSPParseController extends SimpleLPGParseController implements IParseController {
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

        fParser.getIPrsStream().setMessageHandler(new MessageHandlerAdapter(handler));
    }

    public ILanguageSyntaxProperties getSyntaxProperties() {
        return new PSPSyntaxProperties();
    }

    private void createLexerAndParser(IPath filePath) {
        try {
            fLexer= new PSPLexer(filePath.toOSString());
            fParser= new PSPParser(fLexer.getILexStream() /* , project */);
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public Object parse(String contents, IProgressMonitor monitor) {
        PMMonitor my_monitor= new PMMonitor(monitor);
        char[] contentsArray= contents.toCharArray();

        fLexer.reset(contentsArray, fFilePath.toPortableString());
        fParser.getIPrsStream().resetTokenStream();

        fLexer.lexer(my_monitor, fParser.getIPrsStream()); // Lex the stream to produce the token stream
        if (my_monitor.isCancelled())
            return fCurrentAst; // TODO fCurrentAst might (probably will) be inconsistent wrt the lex stream now

        fCurrentAst= (ASTNode) fParser.parser(my_monitor, 0);
        ((PSPParser) fParser).resolve((ASTNode) fCurrentAst);

        cacheKeywordsOnce();

        return fCurrentAst;
    }

    // public List<IParseFragment> getEmbeddedFragments() {
    //     return Collections.emptyList();
    // }
}
