package org.eclipse.imp.presentation.parser;

import lpg.runtime.*;
import java.util.*;
import org.eclipse.imp.parser.ILexer;

public class PSPLexer implements RuleAction, ILexer
{
    private PSPLexerLpgLexStream lexStream;
    
    private static ParseTable prs = new PSPLexerprs();
    public ParseTable getParseTable() { return prs; }

    private LexParser lexParser = new LexParser();
    public LexParser getParser() { return lexParser; }

    public int getToken(int i) { return lexParser.getToken(i); }
    public int getRhsFirstTokenIndex(int i) { return lexParser.getFirstToken(i); }
    public int getRhsLastTokenIndex(int i) { return lexParser.getLastToken(i); }

    public int getLeftSpan() { return lexParser.getToken(1); }
    public int getRightSpan() { return lexParser.getLastToken(); }

    public void resetKeywordLexer()
    {
        if (kwLexer == null)
              this.kwLexer = new PSPKWLexer(lexStream.getInputChars(), PSPParsersym.TK_IDENTIFIER);
        else this.kwLexer.setInputChars(lexStream.getInputChars());
    }

    public void reset(String filename, int tab) throws java.io.IOException
    {
        lexStream = new PSPLexerLpgLexStream(filename, tab);
        lexParser.reset((ILexStream) lexStream, prs, (RuleAction) this);
        resetKeywordLexer();
    }

    public void reset(char[] input_chars, String filename)
    {
        reset(input_chars, filename, 1);
    }
    
    public void reset(char[] input_chars, String filename, int tab)
    {
        lexStream = new PSPLexerLpgLexStream(input_chars, filename, tab);
        lexParser.reset((ILexStream) lexStream, prs, (RuleAction) this);
        resetKeywordLexer();
    }
    
    public PSPLexer(String filename, int tab) throws java.io.IOException 
    {
        reset(filename, tab);
    }

    public PSPLexer(char[] input_chars, String filename, int tab)
    {
        reset(input_chars, filename, tab);
    }

    public PSPLexer(char[] input_chars, String filename)
    {
        reset(input_chars, filename, 1);
    }

    public PSPLexer() {}

    public ILexStream getILexStream() { return lexStream; }

    /**
     * @deprecated replaced by {@link #getILexStream()}
     */
    public ILexStream getLexStream() { return lexStream; }

    private void initializeLexer(IPrsStream prsStream, int start_offset, int end_offset)
    {
        if (lexStream.getInputChars() == null)
            throw new NullPointerException("LexStream was not initialized");
        lexStream.setPrsStream(prsStream);
        prsStream.makeToken(start_offset, end_offset, 0); // Token list must start with a bad token
    }

    private void addEOF(IPrsStream prsStream, int end_offset)
    {
        prsStream.makeToken(end_offset, end_offset, PSPParsersym.TK_EOF_TOKEN); // and end with the end of file token
        prsStream.setStreamLength(prsStream.getSize());
    }

    public void lexer(IPrsStream prsStream)
    {
        lexer(null, prsStream);
    }
    
    public void lexer(Monitor monitor, IPrsStream prsStream)
    {
        initializeLexer(prsStream, 0, -1);
        lexParser.parseCharacters(monitor);  // Lex the input characters
        addEOF(prsStream, lexStream.getStreamIndex());
    }

    public void lexer(IPrsStream prsStream, int start_offset, int end_offset)
    {
        lexer(null, prsStream, start_offset, end_offset);
    }
    
    public void lexer(Monitor monitor, IPrsStream prsStream, int start_offset, int end_offset)
    {
        if (start_offset <= 1)
             initializeLexer(prsStream, 0, -1);
        else initializeLexer(prsStream, start_offset - 1, start_offset - 1);

        lexParser.parseCharacters(monitor, start_offset, end_offset);

        addEOF(prsStream, (end_offset >= lexStream.getStreamIndex() ? lexStream.getStreamIndex() : end_offset + 1));
    }

    /**
     * If a parse stream was not passed to this Lexical analyser then we
     * simply report a lexical error. Otherwise, we produce a bad token.
     */
    public void reportLexicalError(int startLoc, int endLoc) {
        IPrsStream prs_stream = lexStream.getPrsStream();
        if (prs_stream == null)
            lexStream.reportLexicalError(startLoc, endLoc);
        else {
            //
            // Remove any token that may have been processed that fall in the
            // range of the lexical error... then add one error token that spans
            // the error range.
            //
            for (int i = prs_stream.getSize() - 1; i > 0; i--) {
                if (prs_stream.getStartOffset(i) >= startLoc)
                     prs_stream.removeLastToken();
                else break;
            }
            prs_stream.makeToken(startLoc, endLoc, 0); // add an error token to the prsStream
        }        
    }

    //
    // The Lexer contains an array of characters as the input stream to be parsed.
    // There are methods to retrieve and classify characters.
    // The lexparser "token" is implemented simply as the index of the next character in the array.
    // The Lexer extends the abstract class LpgLexStream with an implementation of the abstract
    // method getKind.  The template defines the Lexer class and the lexer() method.
    // A driver creates the action class, "Lexer", passing an Option object to the constructor.
    //
    PSPKWLexer kwLexer;
    boolean printTokens;
    private final static int ECLIPSE_TAB_VALUE = 4;

    public int [] getKeywordKinds() { return kwLexer.getKeywordKinds(); }

    public PSPLexer(String filename) throws java.io.IOException
    {
        this(filename, ECLIPSE_TAB_VALUE);
        this.kwLexer = new PSPKWLexer(lexStream.getInputChars(), PSPParsersym.TK_IDENTIFIER);
    }

    /**
     * @deprecated function replaced by {@link #reset(char [] content, String filename)}
     */
    public void initialize(char [] content, String filename)
    {
        reset(content, filename);
    }
    
    final void makeToken(int left_token, int right_token, int kind)
    {
        lexStream.makeToken(left_token, right_token, kind);
    }
    
    final void makeToken(int kind)
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan();
        lexStream.makeToken(startOffset, endOffset, kind);
        if (printTokens) printValue(startOffset, endOffset);
    }

    final void makeComment(int kind)
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan();
        lexStream.getIPrsStream().makeAdjunct(startOffset, endOffset, kind);
    }

    final void skipToken()
    {
        if (printTokens) printValue(getLeftSpan(), getRightSpan());
    }
    
    final void checkForKeyWord()
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan(),
            kwKind = kwLexer.lexer(startOffset, endOffset);
        lexStream.makeToken(startOffset, endOffset, kwKind);
        if (printTokens) printValue(startOffset, endOffset);
    }
    
    //
    // This flavor of checkForKeyWord is necessary when the default kind
    // (which is returned when the keyword filter doesn't match) is something
    // other than _IDENTIFIER.
    //
    final void checkForKeyWord(int defaultKind)
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan(),
            kwKind = kwLexer.lexer(startOffset, endOffset);
        if (kwKind == PSPParsersym.TK_IDENTIFIER)
            kwKind = defaultKind;
        lexStream.makeToken(startOffset, endOffset, kwKind);
        if (printTokens) printValue(startOffset, endOffset);
    }
    
    final void printValue(int startOffset, int endOffset)
    {
        String s = new String(lexStream.getInputChars(), startOffset, endOffset - startOffset + 1);
        System.out.print(s);
    }

    //
    //
    //
    static class PSPLexerLpgLexStream extends LpgLexStream
    {
    public final static int tokenKind[] =
    {
        PSPLexersym.Char_CtlCharNotWS,    // 000    0x00
        PSPLexersym.Char_CtlCharNotWS,    // 001    0x01
        PSPLexersym.Char_CtlCharNotWS,    // 002    0x02
        PSPLexersym.Char_CtlCharNotWS,    // 003    0x03
        PSPLexersym.Char_CtlCharNotWS,    // 004    0x04
        PSPLexersym.Char_CtlCharNotWS,    // 005    0x05
        PSPLexersym.Char_CtlCharNotWS,    // 006    0x06
        PSPLexersym.Char_CtlCharNotWS,    // 007    0x07
        PSPLexersym.Char_CtlCharNotWS,    // 008    0x08
        PSPLexersym.Char_HT,              // 009    0x09
        PSPLexersym.Char_LF,              // 010    0x0A
        PSPLexersym.Char_CtlCharNotWS,    // 011    0x0B
        PSPLexersym.Char_FF,              // 012    0x0C
        PSPLexersym.Char_CR,              // 013    0x0D
        PSPLexersym.Char_CtlCharNotWS,    // 014    0x0E
        PSPLexersym.Char_CtlCharNotWS,    // 015    0x0F
        PSPLexersym.Char_CtlCharNotWS,    // 016    0x10
        PSPLexersym.Char_CtlCharNotWS,    // 017    0x11
        PSPLexersym.Char_CtlCharNotWS,    // 018    0x12
        PSPLexersym.Char_CtlCharNotWS,    // 019    0x13
        PSPLexersym.Char_CtlCharNotWS,    // 020    0x14
        PSPLexersym.Char_CtlCharNotWS,    // 021    0x15
        PSPLexersym.Char_CtlCharNotWS,    // 022    0x16
        PSPLexersym.Char_CtlCharNotWS,    // 023    0x17
        PSPLexersym.Char_CtlCharNotWS,    // 024    0x18
        PSPLexersym.Char_CtlCharNotWS,    // 025    0x19
        PSPLexersym.Char_CtlCharNotWS,    // 026    0x1A
        PSPLexersym.Char_CtlCharNotWS,    // 027    0x1B
        PSPLexersym.Char_CtlCharNotWS,    // 028    0x1C
        PSPLexersym.Char_CtlCharNotWS,    // 029    0x1D
        PSPLexersym.Char_CtlCharNotWS,    // 030    0x1E
        PSPLexersym.Char_CtlCharNotWS,    // 031    0x1F
        PSPLexersym.Char_Space,           // 032    0x20
        PSPLexersym.Char_Exclamation,     // 033    0x21
        PSPLexersym.Char_DoubleQuote,     // 034    0x22
        PSPLexersym.Char_Sharp,           // 035    0x23
        PSPLexersym.Char_DollarSign,      // 036    0x24
        PSPLexersym.Char_Percent,         // 037    0x25
        PSPLexersym.Char_Ampersand,       // 038    0x26
        PSPLexersym.Char_SingleQuote,     // 039    0x27
        PSPLexersym.Char_LeftParen,       // 040    0x28
        PSPLexersym.Char_RightParen,      // 041    0x29
        PSPLexersym.Char_Star,            // 042    0x2A
        PSPLexersym.Char_Plus,            // 043    0x2B
        PSPLexersym.Char_Comma,           // 044    0x2C
        PSPLexersym.Char_Minus,           // 045    0x2D
        PSPLexersym.Char_Dot,             // 046    0x2E
        PSPLexersym.Char_Slash,           // 047    0x2F
        PSPLexersym.Char_0,               // 048    0x30
        PSPLexersym.Char_1,               // 049    0x31
        PSPLexersym.Char_2,               // 050    0x32
        PSPLexersym.Char_3,               // 051    0x33
        PSPLexersym.Char_4,               // 052    0x34
        PSPLexersym.Char_5,               // 053    0x35
        PSPLexersym.Char_6,               // 054    0x36
        PSPLexersym.Char_7,               // 055    0x37
        PSPLexersym.Char_8,               // 056    0x38
        PSPLexersym.Char_9,               // 057    0x39
        PSPLexersym.Char_Colon,           // 058    0x3A
        PSPLexersym.Char_SemiColon,       // 059    0x3B
        PSPLexersym.Char_LessThan,        // 060    0x3C
        PSPLexersym.Char_Equal,           // 061    0x3D
        PSPLexersym.Char_GreaterThan,     // 062    0x3E
        PSPLexersym.Char_QuestionMark,    // 063    0x3F
        PSPLexersym.Char_AtSign,          // 064    0x40
        PSPLexersym.Char_A,               // 065    0x41
        PSPLexersym.Char_B,               // 066    0x42
        PSPLexersym.Char_C,               // 067    0x43
        PSPLexersym.Char_D,               // 068    0x44
        PSPLexersym.Char_E,               // 069    0x45
        PSPLexersym.Char_F,               // 070    0x46
        PSPLexersym.Char_G,               // 071    0x47
        PSPLexersym.Char_H,               // 072    0x48
        PSPLexersym.Char_I,               // 073    0x49
        PSPLexersym.Char_J,               // 074    0x4A
        PSPLexersym.Char_K,               // 075    0x4B
        PSPLexersym.Char_L,               // 076    0x4C
        PSPLexersym.Char_M,               // 077    0x4D
        PSPLexersym.Char_N,               // 078    0x4E
        PSPLexersym.Char_O,               // 079    0x4F
        PSPLexersym.Char_P,               // 080    0x50
        PSPLexersym.Char_Q,               // 081    0x51
        PSPLexersym.Char_R,               // 082    0x52
        PSPLexersym.Char_S,               // 083    0x53
        PSPLexersym.Char_T,               // 084    0x54
        PSPLexersym.Char_U,               // 085    0x55
        PSPLexersym.Char_V,               // 086    0x56
        PSPLexersym.Char_W,               // 087    0x57
        PSPLexersym.Char_X,               // 088    0x58
        PSPLexersym.Char_Y,               // 089    0x59
        PSPLexersym.Char_Z,               // 090    0x5A
        PSPLexersym.Char_LeftBracket,     // 091    0x5B
        PSPLexersym.Char_BackSlash,       // 092    0x5C
        PSPLexersym.Char_RightBracket,    // 093    0x5D
        PSPLexersym.Char_Caret,           // 094    0x5E
        PSPLexersym.Char__,               // 095    0x5F
        PSPLexersym.Char_BackQuote,       // 096    0x60
        PSPLexersym.Char_a,               // 097    0x61
        PSPLexersym.Char_b,               // 098    0x62
        PSPLexersym.Char_c,               // 099    0x63
        PSPLexersym.Char_d,               // 100    0x64
        PSPLexersym.Char_e,               // 101    0x65
        PSPLexersym.Char_f,               // 102    0x66
        PSPLexersym.Char_g,               // 103    0x67
        PSPLexersym.Char_h,               // 104    0x68
        PSPLexersym.Char_i,               // 105    0x69
        PSPLexersym.Char_j,               // 106    0x6A
        PSPLexersym.Char_k,               // 107    0x6B
        PSPLexersym.Char_l,               // 108    0x6C
        PSPLexersym.Char_m,               // 109    0x6D
        PSPLexersym.Char_n,               // 110    0x6E
        PSPLexersym.Char_o,               // 111    0x6F
        PSPLexersym.Char_p,               // 112    0x70
        PSPLexersym.Char_q,               // 113    0x71
        PSPLexersym.Char_r,               // 114    0x72
        PSPLexersym.Char_s,               // 115    0x73
        PSPLexersym.Char_t,               // 116    0x74
        PSPLexersym.Char_u,               // 117    0x75
        PSPLexersym.Char_v,               // 118    0x76
        PSPLexersym.Char_w,               // 119    0x77
        PSPLexersym.Char_x,               // 120    0x78
        PSPLexersym.Char_y,               // 121    0x79
        PSPLexersym.Char_z,               // 122    0x7A
        PSPLexersym.Char_LeftBrace,       // 123    0x7B
        PSPLexersym.Char_VerticalBar,     // 124    0x7C
        PSPLexersym.Char_RightBrace,      // 125    0x7D
        PSPLexersym.Char_Tilde,           // 126    0x7E

        PSPLexersym.Char_AfterASCII,      // for all chars in range 128..65534
        PSPLexersym.Char_EOF              // for '\uffff' or 65535 
    };
            
    public final int getKind(int i)  // Classify character at ith location
    {
        int c = (i >= getStreamLength() ? '\uffff' : getCharValue(i));
        return (c < 128 // ASCII Character
                  ? tokenKind[c]
                  : c == '\uffff'
                       ? PSPLexersym.Char_EOF
                       : PSPLexersym.Char_AfterASCII);
    }

    public String[] orderedExportedSymbols() { return PSPParsersym.orderedTerminalSymbols; }

    public PSPLexerLpgLexStream(String filename, int tab) throws java.io.IOException
    {
        super(filename, tab);
    }

    public PSPLexerLpgLexStream(char[] input_chars, String filename, int tab)
    {
        super(input_chars, filename, tab);
    }

    public PSPLexerLpgLexStream(char[] input_chars, String filename)
    {
        super(input_chars, filename, 1);
    }
    }

    public void ruleAction(int ruleNumber)
    {
        switch(ruleNumber)
        {

            //
            // Rule 1:  Token ::= identifier
            //
            case 1: { 
            
                checkForKeyWord();
                  break;
            }
            //
            // Rule 2:  Token ::= integer
            //
            case 2: { 
            
                makeToken(PSPParsersym.TK_INTEGER);
                  break;
            }
            //
            // Rule 3:  Token ::= white
            //
            case 3: { 
            
                skipToken();
                  break;
            }
            //
            // Rule 4:  Token ::= slc
            //
            case 4: { 
            
                makeComment(PSPParsersym.TK_SINGLE_LINE_COMMENT);
                  break;
            }
            //
            // Rule 5:  Token ::= stringLiteral
            //
            case 5: { 
            
                makeToken(PSPParsersym.TK_STRING_LITERAL);
                  break;
            }
            //
            // Rule 6:  Token ::= ;
            //
            case 6: { 
            
                makeToken(PSPParsersym.TK_SEMICOLON);
                  break;
            }
            //
            // Rule 7:  Token ::= :
            //
            case 7: { 
            
                makeToken(PSPParsersym.TK_COLON);
                  break;
            }
            //
            // Rule 8:  Token ::= ,
            //
            case 8: { 
            
                makeToken(PSPParsersym.TK_COMMA);
                  break;
            }
            //
            // Rule 9:  Token ::= .
            //
            case 9: { 
            
                makeToken(PSPParsersym.TK_DOT);
                  break;
            }
            //
            // Rule 10:  Token ::= (
            //
            case 10: { 
            
                makeToken(PSPParsersym.TK_LEFTPAREN);
                  break;
            }
            //
            // Rule 11:  Token ::= )
            //
            case 11: { 
            
                makeToken(PSPParsersym.TK_RIGHTPAREN);
                  break;
            }
            //
            // Rule 12:  Token ::= {
            //
            case 12: { 
            
                makeToken(PSPParsersym.TK_LEFTBRACE);
                  break;
            }
            //
            // Rule 13:  Token ::= }
            //
            case 13: { 
            
                makeToken(PSPParsersym.TK_RIGHTBRACE);
                  break;
            }
            //
            // Rule 14:  Token ::= +
            //
            case 14: { 
            
                makeToken(PSPParsersym.TK_PLUS);
                  break;
            }
            //
            // Rule 15:  Token ::= *
            //
            case 15: { 
            
                makeToken(PSPParsersym.TK_TIMES);
                  break;
            }
            //
            // Rule 16:  Token ::= -
            //
            case 16: { 
            
                makeToken(PSPParsersym.TK_MINUS);
                  break;
            }
            //
            // Rule 17:  Token ::= =
            //
            case 17: { 
            
                makeToken(PSPParsersym.TK_EQUAL);
                  break;
            }
            //
            // Rule 18:  Token ::= - >
            //
            case 18: { 
            
                makeToken(PSPParsersym.TK_ARROW);
                  break;
            }
            //
            // Rule 19:  Token ::= javaExpr
            //
            case 19: { 
            
                makeToken(PSPParsersym.TK_JAVA_EXPR);
                  break;
            }
    
            default:
                break;
        }
        return;
    }
}

