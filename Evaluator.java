import Symbols.Operators.CloseParenthesisOperator;
import Symbols.Operators.InitOperator;
import Symbols.Operators.OpenParenthesisOperator;
import Symbols.Operators.Operator;
import Symbols.Operand;

import java.util.*;

public class Evaluator {
  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;

  private StringTokenizer tokenizer;
  private static final String DELIMITERS = "+-*^/() ";

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
    operatorStack.push( new InitOperator() );
  }

  private void handleToken( String token ) {
    if ( Operand.check( token ) ) {
        operandStack.push( new Operand( token ) );
    } else if ( Operator.check( token ) ) {
      Operator newOperator = Operator.getByToken( token );
      handleOperator( newOperator );
    } else if ( token.equals( " " ) {
    } else {
      System.out.println( "*****invalid token******" );
      System.exit( 1 );
    }
  }

  private void handleOperator( Operator newOperator ) {
    while ( operatorStack.peek().getClass() != OpenParenthesisOperator.class &&
            operatorStack.peek().priority() >= newOperator.priority() ) {
      Operator prioritizedOperator = operatorStack.pop();
      Operand secondOperand = operandStack.pop();
      operandStack.push( prioritizedOperator.execute( operandStack.pop(), secondOperand ) );
    }
    if ( newOperator.getClass().equals( CloseParenthesisOperator.class ) ) {
      operatorStack.pop();
    } else {
      operatorStack.push( newOperator );
    }
  }

  private int complete() {
    if ( operatorStack.peek().getClass() != InitOperator.class ) {
      Operator lastOperator = operatorStack.pop();
      Operand secondOperand = operandStack.pop();
      operandStack.push( opr.execute( operandStack.pop(), secondOperand ) );
    }
    return operandStack.peek().getValue();
  }

  public int eval( String expression ) {
    this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );
    while ( this.tokenizer.hasMoreTokens() ) {
      handleToken( this.tokenizer.nextToken() );
    }
    return complete();
  }
}
