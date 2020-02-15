package Calculator.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class CommandButton extends Button implements ActionListener {

  public static boolean isCommand( String token ) {
    return Pattern.matches( "(CE|C|=)", token );
  }

  private Runnable command;

  public CommandButton( String label, Runnable command ) {
    setLabel( label );
    this.command = command;
    addActionListener( this );
  }

  @Override
  public void actionPerformed( ActionEvent e ) {
    command.run();
  }
}
