package UI;

import java.awt.*;

public abstract class CalculatorButton extends Button {

  public CalculatorButton( String label ) {
    this.setLabel( label );
  }

  public abstract void performAction( TextField textField );

}
