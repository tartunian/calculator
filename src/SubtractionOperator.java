public class SubtractionOperator extends Operator {

  @Override
  public int priority() {
    return 1;
  }

  @Override
  public Operand execute(Operand op1, Operand op2) {
    int result =  op1.getValue() - op2.getValue();
    return new Operand( result );
  }

}

