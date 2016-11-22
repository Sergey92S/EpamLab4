package com.shmouradko.textparsing.handle;

import com.shmouradko.textparsing.composite.TextComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by test on 09.11.2016.
 */
public abstract class AbstractHandler {
    private AbstractHandler successor;

    abstract public TextComponent handle(TextComponent component, String parseText, List<String> text);

    public void chain(TextComponent component, List<String> text){
        if(successor!=null){
            for(int i = 0; i < component.defineElement().size(); i++){
                List<String> buffer = new ArrayList<>(text);
                successor.chain(handle(component, text.get(i), text), text);
                text = buffer;
            }
        }
    }

    public AbstractHandler getSuccessor() {
        return successor;
    }

    public AbstractHandler setSuccessor(AbstractHandler successor) {
        this.successor = successor;
        return this;
    }

    protected void cleanText(List<String> text, int textLength){
        for(int i = 0; i < textLength; i++){
            text.remove(0);
        }
    }

}
