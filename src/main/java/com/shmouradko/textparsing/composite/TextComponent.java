package com.shmouradko.textparsing.composite;

import java.util.List;

/**
 * Created by test on 02.11.2016.
 */
public abstract class TextComponent{

    public abstract void print(StringBuilder text);

    public abstract TextComponent addElement(TextComponent element);

    public abstract List<TextComponent> defineElement();

}
