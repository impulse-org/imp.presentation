package org.eclipse.imp.presentation.parser;

import org.eclipse.imp.services.ILanguageSyntaxProperties;

public class ImppSyntaxProperties implements ILanguageSyntaxProperties {

    public String getBlockCommentEnd() {
        return null;
    }

    public String getBlockCommentStart() {
        return null;
    }

    public String getSingleLineCommentPrefix() {
        return "//";
    }

    public String getBlockCommentContinuation() {
        // TODO Auto-generated method stub
        return null;
    }

    public String[][] getFences() {
        // TODO Auto-generated method stub
        return null;
    }

    public int[] getIdentifierComponents(String ident) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getIdentifierConstituentChars() {
        // TODO Auto-generated method stub
        return null;
    }

}
