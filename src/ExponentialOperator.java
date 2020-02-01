public class ExponentialOperator extends Operator {

  @Override
  public int priority() {
    return 3;
  }

  @Override
  public Operand execute( Operand op1, Operand op2 ) {
    int result = (int) Math.pow( op1.getValue(), op2.getValue() );
    return new Operand( result );
  }

}
