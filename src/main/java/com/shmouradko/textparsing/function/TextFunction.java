package com.shmouradko.textparsing.function;

import com.shmouradko.textparsing.composite.*;

import java.util.Collections;

/**
 * Created by test on 02.11.2016.
 */
public class TextFunction {

    public static TextComponent deleteSpecialLexeme(TextComponent inputText, char symbol, int symbolLength){
        TextComponent text = createCopy(inputText);
        for(TextComponent paragraph: text.defineElement()){
            for(TextComponent sentence: paragraph.defineElement()){
                for(int i = 0; i < sentence.defineElement().size(); i++){
                    if(!(sentence.defineElement().get(i) instanceof Sign)){
                        if(sentence.defineElement().get(i) instanceof Word){
                            if(((Word)sentence.defineElement().get(i)).getWord().length()==symbolLength && ((Word)sentence.defineElement().get(i)).getWord().charAt(0)==symbol){
                                sentence.defineElement().remove(sentence.defineElement().get(i));
                                --i;
                            }
                        }else if(sentence.defineElement().get(i) instanceof MathExpression){
                            if(((MathExpression)sentence.defineElement().get(i)).getExpression().length()==symbolLength && ((MathExpression)sentence.defineElement().get(i)).getExpression().charAt(0)==symbol){
                                sentence.defineElement().remove(sentence.defineElement().get(i));
                                --i;
                            }
                        }
                    }
                }
            }
        }
        return text;
    }

    public static TextComponent changeLexemeAtSentence(TextComponent inputText){
        TextComponent text = createCopy(inputText);
        for(TextComponent paragraph: text.defineElement()){
            for(TextComponent sentence: paragraph.defineElement()){
                int firstLexeme = sentence.defineElement().size();
                int lastLexeme = 0;
                for(int i = 0; i<sentence.defineElement().size(); i++){
                    if(!(sentence.defineElement().get(i) instanceof Sign)) {
                        if(firstLexeme>i){
                            firstLexeme = i;
                        }
                        if(lastLexeme<i){
                            lastLexeme = i;
                        }
                    }
                }
                TextComponent bufferComponent = sentence.defineElement().get(firstLexeme);
                sentence.defineElement().set(firstLexeme, sentence.defineElement().get(lastLexeme));
                sentence.defineElement().set(lastLexeme, bufferComponent);
                if(sentence.defineElement().get(firstLexeme) instanceof Word){
                    ((Word)sentence.defineElement().get(firstLexeme)).setIsNearSign(false);
                }
                if(sentence.defineElement().get(lastLexeme) instanceof Word){
                    ((Word)sentence.defineElement().get(lastLexeme)).setIsNearSign(true);
                }
                if(sentence.defineElement().get(firstLexeme) instanceof MathExpression){
                    ((MathExpression)sentence.defineElement().get(firstLexeme)).setIsNearSign(false);
                }
                if(sentence.defineElement().get(lastLexeme) instanceof MathExpression){
                    ((MathExpression)sentence.defineElement().get(lastLexeme)).setIsNearSign(true);
                }
            }
        }
        return text;
    }

    public static TextComponent sortSentenceByLexeme(TextComponent text){
        TextComponent sortedText = new Composite();
        int sentenceSize;
        int lexemeCount = 0;
        int i = 0;
        do {
            sentenceSize = 0;
            sortedText.addElement(new Composite());
            for (TextComponent paragraph : text.defineElement()) {
                sentenceSize = sentenceSize + paragraph.defineElement().size();

                for (TextComponent sentence : paragraph.defineElement()) {

                    for(TextComponent word: sentence.defineElement()){
                        if (!(word instanceof Sign)) {
                            lexemeCount = lexemeCount + 1;
                        }
                    }
                    if (lexemeCount>sortedText.defineElement().get(i).defineElement().size() && sortedText.defineElement().indexOf(sentence)==-1) {
                        sortedText.defineElement().set(i, sentence);
                    }
                    lexemeCount = 0;

                }
            }
            i++;
        } while(i < sentenceSize);
        Collections.reverse(sortedText.defineElement());
        return sortedText;
    }

    private static TextComponent createCopy(TextComponent inputText){
        TextComponent newText = new Composite();
        for(TextComponent paragraph: inputText.defineElement()){
            TextComponent newParagraph = new Composite();
            newText.addElement(newParagraph);
            for(TextComponent sentence: paragraph.defineElement()){
                TextComponent newSentence = new Composite();
                newParagraph.addElement(newSentence);
                for(TextComponent word: sentence.defineElement()){
                    TextComponent newWord = null;
                    if(word instanceof Sign){
                        newWord = ((Sign)word).clone();
                    }
                    if(word instanceof Word){
                        newWord = ((Word)word).clone();
                    }
                    if(word instanceof MathExpression){
                        newWord = ((MathExpression)word).clone();
                    }
                    if(newWord != null){
                        newSentence.addElement(newWord);
                    }
                }
            }
        }
        return newText;
    }

}
