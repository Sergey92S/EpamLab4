package com.shmouradko.textparsing.composite;

import com.shmouradko.textparsing.interpreter.Interpreter;
import com.shmouradko.textparsing.interpreter.InterpreterUtil;
import com.shmouradko.textparsing.interpreter.NumberInterpreter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by test on 14.11.2016.
 */
public class ExpressionConverter {
    private static final String DIGITAL_REG_EXP = "^-?\\d+$";
    private static final String UNARY_REG_EXP = "[\\d]+[+|-]{2}|[+|-]{2}[\\d]+";
    private static final String EXPRESSION_ELEMENT_REG_EXP = "[\\d]+|(?<=\\()[-{1}][\\d]+|[\\(\\)\\*/\\+\\-]";
    private static final int FIFTH_PRIORITY = 5;
    private static final int FOURTH_PRIORITY = 4;
    private static final int ZERO_PRIORITY = 0;
    private static final int MIN_EXPRESSION_LENGTH = 3;

    public static String defineUnaryOperation(String expression){
        Pattern pattern = Pattern.compile(UNARY_REG_EXP);
        Matcher matcher = pattern.matcher(expression);
        while(matcher.find()){
            String element = matcher.group();
            expression = expression.replace(element, String.valueOf(ExpressionConverter.defineDigital(element.toCharArray())));
        }
        return expression;
    }

    public static String convertExpression(String expression){
        if(expression.length()<MIN_EXPRESSION_LENGTH){
            return expression;
        }
        Pattern pattern = Pattern.compile(EXPRESSION_ELEMENT_REG_EXP);
        Matcher matcher = pattern.matcher(expression);
        List<String> infixNotation = new ArrayList<>();
        while (matcher.find()){
            infixNotation.add(matcher.group());
        }
        List<String> tokenArray = convertInfixToRPN(infixNotation);
        Deque<Interpreter> resultStack = new ArrayDeque<>();
        for (String s : tokenArray) {
            if (InterpreterUtil.isOperator(s)) {
                Interpreter rightExpression = resultStack.pop();
                Interpreter leftExpression = resultStack.pop();
                Interpreter operator = InterpreterUtil.getOperator(s, leftExpression,rightExpression);
                resultStack.push(new NumberInterpreter(operator.interpret()));
            } else {
                Interpreter i = new NumberInterpreter(Integer.parseInt(s));
                resultStack.push(i);
            }
        }
        return ((Integer)(int)resultStack.pop().interpret()).toString();
    }

    private static List<String> convertInfixToRPN(List<String> infixNotation){
        Map<String, Integer> prededence = new HashMap<>();
        prededence.put("/", FIFTH_PRIORITY);
        prededence.put("*", FIFTH_PRIORITY);
        prededence.put("+", FOURTH_PRIORITY);
        prededence.put("-", FOURTH_PRIORITY);
        prededence.put("(", ZERO_PRIORITY);

        List<String> resultQueue = new LinkedList<>();
        Deque<String> operatorStack = new ArrayDeque<>();

        for (String token : infixNotation) {
            if ("(".equals(token)) {
                operatorStack.push(token);
                continue;
            }

            if (")".equals(token)) {
                while (!"(".equals(operatorStack.peek())) {
                    resultQueue.add(operatorStack.pop());
                }
                operatorStack.pop();
                continue;
            }

            if (prededence.containsKey(token)) {
                while (!operatorStack.isEmpty() && prededence.get(token) <= prededence.get(operatorStack.peek())) {
                    resultQueue.add(operatorStack.pop());
                }
                operatorStack.push(token);
                continue;
            }

            if (isNumber(token)) {
                resultQueue.add(token);
                continue;
            }

        }

        while (!operatorStack.isEmpty()) {
            resultQueue.add(operatorStack.pop());
        }

        return resultQueue;
    }

    private static boolean isNumber(String token) {
        return token.matches(DIGITAL_REG_EXP) ? true : false;
    }

    private static int defineDigital(char[] element){
        boolean isPlus = false;
        int digital = 0;

        for(int i = 0; i < element.length; i++) {

            if(String.valueOf(element[i]).matches(DIGITAL_REG_EXP)){
                digital = Integer.parseInt(String.valueOf(element[i]));
            }

            if ("+".equals(String.valueOf(element[i]))) {
                isPlus = true;
            }

        }
        return isPlus ? ++digital : --digital;
    }

}
