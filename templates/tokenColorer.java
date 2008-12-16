package $PACKAGE_NAME$;

import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.parser.SimpleLPGParseController;
import org.eclipse.imp.services.ITokenColorer;
import org.eclipse.imp.services.base.TokenColorerBase;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import $PARSER_PKG$.$PARSER_SYMBOL_CLASS_NAME$;

import lpg.runtime.IToken;

/**
 * !!!!!!!!!!!!!!!!!!!!!!
 * !!! GENERATED FILE !!!
 * !!! DO NOT EDIT    !!!
 * !!!!!!!!!!!!!!!!!!!!!!
 * 
 * This file provides an implementation of the language-dependent aspects of a
 * source-text colorer for $LANG_NAME$. This implementation was generated from
 * specification file $SPEC_NAME$ at $DATE_TIME$.
 */
public class $COLORER_CLASS_NAME$ extends TokenColorerBase implements $PARSER_SYMBOL_CLASS_NAME$, ITokenColorer {
    private final TextAttribute $ATTRIBUTE_FIELD_NAMES$;

    public $COLORER_CLASS_NAME$() {
        super();
        // NOTE:  Colors (i.e., instances of org.eclipse.swt.graphics.Color) are system resources
        // and are limited in number.  THEREFORE, it is good practice to reuse existing system Colors
        // or to allocate a fixed set of new Colors and reuse those.  If new Colors are instantiated
        // beyond the bounds of your system capacity then your Eclipse invocation may cease to function
        // properly or at all.
        Display display = Display.getDefault();
        $ATTRIBUTE_INITIALIZERS$
    }
    
    public TextAttribute getColoring(IParseController controller, Object o) {
        if (o == null)
            return null;
        IToken token= (IToken) o;
        if (token.getKind() == TK_EOF_TOKEN)
            return null;
        
        switch (token.getKind()) {
        $TOKEN_CASES$
        default:
            if (((SimpleLPGParseController) controller).isKeyword(token.getKind()))
                return keywordAttribute;
            return super.getColoring(controller, token);
        }
    }

    public IRegion calculateDamageExtent(IRegion seed) {
        return seed;
    }
}
