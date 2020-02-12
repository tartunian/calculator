package Calculator.Symbols;

public class ExponentialOperator extends Operator {

  @Override
  public int getPriority() {
    return 3;
  }

  @Override
  public Operand execute( Operand firstOperand, Operand secondOperand ) {
    int result = (int) Math.pow( firstOperand.getValue(), secondOperand.getValue() );
    return new Operand( result );
  }

}
