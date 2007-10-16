%options package=org.eclipse.imp.presentation.parser
%options template=KeywordTemplate.gi

%Include
    KWLexerLowerCaseMap.gi
%End

%Export
    BOLD
    BOOLEAN
    COLOR
    EDITOR
    EXTENDS
    FONT
    ICON
    IMPORT
    INT
    ITALIC
    LABEL
    LANGUAGE
    LEAF
    NODE
    OUTLINE
    PACKAGE
    PRESENTATION
    REGULAR
    RETURN
    SET
    STYLE
    TEXT
    TOKEN
    TREE
    UNDER
    USE
%End

%Terminals
    a    b    c    d    e    f    g    h    i    j    k    l    m
    n    o    p    q    r    s    t    u    v    w    x    y    z
%End

%Start
    Keyword
%End

%Rules
    Keyword ::= b o l d
        /.$BeginAction
            $setResult($_BOLD);
          $EndAction
        ./

    Keyword ::= b o o l e a n
        /.$BeginAction
            $setResult($_BOOLEAN);
          $EndAction
        ./

    Keyword ::= c o l o r
        /.$BeginAction
            $setResult($_COLOR);
          $EndAction
        ./
        
    Keyword ::= e d i t o r
        /.$BeginAction
            $setResult($_EDITOR);
          $EndAction
        ./

    Keyword ::= e x t e n d s
        /.$BeginAction
            $setResult($_EXTENDS);
          $EndAction
        ./

    Keyword ::= f o n t
        /.$BeginAction
            $setResult($_FONT);
          $EndAction
        ./

    Keyword ::= i c o n
        /.$BeginAction
            $setResult($_ICON);
          $EndAction
        ./

    Keyword ::= i m p o r t
        /.$BeginAction
            $setResult($_IMPORT);
          $EndAction
        ./

    Keyword ::= i n t
        /.$BeginAction
            $setResult($_INT);
          $EndAction
        ./

    Keyword ::= i t a l i c
        /.$BeginAction
            $setResult($_ITALIC);
          $EndAction
        ./

    Keyword ::= l a b e l
        /.$BeginAction
            $setResult($_LABEL);
          $EndAction
        ./

    Keyword ::= l a n g u a g e
        /.$BeginAction
            $setResult($_LANGUAGE);
          $EndAction
        ./

    Keyword ::= l e a f
        /.$BeginAction
            $setResult($_LEAF);
          $EndAction
        ./

    Keyword ::= n o d e
        /.$BeginAction
            $setResult($_NODE);
          $EndAction
        ./

    Keyword ::= o u t l i n e
        /.$BeginAction
            $setResult($_OUTLINE);
          $EndAction
        ./

    Keyword ::= p a c k a g e
        /.$BeginAction
            $setResult($_PACKAGE);
          $EndAction
        ./

    Keyword ::= p r e s e n t a t i o n
        /.$BeginAction
            $setResult($_PRESENTATION);
          $EndAction
        ./

    Keyword ::= r e g u l a r
        /.$BeginAction
            $setResult($_REGULAR);
          $EndAction
        ./

    Keyword ::= r e t u r n
        /.$BeginAction
            $setResult($_RETURN);
          $EndAction
        ./

    Keyword ::= s e t
        /.$BeginAction
            $setResult($_SET);
          $EndAction
        ./

    Keyword ::= s t y l e
        /.$BeginAction
            $setResult($_STYLE);
          $EndAction
        ./

    Keyword ::= t e x t
        /.$BeginAction
            $setResult($_TEXT);
          $EndAction
        ./

    Keyword ::= t o k e n
        /.$BeginAction
            $setResult($_TOKEN);
          $EndAction
        ./

    Keyword ::= t r e e
        /.$BeginAction
            $setResult($_TREE);
          $EndAction
        ./

    Keyword ::= u n d e r
        /.$BeginAction
            $setResult($_UNDER);
          $EndAction
        ./

    Keyword ::= u s e
        /.$BeginAction
            $setResult($_USE);
          $EndAction
        ./
%End
