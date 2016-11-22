package com.shmouradko.textparsing.interpreter;

/**
 * Created by Revotech on 14.11.2016.
 */
public class DivisionInterpreter implements Interpreter {
    private final Interpreter leftExpression;
    private final Interpreter rightExpression;

    public DivisionInterpreter(Interpreter leftExpression,Interpreter rightExpression ){
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public double interpret() {
        return leftExpression.interpret() / rightExpression.interpret();
    }
}
