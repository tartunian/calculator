package Symbols;

import java.util.HashMap;

public abstract class Operator {

  private static String operatorTokens = "+-*/^()";
  // The Symbols.Operator class should contain an instance of a HashMap
  // This map will use keys as the tokens we're interested in,
  // and values will be instances of the Symbols.Operators.

  // Example:
  // Where does this declaration go? What should its access level be?
  // Class or instance variable? Is this the right declaration?
  private static HashMap < String, Operator > operators = new HashMap < String, Operator > ();
  static {
    operators.put( "#", new InitOperator() );
    operators.put( "+", new AdditionOperator() );
    operators.put( "-", new SubtractionOperator() );
    operators.put( "*", new MultiplicationOperator() );
    operators.put( "/", new DivisionOperator() );
    operators.put( "^", new ExponentialOperator() );
    operators.put( "(", new OpenParenthesisOperator() );
    operators.put( ")", new CloseParenthesisOperator() );
  }

  public static Operator getByToken( String token ) {
    return operators.get( token );
  }
  public static boolean check( String token ) {
    return operatorTokens.contains( token );
  }

  public abstract int getPriority();
  public abstract Operand execute( Operand firstOperand, Operand secondOperand );

}