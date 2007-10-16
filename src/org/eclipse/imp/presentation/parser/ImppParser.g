%options package=org.eclipse.imp.presentation.parser
%options template=dtParserTemplate.gi
%options import_terminals=ImppLexer.gi
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

    SET_LBRACK ::= '{:'
    SET_RBRACK ::= ':}'

    LEFTBRACE  ::= '{'
    RIGHTBRACE ::= '}'

    LEFTPAREN  ::= '('
    RIGHTPAREN ::= ')'

    LESS    ::= '<'
    LESSEQ  ::= '<='
    EQUAL   ::= '='
    EQUALS  ::= '=='
    NOTEQ   ::= '!='
    GREATEQ ::= '>='
    GREATER ::= '>'
    PLUS    ::= '+'
    MINUS   ::= '-'
    PLUSPLUS ::= '++'
    MINUSMIN ::= '--'
    TIMES   ::= '*'
    SLASH   ::= '/'
    VBAR    ::= '|'
    BIT_AND ::= '&'
    BIT_NOT ::= '^'
    LOG_AND ::= '&&'
    LOG_OR  ::= '||'
    LOG_NOT ::= '!'

    SEMICOLON ::= ';'
    DOT ::= '.'
    COMMA ::= ','
%End

%Start
    compilationUnit
%End

%Rules
    compilationUnit ::= package_spec import_specs presentation_list

    package_spec ::= PACKAGE$ qualified_name ';'$

    import_specs ::= %empty | import_specs import_spec

    import_spec ::= IMPORT$ qualified_name ';'$

    qualified_name$$simple_name ::= simple_name | qualified_name '.'$ simple_name

    simple_name ::= IDENTIFIER | '*'

    presentation_list$$presentation_declaration ::=
          presentation_declaration
        | presentation_list presentation_declaration

    presentation_declaration ::=
          PRESENTATION$ IDENTIFIER$name super_opt$sup presentation_body$body

    super_opt ::= EXTENDS$ qualified_name | %empty

    presentation_body ::= '{'$ presentation_members '}'$

    presentation_members ::= presentation_member | presentation_members presentation_member

    presentation_member ::=
          text_presentation_spec
        | tree_presentation_spec
        | outline_decl
        | editor_decl
        | resource_decl

    resource_decl ::= primitive_type IDENTIFIER$ident optInitializer$initializer ';'$

    optInitializer ::= '='$ expression | %empty

    text_presentation_spec ::= TEXT$ IDENTIFIER$name super_opt$sup '{'$ text_presentation_members '}'$

    text_presentation_members$$text_presentation_member ::= %empty
                              |   text_presentation_members text_presentation_member

    text_presentation_member ::= resource_decl | token_decl

    text_attribute_decl ::= font_attribute_decl | color_attribute_decl | style_attributes_decl

    font_attribute_decl ::= FONT$ '='$ resource_value ';'$

    color_attribute_decl ::= COLOR$ '='$ resource_value ';'$

    style_attributes_decl ::= STYLE$ '='$ style_set ';'$

    style_set ::= '{'$ style_list '}'$

    style_list$$style_key ::= style_key
               |   style_list style_key

    style_key ::= REGULAR
              |   BOLD
              |   ITALIC
              |   UNDER

    resource_value ::= IDENTIFIER | STRING_LITERAL

    token_decl ::= TOKEN$ IDENTIFIER$name '='$ '{'$ token_decl_attributes$attrs '}'$

    token_decl_attributes$$text_attribute_decl ::= %empty
                            | token_decl_attributes text_attribute_decl

    tree_presentation_spec ::= TREE$ IDENTIFIER$name '{'$ tree_presentation_members$members '}'$

    tree_presentation_members$$tree_presentation_member ::= %empty
                         |   tree_presentation_members tree_presentation_member

    tree_presentation_member ::= NODE$ IDENTIFIER$name '='$ '{'$ tree_presentation_attributes$attrs '}'$

    tree_presentation_attributes$$tree_presentation_attribute ::= %empty
                            | tree_presentation_attributes tree_presentation_attribute

    tree_presentation_attribute ::= label_presentation
                                |   icon_presentation

    label_presentation ::= LABEL$ '='$ expression ';'$

    icon_presentation ::= ICON$ '='$ expression ';'$

    outline_decl ::= OUTLINE$ IDENTIFIER$id super_opt$sup outline_body$body
    outline_body ::= '{'$ outline_body_members$members '}'$
    outline_body_members$$outline_body_member ::= outline_body_member
                         |   outline_body_members outline_body_member
    outline_body_member ::= use_spec | node_spec | leaf_spec
    use_spec  ::= USE$ IDENTIFIER$id ';'$
    node_spec ::= NODE$ IDENTIFIER$id ';'$
    leaf_spec ::= LEAF$ IDENTIFIER$id ';'$

    editor_decl ::= EDITOR$ IDENTIFIER$id super_opt$sup editor_body$body
    editor_body ::= '{'$ editor_body_members$members '}'$
    editor_body_members$$editor_body_member ::= editor_body_member
                        | editor_body_members editor_body_member
    editor_body_member ::= use_spec

    primitive_type ::= ICON
                   |   FONT
                   |   COLOR
                   |   STYLE
                   |   SET
                   |   INT
                   |   BOOLEAN

    expression ::= literal | IDENTIFIER | binary_op | unary_op | member_ref | method_call | set_expression

    literal ::= STRING_LITERAL | INTEGER

    binary_op ::= expression$left binary_operator$op expression$right
    binary_operator ::= '+' | '-' | '*' | '/' | '|' | '&' | '||' | '&&' | '<' | '<=' | '==' | '!=' | '>=' | '>'

    unary_op ::= prefix_unary_op expression | expression postfix_unary_op
    prefix_unary_op ::= '-' | '^' | '!' | '++' | '--'
    postfix_unary_op ::= '++' | '--'

    member_ref ::= expression$target '.'$ IDENTIFIER callSuffix

    method_call ::= IDENTIFIER callSuffix

    callSuffix ::= '('$ parameter_list ')'$

    parameter_list$$expression ::= %empty | expression | parameter_list ','$ expression

    set_expression ::= set_literal
                   |   set_union

    set_literal ::= '{:'$ set_element_list$list ':}'$

    set_element_list ::= set_element$elt
                     |   set_element_list$list ','$ set_element$elt

    set_element ::= expression$e1 '->'$ expression$e2

    set_union ::= set_expression$e1 '+'$ set_expression$e2
%End

%Headers
    /.
        public void resolve(ASTNode root) {
        }
     ./
%End
