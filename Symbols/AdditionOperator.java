package Symbols;

public class AdditionOperator extends Operator {

  @Override
  public int getPriority() {
    return 1;
  }

  @Override
  public Operand execute( Operand firstOperand, Operand secondOperand ) {
    int result =  firstOperand.getValue() + secondOperand.getValue();
    return new Operand( result );
  }

}


