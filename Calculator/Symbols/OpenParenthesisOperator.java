package Calculator.Symbols;

public class OpenParenthesisOperator extends Operator {

  @Override
  public int getPriority() {
    return 4;
  }

  @Override
  public Operand execute( Operand firstOperand, Operand secondOperand ) {
    return null;
  }
}

