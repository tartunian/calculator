public class Operand {

  private int _value;

  public Operand( String token ) {
    try { _value = Integer.parseInt( token ); } catch (Exception e) {}
  }

  public Operand( int value ) {
    _value = value;
  }

  public int getValue() {
    return _value;
  }

  public static boolean check( String token ) {
    try { int i = Integer.parseInt(token); } catch (Exception e) { return false; }
    return true;
  }
}
