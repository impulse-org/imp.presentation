package org.eclipse.imp.presentation.parser;

import org.eclipse.imp.presentation.parser.Ast.*;
import lpg.runtime.*;
import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

public class PSPParser implements RuleAction, IParser
{
    private PrsStream prsStream = null;
    
    private boolean unimplementedSymbolsWarning = false;

    private static ParseTable prsTable = new PSPParserprs();
    public ParseTable getParseTable() { return prsTable; }

    private DeterministicParser dtParser = null;
    public DeterministicParser getParser() { return dtParser; }

    private void setResult(Object object) { dtParser.setSym1(object); }
    public Object getRhsSym(int i) { return dtParser.getSym(i); }

    public int getRhsTokenIndex(int i) { return dtParser.getToken(i); }
    public IToken getRhsIToken(int i) { return prsStream.getIToken(getRhsTokenIndex(i)); }
    
    public int getRhsFirstTokenIndex(int i) { return dtParser.getFirstToken(i); }
    public IToken getRhsFirstIToken(int i) { return prsStream.getIToken(getRhsFirstTokenIndex(i)); }

    public int getRhsLastTokenIndex(int i) { return dtParser.getLastToken(i); }
    public IToken getRhsLastIToken(int i) { return prsStream.getIToken(getRhsLastTokenIndex(i)); }

    public int getLeftSpan() { return dtParser.getFirstToken(); }
    public IToken getLeftIToken()  { return prsStream.getIToken(getLeftSpan()); }

    public int getRightSpan() { return dtParser.getLastToken(); }
    public IToken getRightIToken() { return prsStream.getIToken(getRightSpan()); }

    public int getRhsErrorTokenIndex(int i)
    {
        int index = dtParser.getToken(i);
        IToken err = prsStream.getIToken(index);
        return (err instanceof ErrorToken ? index : 0);
    }
    public ErrorToken getRhsErrorIToken(int i)
    {
        int index = dtParser.getToken(i);
        IToken err = prsStream.getIToken(index);
        return (ErrorToken) (err instanceof ErrorToken ? err : null);
    }

    public void reset(ILexStream lexStream)
    {
        prsStream = new PrsStream(lexStream);
        dtParser.reset(prsStream);

        try
        {
            prsStream.remapTerminalSymbols(orderedTerminalSymbols(), prsTable.getEoftSymbol());
        }
        catch(NullExportedSymbolsException e) {
        }
        catch(NullTerminalSymbolsException e) {
        }
        catch(UnimplementedTerminalsException e)
        {
            if (unimplementedSymbolsWarning) {
                java.util.ArrayList unimplemented_symbols = e.getSymbols();
                System.out.println("The Lexer will not scan the following token(s):");
                for (int i = 0; i < unimplemented_symbols.size(); i++)
                {
                    Integer id = (Integer) unimplemented_symbols.get(i);
                    System.out.println("    " + PSPParsersym.orderedTerminalSymbols[id.intValue()]);               
                }
                System.out.println();
            }
        }
        catch(UndefinedEofSymbolException e)
        {
            throw new Error(new UndefinedEofSymbolException
                                ("The Lexer does not implement the Eof symbol " +
                                 PSPParsersym.orderedTerminalSymbols[prsTable.getEoftSymbol()]));
        }
    }
    
    public PSPParser()
    {
        try
        {
            dtParser = new DeterministicParser(prsStream, prsTable, (RuleAction) this);
        }
        catch (NotDeterministicParseTableException e)
        {
            throw new Error(new NotDeterministicParseTableException
                                ("Regenerate PSPParserprs.java with -NOBACKTRACK option"));
        }
        catch (BadParseSymFileException e)
        {
            throw new Error(new BadParseSymFileException("Bad Parser Symbol File -- PSPParsersym.java. Regenerate PSPParserprs.java"));
        }
    }

    public PSPParser(ILexStream lexStream)
    {
        this();
        reset(lexStream);
    }

    public int numTokenKinds() { return PSPParsersym.numTokenKinds; }
    public String[] orderedTerminalSymbols() { return PSPParsersym.orderedTerminalSymbols; }
    public String getTokenKindName(int kind) { return PSPParsersym.orderedTerminalSymbols[kind]; }            
    public int getEOFTokenKind() { return prsTable.getEoftSymbol(); }
    public IPrsStream getIPrsStream() { return prsStream; }

    /**
     * @deprecated replaced by {@link #getIPrsStream()}
     *
     */
    public PrsStream getPrsStream() { return prsStream; }

    /**
     * @deprecated replaced by {@link #getIPrsStream()}
     *
     */
    public PrsStream getParseStream() { return prsStream; }

    public Object parser()
    {
        return parser(null, 0);
    }
        
    public Object parser(Monitor monitor)
    {
        return parser(monitor, 0);
    }
        
    public Object parser(int error_repair_count)
    {
        return parser(null, error_repair_count);
    }
        
    public Object parser(Monitor monitor, int error_repair_count)
    {
        dtParser.setMonitor(monitor);

        try
        {
            return (Object) dtParser.parse();
        }
        catch (BadParseException e)
        {
            prsStream.reset(e.error_token); // point to error token

            DiagnoseParser diagnoseParser = new DiagnoseParser(prsStream, prsTable);
            diagnoseParser.diagnose(e.error_token);
        }

        return null;
    }

    //
    // Additional entry points, if any
    //
    

    public void resolve(ASTNode root) {
    }
 
    public void ruleAction(int ruleNumber)
    {
        switch (ruleNumber)
        {

            //
            // Rule 1:  compilationUnit ::= packageSpec importSpecs languageSpecList
            //
            case 1: {
                setResult(
                    new compilationUnit(getLeftIToken(), getRightIToken(),
                                        (packageSpec)getRhsSym(1),
                                        (importSpecList)getRhsSym(2),
                                        (languageSpecList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 2:  packageSpec ::= PACKAGE$ qualifiedName ;$
            //
            case 2: {
                setResult(
                    new packageSpec(getLeftIToken(), getRightIToken(),
                                    (simpleNameList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 3:  importSpecs ::= $Empty
            //
            case 3: {
                setResult(
                    new importSpecList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 4:  importSpecs ::= importSpecs importSpec
            //
            case 4: {
                ((importSpecList)getRhsSym(1)).add((importSpec)getRhsSym(2));
                break;
            }
            //
            // Rule 5:  importSpec ::= IMPORT$ qualifiedName ;$
            //
            case 5: {
                setResult(
                    new importSpec(getLeftIToken(), getRightIToken(),
                                   (simpleNameList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 6:  qualifiedName ::= simpleName
            //
            case 6: {
                setResult(
                    new simpleNameList((IsimpleName)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 7:  qualifiedName ::= qualifiedName .$ simpleName
            //
            case 7: {
                ((simpleNameList)getRhsSym(1)).add((IsimpleName)getRhsSym(3));
                break;
            }
            //
            // Rule 8:  simpleName ::= IDENTIFIER
            //
            case 8: {
                setResult(
                    new simpleName0(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 9:  simpleName ::= *
            //
            case 9: {
                setResult(
                    new simpleName1(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 10:  languageSpecList ::= languageSpec
            //
            case 10: {
                setResult(
                    new languageSpecList((languageSpec)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 11:  languageSpecList ::= languageSpecList languageSpec
            //
            case 11: {
                ((languageSpecList)getRhsSym(1)).add((languageSpec)getRhsSym(2));
                break;
            }
            //
            // Rule 12:  languageSpec ::= LANGUAGE$ IDENTIFIER$langName super_opt$sup languageBody
            //
            case 12: {
                setResult(
                    new languageSpec(getLeftIToken(), getRightIToken(),
                                     new ASTNodeToken(getRhsIToken(2)),
                                     (super_opt)getRhsSym(3),
                                     (languageBody)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 13:  super_opt ::= EXTENDS$ qualifiedName
            //
            case 13: {
                setResult(
                    new super_opt(getLeftIToken(), getRightIToken(),
                                  (simpleNameList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 14:  super_opt ::= $Empty
            //
            case 14: {
                setResult(null);
                break;
            }
            //
            // Rule 15:  languageBody ::= {$ languageMembers }$
            //
            case 15: {
                setResult(
                    new languageBody(getLeftIToken(), getRightIToken(),
                                     (languageMemberList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 16:  languageMembers ::= languageMember
            //
            case 16: {
                setResult(
                    new languageMemberList((IlanguageMember)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 17:  languageMembers ::= languageMembers languageMember
            //
            case 17: {
                ((languageMemberList)getRhsSym(1)).add((IlanguageMember)getRhsSym(2));
                break;
            }
            //
            // Rule 18:  languageMember ::= textColoringSpec
            //
            case 18:
                break;
            //
            // Rule 19:  languageMember ::= presentationSpec
            //
            case 19:
                break;
            //
            // Rule 20:  languageMember ::= outlineSpec
            //
            case 20:
                break;
            //
            // Rule 21:  languageMember ::= foldingSpec
            //
            case 21:
                break;
            //
            // Rule 22:  languageMember ::= resourceDecl
            //
            case 22:
                break;
            //
            // Rule 23:  languageMember ::= functionDecl
            //
            case 23:
                break;
            //
            // Rule 24:  foldingSpec ::= FOLDABLE$ {$ foldableNodes }$
            //
            case 24: {
                setResult(
                    new foldingSpec(getLeftIToken(), getRightIToken(),
                                    (foldableNodeList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 25:  foldableNodes ::= foldableNode
            //
            case 25: {
                setResult(
                    new foldableNodeList((foldableNode)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 26:  foldableNodes ::= foldableNodes foldableNode
            //
            case 26: {
                ((foldableNodeList)getRhsSym(1)).add((foldableNode)getRhsSym(2));
                break;
            }
            //
            // Rule 27:  foldableNode ::= NODE$ IDENTIFIER$nodeName ;$
            //
            case 27: {
                setResult(
                    new foldableNode(getLeftIToken(), getRightIToken(),
                                     new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 28:  functionDecl ::= primitiveType IDENTIFIER$name ($ formalArgList )$ functionBody
            //
            case 28: {
                setResult(
                    new functionDecl(getLeftIToken(), getRightIToken(),
                                     (IprimitiveType)getRhsSym(1),
                                     new ASTNodeToken(getRhsIToken(2)),
                                     (IformalArgList)getRhsSym(4),
                                     (functionBody)getRhsSym(6))
                );
                break;
            }
            //
            // Rule 29:  formalArgList ::= formalArg
            //
            case 29:
                break;
            //
            // Rule 30:  formalArgList ::= formalArgList ,$ formalArg
            //
            case 30: {
                setResult(
                    new formalArgList(getLeftIToken(), getRightIToken(),
                                      (IformalArgList)getRhsSym(1),
                                      (formalArg)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 31:  formalArg ::= typeName IDENTIFIER$name
            //
            case 31: {
                setResult(
                    new formalArg(getLeftIToken(), getRightIToken(),
                                  (ItypeName)getRhsSym(1),
                                  new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 32:  typeName ::= primitiveType
            //
            case 32:
                break;
            //
            // Rule 33:  typeName ::= IDENTIFIER$type
            //
            case 33: {
                setResult(
                    new typeName(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 34:  functionBody ::= {$ expression }$
            //
            case 34: {
                setResult(
                    new functionBody(getLeftIToken(), getRightIToken(),
                                     (Iexpression)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 35:  resourceDecl ::= primitiveType IDENTIFIER$ident =$ expression$initializer ;$
            //
            case 35: {
                setResult(
                    new resourceDecl(getLeftIToken(), getRightIToken(),
                                     (IprimitiveType)getRhsSym(1),
                                     new ASTNodeToken(getRhsIToken(2)),
                                     (Iexpression)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 36:  textColoringSpec ::= TEXT$ super_opt$sup {$ textColoringMembers }$
            //
            case 36: {
                setResult(
                    new textColoringSpec(getLeftIToken(), getRightIToken(),
                                         (super_opt)getRhsSym(2),
                                         (textColoringMemberList)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 37:  textColoringMembers ::= $Empty
            //
            case 37: {
                setResult(
                    new textColoringMemberList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 38:  textColoringMembers ::= textColoringMembers textColoringMember
            //
            case 38: {
                ((textColoringMemberList)getRhsSym(1)).add((ItextColoringMember)getRhsSym(2));
                break;
            }
            //
            // Rule 39:  textColoringMember ::= resourceDecl
            //
            case 39:
                break;
            //
            // Rule 40:  textColoringMember ::= tokenDecl
            //
            case 40:
                break;
            //
            // Rule 41:  styleSet ::= {$ styleList }$
            //
            case 41: {
                setResult(
                    new styleSet(getLeftIToken(), getRightIToken(),
                                 (styleKeyList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 42:  styleList ::= styleKey
            //
            case 42: {
                setResult(
                    new styleKeyList((IstyleKey)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 43:  styleList ::= styleList ,$ styleKey
            //
            case 43: {
                ((styleKeyList)getRhsSym(1)).add((IstyleKey)getRhsSym(3));
                break;
            }
            //
            // Rule 44:  styleKey ::= REGULAR
            //
            case 44: {
                setResult(
                    new styleKey0(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 45:  styleKey ::= BOLD
            //
            case 45: {
                setResult(
                    new styleKey1(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 46:  styleKey ::= ITALIC
            //
            case 46: {
                setResult(
                    new styleKey2(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 47:  styleKey ::= UNDERLINE
            //
            case 47: {
                setResult(
                    new styleKey3(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 48:  resourceValue ::= IDENTIFIER
            //
            case 48: {
                setResult(
                    new resourceValue0(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 49:  resourceValue ::= STRING_LITERAL
            //
            case 49: {
                setResult(
                    new resourceValue1(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 50:  tokenDecl ::= IDENTIFIER$name =$ {$ tokenDeclAttributes$attrs }$
            //
            case 50: {
                setResult(
                    new tokenDecl(getLeftIToken(), getRightIToken(),
                                  new ASTNodeToken(getRhsIToken(1)),
                                  (textAttributeDeclList)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 51:  tokenDeclAttributes ::= $Empty
            //
            case 51: {
                setResult(
                    new textAttributeDeclList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 52:  tokenDeclAttributes ::= tokenDeclAttributes textAttributeDecl
            //
            case 52: {
                ((textAttributeDeclList)getRhsSym(1)).add((ItextAttributeDecl)getRhsSym(2));
                break;
            }
            //
            // Rule 53:  textAttributeDecl ::= fontAttributeDecl
            //
            case 53:
                break;
            //
            // Rule 54:  textAttributeDecl ::= colorAttributeDecl
            //
            case 54:
                break;
            //
            // Rule 55:  textAttributeDecl ::= styleAttributesDecl
            //
            case 55:
                break;
            //
            // Rule 56:  fontAttributeDecl ::= FONT$ =$ resourceValue ;$
            //
            case 56: {
                setResult(
                    new fontAttributeDecl(getLeftIToken(), getRightIToken(),
                                          (IresourceValue)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 57:  colorAttributeDecl ::= COLOR$ =$ resourceValue ;$
            //
            case 57: {
                setResult(
                    new colorAttributeDecl(getLeftIToken(), getRightIToken(),
                                           (IresourceValue)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 58:  styleAttributesDecl ::= STYLE$ =$ styleSet ;$
            //
            case 58: {
                setResult(
                    new styleAttributesDecl(getLeftIToken(), getRightIToken(),
                                            (styleSet)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 59:  presentationSpec ::= PRESENTATION$ {$ presentationMembers$members }$
            //
            case 59: {
                setResult(
                    new presentationSpec(getLeftIToken(), getRightIToken(),
                                         (presentationMemberList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 60:  presentationMembers ::= $Empty
            //
            case 60: {
                setResult(
                    new presentationMemberList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 61:  presentationMembers ::= presentationMembers presentationMember
            //
            case 61: {
                ((presentationMemberList)getRhsSym(1)).add((IpresentationMember)getRhsSym(2));
                break;
            }
            //
            // Rule 62:  presentationMember ::= resourceDecl
            //
            case 62:
                break;
            //
            // Rule 63:  presentationMember ::= nodePresentation
            //
            case 63:
                break;
            //
            // Rule 64:  nodePresentation ::= IDENTIFIER$type IDENTIFIER$name =$ {$ nodePresentationAttributes$attrs }$
            //
            case 64: {
                setResult(
                    new nodePresentation(getLeftIToken(), getRightIToken(),
                                         new ASTNodeToken(getRhsIToken(1)),
                                         new ASTNodeToken(getRhsIToken(2)),
                                         (nodePresentationAttributeList)getRhsSym(5))
                );
                break;
            }
            //
            // Rule 65:  nodePresentationAttributes ::= $Empty
            //
            case 65: {
                setResult(
                    new nodePresentationAttributeList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 66:  nodePresentationAttributes ::= nodePresentationAttributes nodePresentationAttribute
            //
            case 66: {
                ((nodePresentationAttributeList)getRhsSym(1)).add((InodePresentationAttribute)getRhsSym(2));
                break;
            }
            //
            // Rule 67:  nodePresentationAttribute ::= labelPresentation
            //
            case 67:
                break;
            //
            // Rule 68:  nodePresentationAttribute ::= iconPresentation
            //
            case 68:
                break;
            //
            // Rule 69:  labelPresentation ::= LABEL$ =$ expression ;$
            //
            case 69: {
                setResult(
                    new labelPresentation(getLeftIToken(), getRightIToken(),
                                          (Iexpression)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 70:  iconPresentation ::= ICON$ =$ expression ;$
            //
            case 70: {
                setResult(
                    new iconPresentation(getLeftIToken(), getRightIToken(),
                                         (Iexpression)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 71:  outlineSpec ::= OUTLINE$ {$ outlineSpecMembers$members }$
            //
            case 71: {
                setResult(
                    new outlineSpec(getLeftIToken(), getRightIToken(),
                                    (outlineSpecMemberList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 72:  outlineSpecMembers ::= outlineSpecMember
            //
            case 72: {
                setResult(
                    new outlineSpecMemberList((nodeSpec)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 73:  outlineSpecMembers ::= outlineSpecMembers outlineSpecMember
            //
            case 73: {
                ((outlineSpecMemberList)getRhsSym(1)).add((nodeSpec)getRhsSym(2));
                break;
            }
            //
            // Rule 74:  outlineSpecMember ::= nodeSpec
            //
            case 74:
                break;
            //
            // Rule 75:  nodeSpec ::= NODE$ IDENTIFIER$id ;$
            //
            case 75: {
                setResult(
                    new nodeSpec(getLeftIToken(), getRightIToken(),
                                 new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 76:  primitiveType ::= ICON
            //
            case 76: {
                setResult(
                    new primitiveType0(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 77:  primitiveType ::= FONT
            //
            case 77: {
                setResult(
                    new primitiveType1(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 78:  primitiveType ::= COLOR
            //
            case 78: {
                setResult(
                    new primitiveType2(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 79:  primitiveType ::= STYLE
            //
            case 79: {
                setResult(
                    new primitiveType3(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 80:  primitiveType ::= INT
            //
            case 80: {
                setResult(
                    new primitiveType4(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 81:  primitiveType ::= BOOLEAN
            //
            case 81: {
                setResult(
                    new primitiveType5(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 82:  expression ::= literal
            //
            case 82:
                break;
            //
            // Rule 83:  expression ::= IDENTIFIER
            //
            case 83: {
                setResult(
                    new expression0(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 84:  expression ::= JAVA_EXPR
            //
            case 84: {
                setResult(
                    new expression1(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 85:  expression ::= caseExpression
            //
            case 85:
                break;
            //
            // Rule 86:  literal ::= STRING_LITERAL
            //
            case 86: {
                setResult(
                    new literal0(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 87:  literal ::= INTEGER
            //
            case 87: {
                setResult(
                    new literal1(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 88:  caseExpression ::= CASE$ {$ alternativeList }$
            //
            case 88: {
                setResult(
                    new caseExpression(getLeftIToken(), getRightIToken(),
                                       (alternativeList)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 89:  alternativeList ::= alternative
            //
            case 89: {
                setResult(
                    new alternativeList((alternative)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 90:  alternativeList ::= alternativeList ,$ alternative
            //
            case 90: {
                ((alternativeList)getRhsSym(1)).add((alternative)getRhsSym(3));
                break;
            }
            //
            // Rule 91:  alternative ::= JAVA_EXPR$cond ->$ expression
            //
            case 91: {
                setResult(
                    new alternative(getLeftIToken(), getRightIToken(),
                                    new ASTNodeToken(getRhsIToken(1)),
                                    (Iexpression)getRhsSym(3))
                );
                break;
            }
    
            default:
                break;
        }
        return;
    }
}

