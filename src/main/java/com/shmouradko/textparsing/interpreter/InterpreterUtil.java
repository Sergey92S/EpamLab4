package com.shmouradko.textparsing.interpreter;

/**
 * Created by test on 10.11.2016.
 */
public class InterpreterUtil {

    public static boolean isOperator(String s) {
        if ("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static Interpreter getOperator(String s, Interpreter left, Interpreter right) {
        switch (s) {
            case "+":
                return new AddInterpreter(left, right);
            case "-":
                return new SubstractInterpreter(left, right);
            case "*":
                return new ProductInterpreter(left, right);
            case "/":
                return new DivisionInterpreter(left, right);
        }
        return null;
    }


}
