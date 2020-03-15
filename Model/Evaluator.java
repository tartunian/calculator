package Model;

import java.util.*;

public class Evaluator {
  private Stack <Operand> operandStack;
  private Stack <Operator> operatorStack;

  private StringTokenizer tokenizer;
  private static final String DELIMITERS = "+-*^/() ";

  private static Evaluator instance;

  private Evaluator() {
    reset();
  }

  public static Evaluator getInstance() {
    if( instance == null ) {
      return instance = new Evaluator();
    } else {
      return instance;
    }
  }

  public void reset() {
    operandStack = new Stack<Operand>();
    operatorStack = new Stack<Operator>();
    operatorStack.push( new InitOperator() );
  }

  private void handleToken( String token ) {
    if ( Operand.check( token ) ) {
        operandStack.push( new Operand( token ) );
    } else if ( Operator.check( token ) ) {
      Operator newOperator = Operator.getByToken( token );
      handleOperator( newOperator );
    } else if ( token.equals(" ") ) {
    } else {
      System.out.println( "*****invalid token******" );
      System.exit( 1 );
    }
  }

  private void handleOperator( Operator newOperator ) {
    while ( operatorStack.peek().getClass() != OpenParenthesisOperator.class &&
            operatorStack.peek().getPriority() >= newOperator.getPriority() ) {
      Operator prioritizedOperator = operatorStack.pop();
      Operand secondOperand = operandStack.pop();
      operandStack.push( prioritizedOperator.execute( operandStack.pop(), secondOperand ) );
    }
    if ( newOperator.getClass() == CloseParenthesisOperator.class ) {
      operatorStack.pop();
    } else {
      operatorStack.push( newOperator );
    }
  }

  private int complete() {
    while ( operatorStack.peek().getClass() != InitOperator.class ) {
      Operator nextOperator = operatorStack.pop();
      Operand secondOperand = operandStack.pop();
      Operand firstOperand = operandStack.pop();
      System.out.println( String.format( "First: %s Second: %s", firstOperand.getValue(), secondOperand.getValue() ) );
      operandStack.push( nextOperator.execute( firstOperand, secondOperand ) );
    }
    int result = operandStack.pop().getValue();
    System.out.println( operandStack + " " + operatorStack );
    return result;
  }

  public int eval( String expression ) {
    this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );
    while ( this.tokenizer.hasMoreTokens() ) {
      handleToken( this.tokenizer.nextToken() );
    }
    return complete();
  }

}
