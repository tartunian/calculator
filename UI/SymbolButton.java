package UI;

import java.awt.*;
import java.util.regex.Pattern;

public class SymbolButton extends CalculatorButton {

  public SymbolButton( String label ) {
    super( label );
    boolean isOperator = Pattern.matches( "[++--*/^()]", label );
    boolean isDigit = Pattern.matches( "\\b\\d\\b", label);
    if( !isOperator && !isDigit ) {
      throw new IllegalArgumentException( "Argument \'label\' must be a digit or operator" );
    }
  }

  @Override
  public void performAction( TextField textField ) {
    textField.setText( textField.getText() + this.getLabel() );
  }

}
