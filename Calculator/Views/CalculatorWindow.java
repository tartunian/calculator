package Calculator.Views;

import Calculator.ViewModels.CalculatorViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CalculatorWindow extends JFrame implements PropertyChangeListener {

  private CalculatorViewModel viewModel = new CalculatorViewModel();

  private TextField textField = new TextField();
  private Panel buttonPanel = new Panel();

  public CalculatorWindow() {

    viewModel.addPropertyChangeListener( "expression", this );

    setLayout( new BorderLayout() );

    add( textField, BorderLayout.NORTH );
    textField.setEditable( false );

    add( buttonPanel, BorderLayout.CENTER );
    buttonPanel.setLayout( new GridLayout( 5, 4 ) );

    Button[] buttons = new CalculatorButton[ 20 ];

    buttons[0] = new CalculatorButton( "7", viewModel.appendSevenCommand );
    buttons[1] = new CalculatorButton( "8", viewModel.appendEightCommand );
    buttons[2] = new CalculatorButton( "9", viewModel.appendNineCommand );
    buttons[3] = new CalculatorButton( "+", viewModel.appendPlusCommand );
    buttons[4] = new CalculatorButton( "4", viewModel.appendFourCommand );
    buttons[5] = new CalculatorButton( "5", viewModel.appendFiveCommand );
    buttons[6] = new CalculatorButton( "6", viewModel.appendSixCommand );
    buttons[7] = new CalculatorButton( "-" , viewModel.appendMinusCommand );
    buttons[8] = new CalculatorButton( "1", viewModel.appendOneCommand );
    buttons[9] = new CalculatorButton( "2", viewModel.appendTwoCommand );
    buttons[10] = new CalculatorButton( "3", viewModel.appendThreeCommand);
    buttons[11] = new CalculatorButton( "*", viewModel.appendMultiplyCommand );
    buttons[12] = new CalculatorButton( "0", viewModel.appendZeroCommand );
    buttons[13] = new CalculatorButton( "^", viewModel.appendExponentCommand );
    buttons[14] = new CalculatorButton( "=", viewModel.equalsCommand );
    buttons[15] = new CalculatorButton( "/", viewModel.appendDivideCommand );
    buttons[16] = new CalculatorButton( "(", viewModel.appendOpenParenthesisCommand );
    buttons[17] = new CalculatorButton( ")", viewModel.appendCloseParenthesisCommand );
    buttons[18] = new CalculatorButton( "C", viewModel.clearCommand );
    buttons[19] = new CalculatorButton( "CE", viewModel.clearEntryCommand );

    //add buttons to button panel
    for ( int i = 0; i < 20; i++ ) {
      buttonPanel.add( buttons[ i ] );
    }

    setTitle( "Calculator" );
    setSize( 400, 400 );
    setLocationByPlatform( true  );
    setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
    setVisible( true );

  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if( evt.getPropertyName() == "expression" ) {
      textField.setText( viewModel.getExpression() );
    }
  }

}
