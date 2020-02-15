package Calculator.Views;

import Calculator.UI.CommandButton;
import Calculator.UI.SymbolButton;
import Calculator.ViewModels.CalculatorViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CalculatorWindow extends JFrame implements PropertyChangeListener {

  private CalculatorViewModel viewModel = new CalculatorViewModel();

  private TextField textField = new TextField();
  private Panel buttonPanel = new Panel();

  private String[] buttonText = {
    "7", "8", "9", "+",
    "4", "5", "6", "-",
    "1", "2", "3", "*",
    "(", "0", ")", "/",
    "^", "C", "CE", "="
  };

  public CalculatorWindow() {

    viewModel.addPropertyChangeListener( "expression", this );

    setLayout( new BorderLayout() );

    add( textField, BorderLayout.NORTH );
    textField.setEditable( false );

    add( buttonPanel, BorderLayout.CENTER );
    buttonPanel.setLayout( new GridLayout( 5, 4 ) );

    Button[] buttons = new Button[ buttonText.length ];

    for( int i=0; i<17; i++ ) {
      if( SymbolButton.isSymbol( buttonText[ i ] ) ) {
        buttons[ i ] = new SymbolButton( buttonText[ i ], viewModel.appendExpressionCommand );
      }
    }

    buttons[17] = new CommandButton( "C", viewModel.clearCommand );
    buttons[18] = new CommandButton( "CE", viewModel.clearEntryCommand );
    buttons[19] = new CommandButton( "=", viewModel.equalsCommand );

    for ( int i=0; i<20; i++ ) {
      buttonPanel.add( buttons[ i ] );
    }

    setTitle( "Calculator" );
    setSize( 400, 400 );
    setLocationByPlatform( true  );
    setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
    setVisible( true );

  }

  @Override
  public void propertyChange( PropertyChangeEvent evt ) {
    if( evt.getPropertyName() == "expression" ) {
      textField.setText( viewModel.getExpression() );
    }
  }

}
