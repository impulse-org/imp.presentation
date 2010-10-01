package org.eclipse.imp.presentation.parser;

import org.eclipse.imp.services.base.LanguageSyntaxPropertiesBase;

public class PSPSyntaxProperties extends LanguageSyntaxPropertiesBase {
    public String getSingleLineCommentPrefix() {
        return "//";
    }

    public String getBlockCommentContinuation() {
        return null;
    }

    public String getBlockCommentEnd() {
        return null;
    }

    public String getBlockCommentStart() {
        return null;
    }
}
