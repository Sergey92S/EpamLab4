package com.shmouradko.textparsing.handle;

import com.shmouradko.textparsing.composite.Composite;
import com.shmouradko.textparsing.composite.TextComponent;
import com.shmouradko.textparsing.composite.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by test on 09.11.2016.
 */
public class SentenceHandler extends AbstractHandler {

    @Override
    public TextComponent handle(TextComponent component, String parseText, List<String> text) {
        List<String> resultText = new ArrayList<>(text);
        Pattern patternSentence = Pattern.compile(Type.SENTENCE.getRegexForSplit());
        Matcher matcher;
        cleanText(text, resultText.size());
        TextComponent sentence = new Composite();
        matcher = patternSentence.matcher(parseText);
        while (matcher.find()){
            text.add(matcher.group());
            sentence.addElement(new Composite());
        }
        component.defineElement().set(resultText.indexOf(parseText), sentence);
        return sentence;
    }

}
