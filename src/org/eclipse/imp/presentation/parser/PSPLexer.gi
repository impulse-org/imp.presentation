%options package=org.eclipse.imp.presentation.parser
%options template=LexerTemplate.gi
%options filter=PSPKWLexer.gi

%Globals
    /.import java.util.*;
    import org.eclipse.imp.parser.ILexer;
    ./
%End

%Define
    $additional_interfaces /., ILexer./
    $kw_lexer_class /.$PSPKWLexer./
%End

%Include
    LexerBasicMap.gi
%End

%Export
    ARROW
    COMMA
    COLON
    DOT
    EQUAL
    IDENTIFIER
    INTEGER
    JAVA_EXPR
    LEFTBRACE
    LEFTPAREN
    MINUS
    PLUS
    RIGHTBRACE
    RIGHTPAREN
    SEMICOLON
    SINGLE_LINE_COMMENT
    STRING_LITERAL
    TIMES
%End

%Terminals
    CtlCharNotWS

    LF   CR   HT   FF

    a    b    c    d    e    f    g    h    i    j    k    l    m
    n    o    p    q    r    s    t    u    v    w    x    y    z
    _

    A    B    C    D    E    F    G    H    I    J    K    L    M
    N    O    P    Q    R    S    T    U    V    W    X    Y    Z

    0    1    2    3    4    5    6    7    8    9

    AfterASCII   ::= '\u0080..\ufffe'
    Space        ::= ' '
    LF           ::= NewLine
    CR           ::= Return
    HT           ::= HorizontalTab
    FF           ::= FormFeed
    DoubleQuote  ::= '"'
    SingleQuote  ::= "'"
    Percent      ::= '%'
    VerticalBar  ::= '|'
    Exclamation  ::= '!'
    AtSign       ::= '@'
    BackQuote    ::= '`'
    Tilde        ::= '~'
    Sharp        ::= '#'
    DollarSign   ::= '$'
    Ampersand    ::= '&'
    Caret        ::= '^'
    Colon        ::= ':'
    SemiColon    ::= ';'
    BackSlash    ::= '\'
    LeftBrace    ::= '{'
    RightBrace   ::= '}'
    LeftBracket  ::= '['
    RightBracket ::= ']'
    QuestionMark ::= '?'
    Comma        ::= ','
    Dot          ::= '.'
    LessThan     ::= '<'
    GreaterThan  ::= '>'
    Plus         ::= '+'
    Minus        ::= '-'
    Slash        ::= '/'
    Star         ::= '*'
    LeftParen    ::= '('
    RightParen   ::= ')'
    Equal        ::= '='
%End

%Start
    Token
%End

%Rules
    Token ::= identifier
        /.$BeginJava
                    checkForKeyWord();
          $EndJava./

    Token ::= integer
        /.$BeginJava
                    makeToken($_INTEGER);
          $EndJava./

    Token ::= white
        /.$BeginJava
                    skipToken();
          $EndJava./

    Token ::= slc
        /.$BeginJava
                    makeComment($_SINGLE_LINE_COMMENT);
          $EndJava./

    Token ::= stringLiteral
        /.$BeginJava
                    makeToken($_STRING_LITERAL);
          $EndJava./

    Token ::= ';'
        /.$BeginJava
                    makeToken($_SEMICOLON);
          $EndJava./

    Token ::= ':'
        /.$BeginJava
                    makeToken($_COLON);
          $EndJava./

    Token ::= ','
        /.$BeginJava
                    makeToken($_COMMA);
          $EndJava./

    Token ::= '.'
        /.$BeginJava
                    makeToken($_DOT);
          $EndJava./

    Token ::= '('
        /.$BeginJava
                    makeToken($_LEFTPAREN);
          $EndJava./

    Token ::= ')'
        /.$BeginJava
                    makeToken($_RIGHTPAREN);
          $EndJava./

    Token ::= '{'
        /.$BeginJava
                    makeToken($_LEFTBRACE);
          $EndJava./

    Token ::= '}'
        /.$BeginJava
                    makeToken($_RIGHTBRACE);
          $EndJava./

    Token ::= '+'
        /.$BeginJava
                    makeToken($_PLUS);
          $EndJava./

    Token ::= '*'
        /.$BeginJava
                    makeToken($_TIMES);
          $EndJava./

    Token ::= '-'
        /.$BeginJava
                    makeToken($_MINUS);
          $EndJava./

    Token ::= '='
        /.$BeginJava
                    makeToken($_EQUAL);
          $EndJava./

    Token ::= '-' '>'
        /.$BeginJava
                    makeToken($_ARROW);
          $EndJava./

    Token ::= javaExpr
        /.$BeginJava
                    makeToken($_JAVA_EXPR);
          $EndJava./

    javaExpr ::= '{' ':' javaExprMiddle ':' '}'

    javaExprMiddle ::=
          anyButColon
        | javaExprMiddle anyButColon
        | javaExprMiddle ':' anyButRBrace

    anyButColon ::= digit | letter | whiteChar | specialNoColon

    anyButRBrace ::= digit | letter | whiteChar | specialNoRBrace

    identifier -> letter
                | identifier letter
                | identifier digit
                | identifier '_'

    integer ::= digit
             | integer digit

    white ::= whiteChar
            | white whiteChar

    slc ::= '/' '/'
          | slc notEOL

    stringLiteral ::= '"' stringBody '"'

    stringBody ::= notDQ | stringBody notDQ

    notDQ -> letter
           | digit
           | specialNotDQ
           | Space
           | HT
           | FF

    digit ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

    aA ::= a | A
    bB ::= b | B
    cC ::= c | C
    dD ::= d | D
    eE ::= e | E
    fF ::= f | F
    gG ::= g | G
    hH ::= h | H
    iI ::= i | I
    jJ ::= j | J
    kK ::= k | K
    lL ::= l | L
    mM ::= m | M
    nN ::= n | N
    oO ::= o | O
    pP ::= p | P
    qQ ::= q | Q
    rR ::= r | R
    sS ::= s | S
    tT ::= t | T
    uU ::= u | U
    vV ::= v | V
    wW ::= w | W
    xX ::= x | X
    yY ::= y | Y
    zZ ::= z | Z

    letter ::= aA | bB | cC | dD | eE | fF | gG | hH | iI | jJ | kK | lL | mM | nN | oO | pP | qQ | rR | sS | tT | uU | vV | wW | xX | yY | zZ

    -- any ::= letter | digit | special | white

    whiteChar ::= Space | LF | CR | HT | FF

    special ::=
                '+' | '-' | '(' | ')' | '"' | '!' | '@' | '`' | '~' | '.' |
                '%' | '&' | '^' | ':' | ';' | "'" | '\' | '|' | '{' | '}' |
                '[' | ']' | '?' | ',' | '<' | '>' | '=' | '#' | '*' | '_' |
                '/' | '$'

    specialNotDQ ::=
                '+' | '-' | '(' | ')' |       '!' | '@' | '`' | '~' | '.' |
                '%' | '&' | '^' | ':' | ';' | "'" | '\' | '|' | '{' | '}' |
                '[' | ']' | '?' | ',' | '<' | '>' | '=' | '#' | '*' | '_' |
                '/' | '$'

    specialNoColon ::=
                '+' | '-' | '(' | ')' | '"' | '!' | '@' | '`' | '~' | '.' |
                '%' | '&' | '^' |       ';' | "'" | '\' | '|' | '{' | '}' |
                '[' | ']' | '?' | ',' | '<' | '>' | '=' | '#' | '*' | '_' |
                '/' | '$'

    specialNoRBrace ::=
                '+' | '-' | '(' | ')' | '"' | '!' | '@' | '`' | '~' | '.' |
                '%' | '&' | '^' | ':' | ';' | "'" | '\' | '|' | '{' |
                '[' | ']' | '?' | ',' | '<' | '>' | '=' | '#' | '*' | '_' |
                '/' | '$'

    notEOL ::= letter | digit | special | Space | HT | FF
%End
