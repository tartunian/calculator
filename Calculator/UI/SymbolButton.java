package Calculator.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class SymbolButton extends Button implements ActionListener {

  public static boolean isSymbol( String token ) {
    return Pattern.matches( "([0-9]|[+\\-*/^()])", token );
  }

  private Consumer<String> command;

  public SymbolButton( String label, Consumer<String> command ) {
    setLabel( label );
    this.command = command;
    addActionListener( this );
  }

  @Override
  public void actionPerformed( ActionEvent e ) {
    command.accept( this.getLabel() );
  }

}
