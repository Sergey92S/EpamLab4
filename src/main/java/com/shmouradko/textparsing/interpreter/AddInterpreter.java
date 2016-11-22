package com.shmouradko.textparsing.interpreter;

/**
 * Created by test on 10.11.2016.
 */
public class AddInterpreter implements Interpreter {

    private final Interpreter leftExpression;
    private final Interpreter rightExpression;

    public AddInterpreter(Interpreter leftExpression,Interpreter rightExpression ){
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public double interpret() {
        return leftExpression.interpret() + rightExpression.interpret();
    }


}
