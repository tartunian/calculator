package Calculator.Model;

import java.util.*;

public class Evaluator {
  private Stack <Operand> operandStack;
  private Stack <Operator> operatorStack;

  private StringTokenizer tokenizer;
  private static final String DELIMITERS = "+-*^/() ";

  private static Evaluator instance;

  private Evaluator() {
    operandStack = new Stack<Operand>();
    operatorStack = new Stack<Operator>();
    operatorStack.push( new InitOperator() );
  }

  public static Evaluator getInstance() {
    if( instance == null ) {
      return instance = new Evaluator();
    } else {
      return instance;
    }
  }

  private void handleToken( String token ) {
    if ( Operand.isOperand( token ) ) {
        operandStack.push( new Operand( token ) );
    } else if ( Operator.isOperator( token ) ) {
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
            operatorStack.peek().getClass() != InitOperator.class &&
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
      operandStack.push( nextOperator.execute( operandStack.pop(), secondOperand ) );
    }
    return operandStack.peek().getValue();
  }

  public int evaluate( String expression ) {
    this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );
    while ( this.tokenizer.hasMoreTokens() ) {
      handleToken( this.tokenizer.nextToken() );
    }
    return complete();
  }

}
