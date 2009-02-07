package org.eclipse.imp.presentation.parser;

public class PSPKWLexerprs implements lpg.runtime.ParseTable, PSPKWLexersym {
    public final static int ERROR_SYMBOL = 0;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 0;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 0;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 0;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 99;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 29;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 147;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 0;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 22;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 2;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 31;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 23;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 21;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 30;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 124;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 125;
    public final int getErrorAction() { return ERROR_ACTION; }

    public final static boolean BACKTRACK = false;
    public final boolean getBacktrack() { return BACKTRACK; }

    public final int getStartSymbol() { return lhs(0); }
    public final boolean isValidForParser() { return PSPKWLexersym.isValidForParser; }


    public interface IsNullable {
        public final static byte isNullable[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            2,1
        };
    };
    public final static byte prosthesesIndex[] = ProsthesesIndex.prosthesesIndex;
    public final int prosthesesIndex(int index) { return prosthesesIndex[index]; }

    public interface IsKeyword {
        public final static byte isKeyword[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static byte baseCheck[] = {0,
            4,7,4,5,7,8,4,4,6,3,
            6,5,8,4,7,7,12,7,5,4,
            5,9
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,25,25,7,23,43,49,
            22,44,47,52,16,55,53,30,56,12,
            54,61,62,63,64,66,67,71,31,75,
            68,77,80,37,83,86,85,38,90,94,
            87,96,98,100,101,102,103,106,107,110,
            114,119,115,122,123,125,126,128,132,134,
            133,136,139,143,145,138,146,149,148,153,
            150,161,155,164,166,167,168,171,172,175,
            176,178,177,179,184,180,185,188,194,196,
            198,199,200,201,204,205,207,213,215,217,
            210,154,218,125,125
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,0,7,2,9,
            10,0,12,13,14,0,16,2,18,8,
            5,0,0,1,0,10,4,6,7,0,
            0,0,2,4,19,6,0,0,2,3,
            3,4,0,0,14,21,0,5,0,1,
            4,0,0,0,0,0,13,6,4,4,
            0,0,0,0,1,0,0,0,15,17,
            0,5,5,11,0,10,0,17,8,0,
            6,20,0,4,0,0,0,5,3,0,
            1,5,16,0,1,0,12,0,3,0,
            0,0,0,1,3,0,0,1,0,0,
            13,12,3,0,0,15,11,4,0,5,
            2,0,0,1,0,0,1,0,4,8,
            3,0,0,0,2,0,1,0,0,8,
            7,3,0,1,0,0,9,0,0,0,
            6,3,0,0,0,6,9,4,13,7,
            0,7,2,0,1,0,0,0,3,2,
            0,0,6,2,0,0,0,0,0,0,
            6,11,5,0,0,10,8,0,9,6,
            14,7,5,0,1,0,1,0,0,0,
            0,3,2,0,0,2,0,1,11,0,
            6,12,0,1,0,1,0,0,9,2,
            0,5,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            125,36,32,33,31,27,125,29,39,34,
            37,125,28,26,38,125,30,50,35,58,
            49,125,125,41,125,52,40,45,44,125,
            125,125,67,55,51,56,125,125,72,73,
            78,77,125,125,68,124,125,42,125,43,
            47,125,125,125,125,125,46,48,53,57,
            125,125,125,125,63,125,125,125,59,54,
            125,65,135,62,125,64,125,60,66,125,
            69,61,125,71,125,125,125,74,75,125,
            79,145,70,125,80,125,76,125,81,125,
            125,125,125,139,85,125,125,87,125,125,
            82,83,88,125,125,84,86,89,125,132,
            133,125,125,91,125,125,128,125,92,90,
            93,125,125,125,146,125,144,125,125,126,
            94,95,125,96,125,125,98,125,125,125,
            97,137,125,125,125,102,100,122,99,101,
            125,129,103,125,104,125,125,125,105,107,
            125,125,106,109,125,125,125,125,125,125,
            110,108,134,125,125,136,112,125,114,113,
            111,143,115,125,141,125,140,125,125,125,
            125,117,127,125,125,118,125,138,116,125,
            119,130,125,131,125,147,125,125,121,142,
            125,120
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }
    public final int asb(int index) { return 0; }
    public final int asr(int index) { return 0; }
    public final int nasb(int index) { return 0; }
    public final int nasr(int index) { return 0; }
    public final int terminalIndex(int index) { return 0; }
    public final int nonterminalIndex(int index) { return 0; }
    public final int scopePrefix(int index) { return 0;}
    public final int scopeSuffix(int index) { return 0;}
    public final int scopeLhs(int index) { return 0;}
    public final int scopeLa(int index) { return 0;}
    public final int scopeStateSet(int index) { return 0;}
    public final int scopeRhs(int index) { return 0;}
    public final int scopeState(int index) { return 0;}
    public final int inSymb(int index) { return 0;}
    public final String name(int index) { return null; }
    public final int originalState(int state) { return 0; }
    public final int asi(int state) { return 0; }
    public final int nasi(int state) { return 0; }
    public final int inSymbol(int state) { return 0; }

    /**
     * assert(! goto_default);
     */
    public final int ntAction(int state, int sym) {
        return baseAction[state + sym];
    }

    /**
     * assert(! shift_default);
     */
    public final int tAction(int state, int sym) {
        int i = baseAction[state],
            k = i + sym;
        return termAction[termCheck[k] == sym ? k : i];
    }
    public final int lookAhead(int la_state, int sym) {
        int k = la_state + sym;
        return termAction[termCheck[k] == sym ? k : la_state];
    }
}
