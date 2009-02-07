package org.eclipse.imp.presentation.parser;

public class PSPParserprs implements lpg.runtime.ParseTable, PSPParsersym {
    public final static int ERROR_SYMBOL = 42;
    public final int getErrorSymbol() { return ERROR_SYMBOL; }

    public final static int SCOPE_UBOUND = 0;
    public final int getScopeUbound() { return SCOPE_UBOUND; }

    public final static int SCOPE_SIZE = 1;
    public final int getScopeSize() { return SCOPE_SIZE; }

    public final static int MAX_NAME_LENGTH = 25;
    public final int getMaxNameLength() { return MAX_NAME_LENGTH; }

    public final static int NUM_STATES = 80;
    public final int getNumStates() { return NUM_STATES; }

    public final static int NT_OFFSET = 42;
    public final int getNtOffset() { return NT_OFFSET; }

    public final static int LA_STATE_OFFSET = 358;
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }

    public final static int MAX_LA = 1;
    public final int getMaxLa() { return MAX_LA; }

    public final static int NUM_RULES = 91;
    public final int getNumRules() { return NUM_RULES; }

    public final static int NUM_NONTERMINALS = 53;
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }

    public final static int NUM_SYMBOLS = 95;
    public final int getNumSymbols() { return NUM_SYMBOLS; }

    public final static int SEGMENT_SIZE = 8192;
    public final int getSegmentSize() { return SEGMENT_SIZE; }

    public final static int START_STATE = 155;
    public final int getStartState() { return START_STATE; }

    public final static int IDENTIFIER_SYMBOL = 0;
    public final int getIdentifier_SYMBOL() { return IDENTIFIER_SYMBOL; }

    public final static int EOFT_SYMBOL = 35;
    public final int getEoftSymbol() { return EOFT_SYMBOL; }

    public final static int EOLT_SYMBOL = 35;
    public final int getEoltSymbol() { return EOLT_SYMBOL; }

    public final static int ACCEPT_ACTION = 266;
    public final int getAcceptAction() { return ACCEPT_ACTION; }

    public final static int ERROR_ACTION = 267;
    public final int getErrorAction() { return ERROR_ACTION; }

    public final static boolean BACKTRACK = false;
    public final boolean getBacktrack() { return BACKTRACK; }

    public final int getStartSymbol() { return lhs(0); }
    public final boolean isValidForParser() { return PSPParsersym.isValidForParser; }


    public interface IsNullable {
        public final static byte isNullable[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            1,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,1,0,0,
            0,0,0,0,0,1,0,0,0,0,
            1,0,0,0,0,1,0,0,1,0,
            0,0,0,0,0
        };
    };
    public final static byte isNullable[] = IsNullable.isNullable;
    public final boolean isNullable(int index) { return isNullable[index] != 0; }

    public interface ProsthesesIndex {
        public final static byte prosthesesIndex[] = {0,
            22,27,50,51,8,18,6,9,10,13,
            14,15,16,17,19,21,25,26,33,34,
            48,49,53,2,3,4,5,7,11,12,
            20,23,24,28,29,30,31,32,35,36,
            37,38,39,40,41,42,43,44,45,46,
            47,52,1
        };
    };
    public final static byte prosthesesIndex[] = ProsthesesIndex.prosthesesIndex;
    public final int prosthesesIndex(int index) { return prosthesesIndex[index]; }

    public interface IsKeyword {
        public final static byte isKeyword[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static byte baseCheck[] = {0,
            3,3,0,2,3,1,3,1,1,1,
            2,4,2,0,3,1,2,1,1,1,
            1,1,1,4,1,2,3,6,1,3,
            2,1,1,3,5,5,0,2,1,1,
            3,1,3,1,1,1,1,1,1,5,
            0,2,1,1,1,4,4,4,4,0,
            2,1,1,6,0,2,1,1,4,4,
            4,1,2,1,3,1,1,1,1,1,
            1,1,1,1,1,1,1,4,1,3,
            3,-15,0,-29,0,-2,-28,0,-25,0,
            -61,0,0,0,0,0,0,-34,-5,0,
            0,0,-17,0,-35,0,0,-62,0,0,
            0,0,0,0,0,0,0,0,-36,0,
            0,0,0,-6,0,0,0,-30,0,0,
            0,0,0,0,-49,0,0,-8,-55,0,
            0,0,0,-59,-1,0,0,0,0,0,
            -48,0,0,0,0,0,0,0,-71,-3,
            0,0,0,-72,-26,0,0,0,0,0,
            -4,-9,-74,0,-14,0,0,0,0,0,
            0,0,-7,-10,-11,0,-13,-18,0,-24,
            0,0,-32,0,-50,0,0,-56,-57,-58,
            -68,-69,0,-70,-12,-80,-16,-19,0,-20,
            0,-21,-22,-23,-27,0,-31,-33,-37,-38,
            -39,0,0,0,0,-40,-41,0,-42,-43,
            -44,-45,-46,0,-47,-51,0,0,-52,-53,
            -54,-60,-63,-64,-65,0,-66,-67,-73,-75,
            -76,-77,-78,-79,0
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            24,24,25,26,26,28,7,7,5,5,
            27,27,8,9,9,29,30,30,10,10,
            10,10,10,10,14,31,31,16,15,32,
            32,17,18,18,33,6,11,34,34,35,
            35,37,38,38,19,19,19,19,20,20,
            36,39,39,40,40,40,41,42,43,12,
            44,44,45,45,46,47,47,48,48,49,
            50,13,51,51,21,22,1,1,1,1,
            1,1,2,2,2,2,3,3,4,52,
            52,23,15,223,35,230,14,113,22,130,
            62,73,16,18,19,20,21,23,51,61,
            237,82,85,1,223,59,32,10,81,22,
            72,74,113,17,18,19,20,21,23,47,
            230,129,29,241,93,39,193,4,28,61,
            63,52,53,54,55,59,32,240,63,51,
            138,252,82,85,51,5,91,82,85,73,
            74,148,30,241,38,40,66,67,68,51,
            6,262,82,85,51,132,263,82,85,96,
            170,2,2,62,89,2,6,6,134,215,
            6,25,217,13,2,75,109,83,75,7,
            74,11,42,99,197,160,203,224,159,193,
            148,181,116,250,116,94,62,110,118,26,
            125,264,126,121,129,86,12,133,135,138,
            140,143,260,90,261,43,145,146,28,151,
            100,154,152,156,94,158,164,101,259,165,
            101,17,169,170,171,173,118,174,177,180,
            185,186,187,188,105,267,267
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,0,1,3,0,0,6,7,8,9,
            10,11,0,0,0,15,0,17,18,19,
            6,7,8,9,10,11,25,0,16,15,
            3,17,18,19,0,1,32,3,22,12,
            6,7,8,9,10,11,0,1,35,3,
            0,1,6,7,8,9,10,11,0,1,
            0,0,0,1,6,7,8,9,10,11,
            20,21,0,0,0,3,16,27,6,7,
            0,9,0,3,2,0,36,26,8,29,
            5,30,0,0,33,34,4,4,0,0,
            0,3,28,3,0,13,13,3,23,0,
            12,31,0,14,14,0,1,0,14,2,
            0,1,13,24,0,0,2,2,0,0,
            2,0,0,1,0,1,21,0,1,0,
            1,12,0,12,0,0,4,0,4,4,
            0,0,2,0,1,0,5,0,0,0,
            5,2,5,0,0,2,2,20,0,0,
            0,3,0,0,5,5,0,5,5,0,
            0,5,2,4,0,0,0,0,4,4,
            4,4,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            267,267,275,282,267,3,345,344,343,346,
            348,347,1,267,267,222,267,220,218,198,
            345,344,343,346,348,347,276,267,148,222,
            338,220,218,198,267,229,181,326,154,227,
            345,344,343,346,348,347,267,242,266,303,
            267,350,345,344,343,346,348,347,267,300,
            267,267,267,195,345,344,343,346,348,347,
            351,353,267,60,14,317,148,239,254,255,
            267,253,267,331,92,267,354,312,257,182,
            108,313,267,267,311,314,269,272,267,267,
            267,291,185,355,267,194,194,308,115,13,
            228,258,37,145,210,267,315,267,216,200,
            267,225,194,205,267,267,99,175,267,267,
            97,267,267,231,267,236,316,267,243,267,
            245,227,267,228,267,267,342,267,294,302,
            267,267,161,267,298,267,246,267,51,267,
            249,149,108,267,267,208,209,251,267,267,
            267,301,267,267,211,212,267,214,169,267,
            267,174,183,325,267,267,267,267,324,323,
            337,336,65
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }

    public interface Asb {
        public final static byte asb[] = {0,
            25,41,29,32,29,19,40,53,32,32,
            43,19,65,32,8,64,7,43,65,65,
            65,53,65,46,23,23,67,46,46,22,
            53,22,53,1,47,46,53,53,20,20,
            20,65,70,53,68,68,68,27,47,65,
            65,65,73,76,1,55,60,27,1,58,
            55,60,68,68,68,68,68,65,4,4,
            1,1,20,35,20,20,20,20,73,35
        };
    };
    public final static byte asb[] = Asb.asb;
    public final int asb(int index) { return asb[index]; }

    public interface Asr {
        public final static byte asr[] = {0,
            36,27,20,1,21,0,3,8,7,6,
            9,11,10,15,17,18,19,0,13,4,
            0,3,12,0,32,0,20,0,29,16,
            0,1,25,0,33,26,30,34,0,16,
            35,0,2,28,0,3,8,7,6,9,
            11,10,1,0,6,7,9,3,0,3,
            8,31,0,13,2,0,23,5,0,24,
            14,0,3,14,0,22,0
        };
    };
    public final static byte asr[] = Asr.asr;
    public final int asr(int index) { return asr[index]; }

    public interface Nasb {
        public final static byte nasb[] = {0,
            34,33,39,43,15,33,47,33,43,49,
            51,33,53,43,1,33,18,51,33,33,
            33,33,33,55,9,41,33,7,4,29,
            33,57,33,13,21,26,33,33,33,33,
            33,33,33,33,33,33,33,37,31,59,
            33,33,33,33,13,61,63,65,13,33,
            11,24,33,33,33,33,33,67,69,69,
            13,13,33,45,33,33,33,33,33,71
        };
    };
    public final static byte nasb[] = Nasb.nasb;
    public final int nasb(int index) { return nasb[index]; }

    public interface Nasr {
        public final static byte nasr[] = {0,
            1,30,0,1,45,0,34,0,51,0,
            40,0,2,0,27,28,0,1,10,0,
            18,32,0,48,0,1,35,0,21,0,
            18,17,0,25,24,0,52,0,26,0,
            31,0,7,0,38,0,8,0,5,0,
            9,0,29,0,44,0,16,0,33,0,
            39,0,47,0,23,0,37,0,20,0,
            19,0
        };
    };
    public final static byte nasr[] = Nasr.nasr;
    public final int nasr(int index) { return nasr[index]; }

    public interface TerminalIndex {
        public final static byte terminalIndex[] = {0,
            38,2,3,10,6,17,20,21,32,15,
            23,27,11,12,19,26,28,30,33,40,
            42,1,4,5,9,14,16,18,22,24,
            25,29,31,35,36,39,7,8,34,37,
            41,43
        };
    };
    public final static byte terminalIndex[] = TerminalIndex.terminalIndex;
    public final int terminalIndex(int index) { return terminalIndex[index]; }

    public interface NonterminalIndex {
        public final static byte nonterminalIndex[] = {0,
            56,61,0,0,49,0,47,50,0,53,
            0,0,0,0,0,55,59,60,65,66,
            71,0,73,44,45,0,46,48,51,52,
            54,57,58,0,62,0,63,64,0,67,
            0,0,0,0,68,0,0,69,0,0,
            70,72,0
        };
    };
    public final static byte nonterminalIndex[] = NonterminalIndex.nonterminalIndex;
    public final int nonterminalIndex(int index) { return nonterminalIndex[index]; }

    public interface ScopePrefix {
        public final static byte scopePrefix[] = {
            1
        };
    };
    public final static byte scopePrefix[] = ScopePrefix.scopePrefix;
    public final int scopePrefix(int index) { return scopePrefix[index]; }

    public interface ScopeSuffix {
        public final static byte scopeSuffix[] = {
            5
        };
    };
    public final static byte scopeSuffix[] = ScopeSuffix.scopeSuffix;
    public final int scopeSuffix(int index) { return scopeSuffix[index]; }

    public interface ScopeLhs {
        public final static byte scopeLhs[] = {
            4
        };
    };
    public final static byte scopeLhs[] = ScopeLhs.scopeLhs;
    public final int scopeLhs(int index) { return scopeLhs[index]; }

    public interface ScopeLa {
        public final static byte scopeLa[] = {
            3
        };
    };
    public final static byte scopeLa[] = ScopeLa.scopeLa;
    public final int scopeLa(int index) { return scopeLa[index]; }

    public interface ScopeStateSet {
        public final static byte scopeStateSet[] = {
            1
        };
    };
    public final static byte scopeStateSet[] = ScopeStateSet.scopeStateSet;
    public final int scopeStateSet(int index) { return scopeStateSet[index]; }

    public interface ScopeRhs {
        public final static byte scopeRhs[] = {0,
            94,2,27,0,3,0
        };
    };
    public final static byte scopeRhs[] = ScopeRhs.scopeRhs;
    public final int scopeRhs(int index) { return scopeRhs[index]; }

    public interface ScopeState {
        public final static char scopeState[] = {0,
            174,169,154,149,108,0
        };
    };
    public final static char scopeState[] = ScopeState.scopeState;
    public final int scopeState(int index) { return scopeState[index]; }

    public interface InSymb {
        public final static byte inSymb[] = {0,
            0,66,67,32,68,49,69,16,29,13,
            1,49,51,28,2,49,72,19,18,17,
            15,43,51,2,2,2,1,2,86,93,
            12,73,12,5,23,76,1,43,1,1,
            44,27,74,60,1,1,1,2,14,24,
            5,5,94,20,2,2,2,14,22,44,
            81,89,9,6,7,8,31,5,5,5,
            5,5,79,2,62,62,44,44,80,14
        };
    };
    public final static byte inSymb[] = InSymb.inSymb;
    public final int inSymb(int index) { return inSymb[index]; }

    public interface Name {
        public final static String name[] = {
            "",
            "->",
            "{",
            "}",
            "(",
            ")",
            "=",
            "+",
            "-",
            "*",
            ";",
            ".",
            ",",
            "$empty",
            "BOLD",
            "BOOLEAN",
            "CASE",
            "COLOR",
            "EXTENDS",
            "FOLDABLE",
            "FONT",
            "ICON",
            "IMPORT",
            "INT",
            "ITALIC",
            "LABEL",
            "LANGUAGE",
            "NODE",
            "OUTLINE",
            "PACKAGE",
            "PRESENTATION",
            "REGULAR",
            "STYLE",
            "TEXT",
            "TOKEN",
            "UNDERLINE",
            "EOF_TOKEN",
            "COLON",
            "IDENTIFIER",
            "INTEGER",
            "JAVA_EXPR",
            "SINGLE_LINE_COMMENT",
            "STRING_LITERAL",
            "ERROR_TOKEN",
            "compilationUnit",
            "packageSpec",
            "languageSpecList",
            "qualifiedName",
            "importSpec",
            "simpleName",
            "languageSpec",
            "languageBody",
            "languageMembers",
            "languageMember",
            "foldableNodes",
            "foldableNode",
            "primitiveType",
            "formalArgList",
            "functionBody",
            "formalArg",
            "typeName",
            "expression",
            "textColoringMember",
            "styleSet",
            "styleList",
            "styleKey",
            "resourceValue",
            "textAttributeDecl",
            "presentationMember",
            "nodePresentationAttribute",
            "outlineSpecMembers",
            "outlineSpecMember",
            "alternativeList",
            "alternative"
        };
    };
    public final static String name[] = Name.name;
    public final String name(int index) { return name[index]; }

    public final int originalState(int state) {
        return -baseCheck[state];
    }
    public final int asi(int state) {
        return asb[originalState(state)];
    }
    public final int nasi(int state) {
        return nasb[originalState(state)];
    }
    public final int inSymbol(int state) {
        return inSymb[originalState(state)];
    }

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
