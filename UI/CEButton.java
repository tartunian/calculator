package UI;

import java.awt.*;

public class CEButton extends CalculatorButton {

  public CEButton() {
    super( "CE" );
  }

  @Override
  public void performAction( TextField textField ) {
    if ( textField.getText().length() > 0 ) {
      textField.setText( textField.getText().substring( 0, textField.getText().length() - 1) );
    }
  }
}
