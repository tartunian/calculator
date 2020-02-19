import UI.*;

import javax.swing.*;
import java.awt.*;

public class EvaluatorUI extends JFrame {

  private TextField textField = new TextField();
  private Panel buttonPanel = new Panel();

  private Button[] buttons = new CalculatorButton[ 20 ];

  private EventManager eventManager = new EventManager( textField );

  public EvaluatorUI() {

    setLayout( new BorderLayout() );

    add( textField, BorderLayout.NORTH );
    textField.setEditable( false );

    add( buttonPanel, BorderLayout.CENTER );

    buttonPanel.setLayout( new GridLayout( 5, 4 ) );

    buttons[0] = new SymbolButton( "7" );
    buttons[1] = new SymbolButton( "8" );
    buttons[2] = new SymbolButton( "9" );
    buttons[3] = new SymbolButton( "+" );
    buttons[4] = new SymbolButton( "4" );
    buttons[5] = new SymbolButton( "5" );
    buttons[6] = new SymbolButton( "6" );
    buttons[7] = new SymbolButton( "-" );
    buttons[8] = new SymbolButton( "1" );
    buttons[9] = new SymbolButton( "2" );
    buttons[10] = new SymbolButton( "3" );
    buttons[11] = new SymbolButton( "*" );
    buttons[12] = new SymbolButton( "0" );
    buttons[13] = new SymbolButton( "^" );
    buttons[14] = new EqualsButton( Evaluator.getInstance() );
    buttons[15] = new SymbolButton( "/" );
    buttons[16] = new SymbolButton( "(" );
    buttons[17] = new SymbolButton( ")" );
    buttons[18] = new CButton();
    buttons[19] = new CEButton();

    //add buttons to button panel
    for ( int i = 0; i < 20; i++ ) {
      buttonPanel.add( buttons[ i ] );
    }

    //set up buttons to listen for mouse input
    for ( int i = 0; i < 20; i++ ) {
      buttons[ i ].addActionListener( eventManager );
    }

    setTitle("UI");
    setSize( 400, 400 );
    setLocationByPlatform( true  );
    setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
    setVisible( true );

  }

  public static void main(String[] args) {
    new EvaluatorUI();
  }

}
