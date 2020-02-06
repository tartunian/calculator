package Symbols.Operators;

import Symbols.Operand;

public class CloseParenthesisOperator extends Operator {

  @Override
  public int priority() {
        return 0;
    }

  @Override
  public Operand execute(Operand op1, Operand op2) {
        return null;
    }
}
