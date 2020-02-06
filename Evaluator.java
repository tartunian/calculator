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
  }

  // Computes the final operation and returns the result.
  public int complete() {
    if( operatorStack.peek().getClass() != InitOperator.class ) {
      Operator opr = operatorStack.pop();
      Operand op2 = operandStack.pop();
      operandStack.push( opr.execute( operandStack.pop(), op2 ) );
    }
    return operandStack.peek().getValue();
  }

  public int eval( String expression ) {
    String token;

    this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );

    operatorStack.push( new InitOperator() );

    while ( this.tokenizer.hasMoreTokens() ) {

      // Ignore space tokens
      if ( ! ( token = this.tokenizer.nextToken() ).equals( " " ) ) {
        // Check if token is an operand. If it is, push it onto operandStack. If not, proceed to
        // else block.
        if ( Operand.check( token ) ) {
          operandStack.push( new Operand( token ) );
        } else {
          // Check if the token is an operator. If not, exit the program.
          if ( ! Operator.check( token ) ) {
            System.out.println( "*****invalid token******" );
            System.exit( 1 );
          }

          // Initialize newOperator with the Symbols.Operators.Operator subclass corresponding to the token.
          Operator newOperator = Operator.getByToken( token );

          // Cycle through operatorStack as long as the next operator is not an OpenParenthesisOperator and
          // its priority is greater than newOperator. Perform these higher priority operations and push the
          // result onto operandStack.
          while ( operatorStack.peek().getClass() != OpenParenthesisOperator.class &&
                  operatorStack.peek().priority() >= newOperator.priority() ) {
            Operator oldOpr = operatorStack.pop();
            Operand op2 = operandStack.pop();
            Operand op1 = operandStack.pop();
            operandStack.push( oldOpr.execute( op1, op2 ) );
          }
          // If newOperator is anything other than a CloseParenthesisOperator, push it onto operatorStack.
          // If it is, do not put it on operatorStack and pop the corresponding OpenParenthesisOperator off
          // of operatorStack.
          if( ! newOperator.getClass().equals( CloseParenthesisOperator.class ) ) {
            operatorStack.push( newOperator );
          } else {
            operatorStack.pop();
          }

        }
      }
    }

    // Complete and return the last operation.
    return complete();

  }
}
