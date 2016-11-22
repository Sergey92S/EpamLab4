package com.shmouradko.textparsing.handle;

import com.shmouradko.textparsing.composite.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by test on 09.11.2016.
 */
public class WordHandler extends AbstractHandler {
    private static final String SPACE = " ";

    @Override
    public TextComponent handle(TextComponent component, String parseText, List<String> text) {
        List<String> resultText = new ArrayList<>(text);
        Pattern patternSign = Pattern.compile(Type.SIGN.getRegexForSplit());
        Pattern patternWord = Pattern.compile(Type.WORD.getRegexForSplit());
        Pattern patternExpression = Pattern.compile(Type.EXPRESSION.getRegexForSplit());
        Matcher matcher;
        String[] results;
        List<String> sign = new ArrayList<>();
        boolean isNearSign;
        cleanText(text, resultText.size());
        TextComponent wordList = new Composite();
        results = parseText.split(SPACE);

        for(int j = 0; j < results.length; j++) {
            matcher = patternSign.matcher(results[j]);
            while(matcher.find()){
                sign.add(matcher.group());
                if(results[j].indexOf(sign.get(sign.size()-1))==0){
                    wordList.addElement(new Sign(sign.get(sign.size()-1), true));
                    sign.remove(sign.size()-1);
                }
            }

            if ((matcher = patternWord.matcher(results[j])).find()) {
                isNearSign = sign.size()>0 ? true : false;
                wordList.addElement(new Word(matcher.group(), isNearSign));
                while(sign.size()>0){
                    isNearSign = sign.size()>1 ? true : false;
                    wordList.addElement(new Sign(sign.get(0), isNearSign));
                    sign.remove(0);
                }
            }

            if ((matcher = patternExpression.matcher(results[j])).find()) {
                wordList.addElement(new MathExpression(matcher.group(), false));
            }

        }

        component.defineElement().set(resultText.indexOf(parseText), wordList);
        return component;
    }

}