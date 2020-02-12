package Calculator.Symbols;

public class InitOperator extends Operator {

  @Override
  public int getPriority() {
    return 0;
  }

  @Override
  public Operand execute( Operand firstOperand, Operand secondOperand ) {
      return null;
  }

}
