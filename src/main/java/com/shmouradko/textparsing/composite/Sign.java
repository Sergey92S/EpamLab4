package com.shmouradko.textparsing.composite;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by test on 14.11.2016.
 */
public class Sign extends TextComponent implements Cloneable{
    static Logger logger = LogManager.getLogger();
    private static final String SPACE_AFTER_SIGN = " ";
    private String sign;
    private boolean isNearSign;

    public Sign(String sign, boolean isNearSign){
        setSign(sign);
        setIsNearSign(isNearSign);
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setIsNearSign(boolean isNearSign) {
        this.isNearSign = isNearSign;
    }

    public String getSign() {
        return sign;
    }

    public boolean getIsNearSign() {
        return isNearSign;
    }

    @Override
    public String toString() {
        return "Sign{" +
                "sign='" + sign + '\'' +
                ", isNearSign=" + isNearSign +
                '}';
    }

    @Override
    public void print(StringBuilder text) {
        if(isNearSign){
            text.append(sign);
        }else{
            text.append(sign+SPACE_AFTER_SIGN);
        }
    }

    @Override
    public Sign clone() {
        Sign copy;
        try {
            copy = (Sign) super.clone();
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
