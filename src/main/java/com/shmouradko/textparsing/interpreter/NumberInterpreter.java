package com.shmouradko.textparsing.interpreter;

/**
 * Created by test on 10.11.2016.
 */
public class NumberInterpreter implements Interpreter {

    private final double number;

    public NumberInterpreter(double number){
        this.number = number;
    }

    @Override
    public double interpret() {
        return number;
    }


}
