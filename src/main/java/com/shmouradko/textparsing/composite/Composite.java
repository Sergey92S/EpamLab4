package com.shmouradko.textparsing.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by test on 02.11.2016.
 */
public class Composite extends TextComponent{
    private List<TextComponent> textParts = new ArrayList<>();

    @Override
    public void print(StringBuilder text) {
        textParts.forEach(textPart -> textPart.print(text));
    }

    @Override
    public TextComponent addElement(TextComponent element) {
        textParts.add(element);
        return element;
    }

    @Override
    public List<TextComponent> defineElement() {
        return textParts;
    }

}
