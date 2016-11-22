package com.shmouradko.textparsing.composite;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Created by test on 09.11.2016.
 */
public class Word extends TextComponent implements Cloneable {
    static Logger logger = LogManager.getLogger();
    private static final String SPACE_AFTER_WORD = " ";
    private String word;
    private boolean isNearSign;

    public Word(String word, boolean isNearSignWord){
        setWord(word);
        setIsNearSign(isNearSignWord);
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setIsNearSign(boolean isNearSign) {
        this.isNearSign = isNearSign;
    }

    public String getWord() {
        return word;
    }

    public boolean getIsNearSign() {
        return isNearSign;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", isNearSign=" + isNearSign +
                '}';
    }

    @Override
    public void print(StringBuilder text) {
        if(isNearSign){
            text.append(word);
        }else{
            text.append(word+SPACE_AFTER_WORD);
        }
    }

    @Override
    public Word clone() {
        Word copy;
        try {
            copy = (Word) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.log(Level.ERROR, "Clone not supported");
            throw new RuntimeException();
        }
        return copy;
    }

    @Override
    public TextComponent addElement(TextComponent element) {
        logger.log(Level.ERROR, "This method is unused");
        throw new RuntimeException();
    }

    @Override
    public List<TextComponent> defineElement() {
        logger.log(Level.ERROR, "This method is unused");
        throw new RuntimeException();
    }
}
