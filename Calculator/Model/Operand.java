package Calculator.Model;

public class Operand {

  private int value;

  public static boolean check( String token ) {
    try { int i = Integer.parseInt(token); } catch (Exception e) { return false; }
    return true;
  }

  public Operand( String token ) {
    try { this.value = Integer.parseInt( token ); } catch (Exception e) {}
  }

  public Operand( int value ) {
    this.value = value;
  }

  public int getValue() {
    return this.value;
  }

  public String toString() {
    return String.valueOf( getValue() );
  }

}
