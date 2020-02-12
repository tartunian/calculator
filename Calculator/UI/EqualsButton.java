package Calculator.UI;

import Calculator.Evaluator;

import java.awt.*;

public class EqualsButton extends CalculatorButton {

  public EqualsButton() {
    super( "=" );
  }

  @Override
  public void performAction( TextField textField ) {
    textField.setText( Integer.toString( Evaluator.getInstance().eval( textField.getText() ) ) );
  }

}
