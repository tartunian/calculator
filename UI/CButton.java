package UI;

import java.awt.*;

public class CButton extends CalculatorButton {

  public CButton() {
    super( "C" );
  }

  @Override
  public void performAction( TextField textField ) {
    textField.setText( "" );
  }
}
