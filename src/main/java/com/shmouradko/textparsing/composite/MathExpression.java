package com.shmouradko.textparsing.composite;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by test on 14.11.2016.
 */
public class MathExpression extends TextComponent implements Cloneable {
    static Logger logger = LogManager.getLogger();
    private static final String SPACE_AFTER_EXPRESSION = " ";
    private String expression;
    private boolean isNearSign;

    public MathExpression(String expression, boolean isNearSign){
        setExpression(expression);
        setIsNearSign(isNearSign);
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setIsNearSign(boolean isNearSign) {
        this.isNearSign = isNearSign;
    }

    public String getExpression() {
        return expression;
    }

    public boolean getIsNearSign() {
        return isNearSign;
    }

    @Override
    public String toString() {
        return "MathExpression{" +
                "expression='" + expression + '\'' +
                ", isNearSign=" + isNearSign +
                '}';
    }

    @Override
    public MathExpression clone() {
        MathExpression copy;
        try {
            copy = (MathExpression) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.log(Level.ERROR, "Clone not supported");
            throw new RuntimeException();
        }
        return copy;
    }

    @Override
    public void print(StringBuilder text) {
        expression = ExpressionConverter.defineUnaryOperation(expression);
        expression = ExpressionConverter.convertExpression(expression);
        if(isNearSign){
            text.append(expression);
        }else{
            text.append(expression+SPACE_AFTER_EXPRESSION);
        }
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
