/*******************************************************************************
* Copyright (c) 2009 IBM Corporation.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation
*******************************************************************************/

package org.eclipse.imp.presentation.parser;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.parser.IParseController;
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
        fLexer= new PSPLexer();
        fParser= new PSPParser();
    }

    public ILanguageSyntaxProperties getSyntaxProperties() {
        return new PSPSyntaxProperties();
    }

    public Object parse(String contents, IProgressMonitor monitor) {
        super.parse(contents, monitor);

        ((PSPParser) fParser).resolve((ASTNode) fCurrentAst);
        return fCurrentAst;
    }

    // public List<IParseFragment> getEmbeddedFragments() {
    //     return Collections.emptyList();
    // }
}
