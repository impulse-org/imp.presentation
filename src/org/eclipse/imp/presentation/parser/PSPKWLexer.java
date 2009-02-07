package org.eclipse.imp.presentation.parser;

import lpg.runtime.*;

public class PSPKWLexer extends PSPKWLexerprs
{
    private char[] inputChars;
    private final int keywordKind[] = new int[22 + 1];

    public int[] getKeywordKinds() { return keywordKind; }

    public int lexer(int curtok, int lasttok)
    {
        int current_kind = getKind(inputChars[curtok]),
            act;

        for (act = tAction(START_STATE, current_kind);
             act > NUM_RULES && act < ACCEPT_ACTION;
             act = tAction(act, current_kind))
        {
            curtok++;
            current_kind = (curtok > lasttok
                                   ? PSPKWLexersym.Char_EOF
                                   : getKind(inputChars[curtok]));
        }

        if (act > ERROR_ACTION)
        {
            curtok++;
            act -= ERROR_ACTION;
        }

        return keywordKind[act == ERROR_ACTION  || curtok <= lasttok ? 0 : act];
    }

    public void setInputChars(char[] inputChars) { this.inputChars = inputChars; }


    final static int tokenKind[] = new int[128];
    static
    {
        tokenKind['$'] = PSPKWLexersym.Char_DollarSign;
        tokenKind['_'] = PSPKWLexersym.Char__;

        tokenKind['a'] = PSPKWLexersym.Char_a;
        tokenKind['b'] = PSPKWLexersym.Char_b;
        tokenKind['c'] = PSPKWLexersym.Char_c;
        tokenKind['d'] = PSPKWLexersym.Char_d;
        tokenKind['e'] = PSPKWLexersym.Char_e;
        tokenKind['f'] = PSPKWLexersym.Char_f;
        tokenKind['g'] = PSPKWLexersym.Char_g;
        tokenKind['h'] = PSPKWLexersym.Char_h;
        tokenKind['i'] = PSPKWLexersym.Char_i;
        tokenKind['j'] = PSPKWLexersym.Char_j;
        tokenKind['k'] = PSPKWLexersym.Char_k;
        tokenKind['l'] = PSPKWLexersym.Char_l;
        tokenKind['m'] = PSPKWLexersym.Char_m;
        tokenKind['n'] = PSPKWLexersym.Char_n;
        tokenKind['o'] = PSPKWLexersym.Char_o;
        tokenKind['p'] = PSPKWLexersym.Char_p;
        tokenKind['q'] = PSPKWLexersym.Char_q;
        tokenKind['r'] = PSPKWLexersym.Char_r;
        tokenKind['s'] = PSPKWLexersym.Char_s;
        tokenKind['t'] = PSPKWLexersym.Char_t;
        tokenKind['u'] = PSPKWLexersym.Char_u;
        tokenKind['v'] = PSPKWLexersym.Char_v;
        tokenKind['w'] = PSPKWLexersym.Char_w;
        tokenKind['x'] = PSPKWLexersym.Char_x;
        tokenKind['y'] = PSPKWLexersym.Char_y;
        tokenKind['z'] = PSPKWLexersym.Char_z;
    };

    final int getKind(int c)
    {
        return ((c & 0xFFFFFF80) == 0 /* 0 <= c < 128? */ ? tokenKind[c] : 0);
    }


    public PSPKWLexer(char[] inputChars, int identifierKind)
    {
        this.inputChars = inputChars;
        keywordKind[0] = identifierKind;

        //
        // Rule 1:  Keyword ::= b o l d
        //
        
        keywordKind[1] = (PSPParsersym.TK_BOLD);
      
    
        //
        // Rule 2:  Keyword ::= b o o l e a n
        //
        
        keywordKind[2] = (PSPParsersym.TK_BOOLEAN);
      
    
        //
        // Rule 3:  Keyword ::= c a s e
        //
        
        keywordKind[3] = (PSPParsersym.TK_CASE);
      
    
        //
        // Rule 4:  Keyword ::= c o l o r
        //
        
        keywordKind[4] = (PSPParsersym.TK_COLOR);
      
    
        //
        // Rule 5:  Keyword ::= e x t e n d s
        //
        
        keywordKind[5] = (PSPParsersym.TK_EXTENDS);
      
    
        //
        // Rule 6:  Keyword ::= f o l d a b l e
        //
        
        keywordKind[6] = (PSPParsersym.TK_FOLDABLE);
      
    
        //
        // Rule 7:  Keyword ::= f o n t
        //
        
        keywordKind[7] = (PSPParsersym.TK_FONT);
      
    
        //
        // Rule 8:  Keyword ::= i c o n
        //
        
        keywordKind[8] = (PSPParsersym.TK_ICON);
      
    
        //
        // Rule 9:  Keyword ::= i m p o r t
        //
        
        keywordKind[9] = (PSPParsersym.TK_IMPORT);
      
    
        //
        // Rule 10:  Keyword ::= i n t
        //
        
        keywordKind[10] = (PSPParsersym.TK_INT);
      
    
        //
        // Rule 11:  Keyword ::= i t a l i c
        //
        
        keywordKind[11] = (PSPParsersym.TK_ITALIC);
      
    
        //
        // Rule 12:  Keyword ::= l a b e l
        //
        
        keywordKind[12] = (PSPParsersym.TK_LABEL);
      
    
        //
        // Rule 13:  Keyword ::= l a n g u a g e
        //
        
        keywordKind[13] = (PSPParsersym.TK_LANGUAGE);
      
    
        //
        // Rule 14:  Keyword ::= n o d e
        //
        
        keywordKind[14] = (PSPParsersym.TK_NODE);
      
    
        //
        // Rule 15:  Keyword ::= o u t l i n e
        //
        
        keywordKind[15] = (PSPParsersym.TK_OUTLINE);
      
    
        //
        // Rule 16:  Keyword ::= p a c k a g e
        //
        
        keywordKind[16] = (PSPParsersym.TK_PACKAGE);
      
    
        //
        // Rule 17:  Keyword ::= p r e s e n t a t i o n
        //
        
        keywordKind[17] = (PSPParsersym.TK_PRESENTATION);
      
    
        //
        // Rule 18:  Keyword ::= r e g u l a r
        //
        
        keywordKind[18] = (PSPParsersym.TK_REGULAR);
      
    
        //
        // Rule 19:  Keyword ::= s t y l e
        //
        
        keywordKind[19] = (PSPParsersym.TK_STYLE);
      
    
        //
        // Rule 20:  Keyword ::= t e x t
        //
        
        keywordKind[20] = (PSPParsersym.TK_TEXT);
      
    
        //
        // Rule 21:  Keyword ::= t o k e n
        //
        
        keywordKind[21] = (PSPParsersym.TK_TOKEN);
      
    
        //
        // Rule 22:  Keyword ::= u n d e r l i n e
        //
        
        keywordKind[22] = (PSPParsersym.TK_UNDERLINE);
      
    
        for (int i = 0; i < keywordKind.length; i++)
        {
            if (keywordKind[i] == 0)
                keywordKind[i] = identifierKind;
        }
    }
}

