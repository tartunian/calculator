import Calculator.Evaluator;

/**
 * Class to help test your Calculator.Evaluator:
 * javac EvaluatorTester
 * java EvaluatorTester "1+2" "3*5"
 */
public class EvaluatorTester {
  public static void main(String[] args) {

    for ( String arg : args ) {
      System.out.format( "%s = %d\n", arg, Evaluator.getInstance().eval( arg ) );
    }
  }
}
