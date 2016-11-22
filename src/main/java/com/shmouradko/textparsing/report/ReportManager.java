package com.shmouradko.textparsing.report;

import com.shmouradko.textparsing.composite.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by test on 16.11.2016.
 */
public class ReportManager {
    static Logger logger = LogManager.getLogger();

    public static void basicTransformationReport(TextComponent text){
        StringBuilder result = new StringBuilder();
        text.print(result);
        logger.info("Basic text transformation: Change math expressions to the result of its computation:");
        logger.info(result);
    }

    public static void deleteSpecialLexemeReport(TextComponent text, char symbol, int symbolLength) {
        StringBuilder result = new StringBuilder();
        text.print(result);
        logger.info("Functionality 1: Delete lexemes in text which have the first element = {} and have length = {}:", symbol, symbolLength);
        logger.info(result);
    }

    public static void changeLexemeAtSentenceReport(TextComponent text){
        StringBuilder result = new StringBuilder();
        text.print(result);
        logger.info("Functionality 2: Change first lexeme with last lexeme at sentences:");
        logger.info(result);
    }

    public static void sortSentenceByLexemeReport(TextComponent text){
        StringBuilder result = new StringBuilder();
        text.print(result);
        logger.info("Functionality 3: Sorting sentences in order to growing count of lexeme there:");
        logger.info(result);
    }

}
