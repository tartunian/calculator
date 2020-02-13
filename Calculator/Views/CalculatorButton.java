package Calculator.Views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorButton extends Button implements ActionListener {

  private Runnable command;

  public CalculatorButton( String label, Runnable command ) {
    this.setLabel( label );
    this.command = command;
    this.addActionListener( this );
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    command.run();
  }

}
