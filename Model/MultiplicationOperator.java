package Model;

public class MultiplicationOperator extends Operator {

  @Override
  public int getPriority() {
    return 2;
  }

  @Override
  public Operand execute( Operand firstOperand, Operand secondOperand ) {
    int result =  firstOperand.getValue() * secondOperand.getValue();
    return new Operand( result );
  }

}

