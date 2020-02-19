package UI;

import Model.IEvaluator;

import java.awt.*;

public class EqualsButton extends CalculatorButton {

  private IEvaluator evaluator;

  public EqualsButton( IEvaluator evaluator ) {
    super( "=" );
    this.evaluator = evaluator;
  }

  @Override
  public void performAction( TextField textField ) {
    textField.setText( Integer.toString( this.evaluator.evaluate( textField.getText() ) ) );
  }

}
