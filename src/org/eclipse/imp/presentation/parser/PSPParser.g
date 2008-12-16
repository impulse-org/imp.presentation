%options package=org.eclipse.imp.presentation.parser
%options template=dtParserTemplate.gi
%options import_terminals=PSPLexer.gi
%options parent_saved,automatic_ast=toplevel,visitor=preorder,ast_directory=./Ast,ast_type=ASTNode

%Globals
    /.import org.eclipse.imp.parser.IParser;
    import java.util.Hashtable;
    import java.util.Stack;
    ./
%End

%Define
    $ast_class /.Object./
    $additional_interfaces /., IParser./
%End

%Terminals
    ARROW ::= '->'

    LEFTBRACE  ::= '{'
    RIGHTBRACE ::= '}'

    LEFTPAREN  ::= '('
    RIGHTPAREN ::= ')'

    EQUAL   ::= '='
    PLUS    ::= '+'
    MINUS   ::= '-'
    TIMES   ::= '*'

    SEMICOLON ::= ';'
    DOT       ::= '.'
    COMMA     ::= ','
%End

%Start
    compilationUnit
%End

%Rules
    compilationUnit ::= packageSpec importSpecs languageSpecList

    packageSpec ::= PACKAGE$ qualifiedName ';'$

    importSpecs$$importSpec ::= %empty | importSpecs importSpec

    importSpec ::= IMPORT$ qualifiedName ';'$

    qualifiedName$$simpleName ::= simpleName | qualifiedName '.'$ simpleName

    simpleName ::= IDENTIFIER | '*'

    languageSpecList$$languageSpec ::=
          languageSpec
        | languageSpecList languageSpec

    languageSpec ::= LANGUAGE$ IDENTIFIER$langName super_opt$sup languageBody

    super_opt ::= EXTENDS$ qualifiedName | %empty

    languageBody ::= '{'$ languageMembers '}'$

    languageMembers$$languageMember ::=
          languageMember
        | languageMembers languageMember

    languageMember ::=
          textColoringSpec
        | presentationSpec
        | outlineSpec
        | foldingSpec
        | resourceDecl
        | functionDecl

    foldingSpec ::= FOLDABLE$ '{'$ foldableNodes '}'$
    foldableNodes$$foldableNode ::=
          foldableNode
        | foldableNodes foldableNode
    foldableNode ::= NODE$ IDENTIFIER$nodeName ';'$

    functionDecl ::= primitiveType IDENTIFIER$name '('$ formalArgList ')'$ functionBody

    formalArgList ::=
          formalArg
        | formalArgList ','$ formalArg

    formalArg ::= typeName IDENTIFIER$name
    typeName  ::= primitiveType | IDENTIFIER$type

    functionBody ::= '{'$ expression '}'$

    resourceDecl ::= primitiveType IDENTIFIER$ident '='$ expression$initializer ';'$

    textColoringSpec ::= TEXT$ super_opt$sup '{'$ textColoringMembers '}'$

    textColoringMembers$$textColoringMember ::= %empty
        | textColoringMembers textColoringMember

    textColoringMember ::= resourceDecl | tokenDecl

    styleSet ::= '{'$ styleList '}'$

    styleList$$styleKey ::=
          styleKey
        | styleList ','$ styleKey

    styleKey ::=
          REGULAR
        | BOLD
        | ITALIC
        | UNDERLINE

    resourceValue ::= IDENTIFIER | STRING_LITERAL

    tokenDecl ::= IDENTIFIER$name '='$ '{'$ tokenDeclAttributes$attrs '}'$

    tokenDeclAttributes$$textAttributeDecl ::= %empty
        | tokenDeclAttributes textAttributeDecl

    textAttributeDecl   ::= fontAttributeDecl | colorAttributeDecl | styleAttributesDecl

    fontAttributeDecl   ::= FONT$  '='$ resourceValue ';'$
    colorAttributeDecl  ::= COLOR$ '='$ resourceValue ';'$
    styleAttributesDecl ::= STYLE$ '='$ styleSet ';'$

    presentationSpec ::= PRESENTATION$ '{'$ presentationMembers$members '}'$

    presentationMembers$$presentationMember ::= %empty
        | presentationMembers presentationMember

    presentationMember ::=
          resourceDecl
        | nodePresentation

    nodePresentation ::= IDENTIFIER$type IDENTIFIER$name '='$ '{'$ nodePresentationAttributes$attrs '}'$

    nodePresentationAttributes$$nodePresentationAttribute ::= %empty
        | nodePresentationAttributes nodePresentationAttribute

    nodePresentationAttribute ::=
          labelPresentation
        | iconPresentation

    labelPresentation ::= LABEL$ '='$ expression ';'$
    iconPresentation  ::= ICON$  '='$ expression ';'$

    outlineSpec ::= OUTLINE$ '{'$ outlineSpecMembers$members '}'$

    outlineSpecMembers$$outlineSpecMember ::=
          outlineSpecMember
        | outlineSpecMembers outlineSpecMember
    outlineSpecMember ::= nodeSpec

    nodeSpec ::= NODE$ IDENTIFIER$id ';'$

    primitiveType ::=
          ICON
        | FONT
        | COLOR
        | STYLE
        | INT
        | BOOLEAN

    expression ::= literal | IDENTIFIER | JAVA_EXPR | caseExpression

    literal ::= STRING_LITERAL | INTEGER

    caseExpression ::= CASE$ '{'$ alternativeList '}'$

    alternativeList$$alternative ::=
          alternative
        | alternativeList ','$ alternative

    alternative ::= JAVA_EXPR$cond '->'$ expression
%End

%Headers
    /.
        public void resolve(ASTNode root) {
        }
     ./
%End
