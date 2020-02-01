public class OpenParenthesisOperator extends Operator {

  @Override
  public int priority() {
    return 4;
  }

  @Override
  public Operand execute(Operand op1, Operand op2) {
    return null;
  }
}

