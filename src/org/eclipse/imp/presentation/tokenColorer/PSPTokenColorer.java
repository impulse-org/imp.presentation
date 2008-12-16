package org.eclipse.imp.presentation.tokenColorer;

import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.presentation.parser.PSPParseController;
import org.eclipse.imp.presentation.parser.PSPParsersym;
import org.eclipse.imp.services.ITokenColorer;
import org.eclipse.imp.services.base.TokenColorerBase;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import lpg.runtime.IToken;

public class PSPTokenColorer extends TokenColorerBase implements PSPParsersym, ITokenColorer {
    private TextAttribute commentAttribute, keywordAttribute, stringAttribute, numberAttribute, identifierAttribute;

    public TextAttribute getColoring(IParseController controller, Object o) {
        IToken token= (IToken) o;
        switch (token.getKind()) {
        // START_HERE
        case TK_SINGLE_LINE_COMMENT:
            return commentAttribute;
        case TK_IDENTIFIER:
            return identifierAttribute;
        case TK_INTEGER:
            return numberAttribute;
        case TK_STRING_LITERAL:
            return stringAttribute;
        default:
            if (((PSPParseController) controller).isKeyword(token.getKind()))
                return keywordAttribute;
            return super.getColoring(controller, token);
        }
    }

    public PSPTokenColorer() {
        super();
        Display display= Display.getDefault();
        commentAttribute= new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_RED), null, SWT.ITALIC);
        stringAttribute= new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_BLUE), null, SWT.BOLD);
        identifierAttribute= new TextAttribute(display.getSystemColor(SWT.COLOR_BLACK), null, SWT.NORMAL);
        numberAttribute= new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_YELLOW), null, SWT.BOLD);
        keywordAttribute= new TextAttribute(display.getSystemColor(SWT.COLOR_DARK_MAGENTA), null, SWT.BOLD);
    }

    public IRegion calculateDamageExtent(IRegion seed) {
        return seed;
    }
}
