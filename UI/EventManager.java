package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventManager implements ActionListener {

  private TextField textField;

  public EventManager( TextField textField ) {
    this.textField = textField;
  }

  @Override
  public void actionPerformed( ActionEvent event ) {
    ( (CalculatorButton) event.getSource() ).performAction( this.textField );
  }

}
