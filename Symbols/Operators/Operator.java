package Symbols.Operators;

import Symbols.Operand;

import java.util.HashMap;

public abstract class Operator {

  private static String operatorTokens = "+-*/^()";
  // The Symbols.Operators.Operator class should contain an instance of a HashMap
  // This map will use keys as the tokens we're interested in,
  // and values will be instances of the Symbols.Operators.

  // Example:
  // Where does this declaration go? What should its access level be?
  // Class or instance variable? Is this the right declaration?
  private static HashMap<String, Operator> Operators = new HashMap();
  static {
    Operators.put( "#", new InitOperator() );
    Operators.put( "+", new AdditionOperator() );
    Operators.put( "-", new SubtractionOperator() );
    Operators.put( "*", new MultiplicationOperator() );
    Operators.put( "/", new DivisionOperator() );
    Operators.put( "^", new ExponentialOperator() );
    Operators.put( "(", new OpenParenthesisOperator() );
    Operators.put( ")", new CloseParenthesisOperator() );
  }

  public static Operator getByToken( String token ) {
    return Operators.get( token );
  }
  public static boolean check( String token ) {
    return operatorTokens.contains(token);
  }

  public abstract int priority();
  public abstract Operand execute(Operand op1, Operand op2 );

}