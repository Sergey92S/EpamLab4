package com.shmouradko.textparsing.runner;

import com.shmouradko.textparsing.composite.Composite;
import com.shmouradko.textparsing.composite.TextComponent;
import com.shmouradko.textparsing.function.TextFunction;
import com.shmouradko.textparsing.handle.ParagraphHandler;
import com.shmouradko.textparsing.handle.SentenceHandler;
import com.shmouradko.textparsing.handle.WordHandler;
import com.shmouradko.textparsing.report.ReportManager;
import com.shmouradko.textparsing.scanner.TextScanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by test on 02.11.2016.
 */
public class Runner {
    private static final char SYMBOL = 'I';
    private static final int SYMBOL_LENGTH = 2;

    public static void main(String[] args) {

        TextComponent text = new Composite();
        text.addElement(new Composite());
        List<String> parseText = new ArrayList<>();
        parseText.add(TextScanner.scanFile());
        (new ParagraphHandler().setSuccessor(new SentenceHandler().setSuccessor(new WordHandler().setSuccessor(new WordHandler())))).chain(text, parseText);
        ReportManager.basicTransformationReport(text);
        ReportManager.deleteSpecialLexemeReport(TextFunction.deleteSpecialLexeme(text.defineElement().get(0), SYMBOL, SYMBOL_LENGTH), SYMBOL, SYMBOL_LENGTH);
        ReportManager.changeLexemeAtSentenceReport(TextFunction.changeLexemeAtSentence(text.defineElement().get(0)));
        ReportManager.sortSentenceByLexemeReport(TextFunction.sortSentenceByLexeme(text.defineElement().get(0)));

    }
}
