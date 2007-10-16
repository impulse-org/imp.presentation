%options package=org.eclipse.imp.presentation.parser
%options template=LexerTemplate.gi
%options filter=ImppKWLexer.gi

%Globals
    /.import java.util.*;
    import org.eclipse.imp.parser.ILexer;
    ./
%End

%Define
    $additional_interfaces /., ILexer./
    $kw_lexer_class /.$ImppKWLexer./
%End

%Include
    LexerBasicMap.gi
%End

%Export
    SINGLE_LINE_COMMENT
    IDENTIFIER
    INTEGER
    STRING_LITERAL
    ARROW
    SET_LBRACK
    SET_RBRACK
    COMMA
    COLON
    SEMICOLON
    DOT
    PLUS
    MINUS
    TIMES
    DIVIDE
    PLUSPLUS
    MINUSMINUS
    BIT_OR
    BIT_AND
    LOG_OR
    LOG_AND
    BIT_NOT
    LOG_NOT
    LESS
    LESSEQ
    EQUAL
    EQUALS
    NOTEQ
    GREATEREQ
    GREATER
    LEFTPAREN
    RIGHTPAREN
    LEFTBRACE
    RIGHTBRACE
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

    Token ::= '='
        /.$BeginJava
                    makeToken($_EQUAL);
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

    Token ::= '-'
        /.$BeginJava
                    makeToken($_MINUS);
          $EndJava./

    Token ::= '*'
        /.$BeginJava
                    makeToken($_TIMES);
          $EndJava./

    Token ::= '/'
        /.$BeginJava
                    makeToken($_DIVIDE);
          $EndJava./

    Token ::= '+' '+'
        /.$BeginJava
                    makeToken($_PLUSPLUS);
          $EndJava./

    Token ::= '-' '-'
        /.$BeginJava
                    makeToken($_MINUSMINUS);
          $EndJava./

    Token ::= '^'
        /.$BeginJava
                    makeToken($_BIT_NOT);
          $EndJava./

    Token ::= '!'
        /.$BeginJava
                    makeToken($_LOG_NOT);
          $EndJava./

    Token ::= '&'
        /.$BeginJava
                    makeToken($_BIT_AND);
          $EndJava./

    Token ::= '|'
        /.$BeginJava
                    makeToken($_BIT_OR);
          $EndJava./

    Token ::= '|' '|'
        /.$BeginJava
                    makeToken($_LOG_OR);
          $EndJava./

    Token ::= '&' '&'
        /.$BeginJava
                    makeToken($_LOG_AND);
          $EndJava./

    Token ::= '<'
        /.$BeginJava
                    makeToken($_LESS);
          $EndJava./

    Token ::= '<' '='
        /.$BeginJava
                    makeToken($_LESSEQ);
          $EndJava./

    Token ::= '=' '='
        /.$BeginJava
                    makeToken($_EQUALS);
          $EndJava./

    Token ::= '!' '='
        /.$BeginJava
                    makeToken($_NOTEQ);
          $EndJava./

    Token ::= '>' '='
        /.$BeginJava
                    makeToken($_GREATEREQ);
          $EndJava./

    Token ::= '>'
        /.$BeginJava
                    makeToken($_GREATER);
          $EndJava./

    Token ::= '-' '>'
        /.$BeginJava
                    makeToken($_ARROW);
          $EndJava./

    Token ::= '{' ':'
        /.$BeginJava
                    makeToken($_SET_LBRACK);
          $EndJava./

    Token ::= ':' '}'
        /.$BeginJava
                    makeToken($_SET_RBRACK);
          $EndJava./

    identifier -> letter
                | identifier letter
                | identifier digit

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
                '+' | '-' | '(' | ')' |     | '!' | '@' | '`' | '~' | '.' |
                '%' | '&' | '^' | ':' | ';' | "'" | '\' | '|' | '{' | '}' |
                '[' | ']' | '?' | ',' | '<' | '>' | '=' | '#' | '*' | '_' |
                '/' | '$'

    notEOL ::= letter | digit | special | Space | HT | FF
%End
