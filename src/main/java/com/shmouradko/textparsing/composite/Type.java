package com.shmouradko.textparsing.composite;

/**
 * Created by test on 02.11.2016.
 */
public enum Type {
    PARAGRAPH, SENTENCE, WORD, SIGN, EXPRESSION;

    private String RegexForSplit;

    static {
        PARAGRAPH.RegexForSplit = "\\s{4}|\\t";
        SENTENCE.RegexForSplit = "\\s*[^.!?]*[.!?]";
        WORD.RegexForSplit = "[\\p{Lower}\\p{Upper}–]+";
        SIGN.RegexForSplit = "[,.!'?]";
        EXPRESSION.RegexForSplit = "[\\d()*/+-]+";
    }

    public String getRegexForSplit() {
        return RegexForSplit;
    }
}
