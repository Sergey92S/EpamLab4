package com.shmouradko.textparsing.handle;

import com.shmouradko.textparsing.composite.Composite;
import com.shmouradko.textparsing.composite.TextComponent;
import com.shmouradko.textparsing.composite.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by test on 09.11.2016.
 */
public class ParagraphHandler extends AbstractHandler {
    private static final int FIRST_PARAGRAPH = 1;

    @Override
    public TextComponent handle(TextComponent component, String parseText, List<String> text) {
        List<String> resultText = new ArrayList<>(text);
        Pattern patternParagraph = Pattern.compile(Type.PARAGRAPH.getRegexForSplit());
        String[] results;
        cleanText(text, resultText.size());
        TextComponent paragraph = new Composite();
        results = patternParagraph.split(parseText);
        for(int j = FIRST_PARAGRAPH; j < results.length; j++){
            text.add(results[j]);
            paragraph.addElement(new Composite());
        }
        component.defineElement().set(resultText.indexOf(parseText), paragraph);
        return paragraph;
    }

}
