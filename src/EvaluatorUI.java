import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {
  private TextField txField = new TextField();
  private Panel buttonPanel = new Panel();

  // total of 20 buttons on the calculator,
  // numbered from left to right, top to bottom
  // bText[] array contains the text for corresponding buttons
  private static final String[] bText = {
    "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3",
    "*", "0", "^", "=", "/", "(", ")", "C", "CE"
  };
  private Button[] buttons = new Button[ bText.length ];

  private Evaluator evaluator = new Evaluator();

  public static void main(String argv[]) {
    EvaluatorUI calc = new EvaluatorUI();
  }

  public EvaluatorUI() {

    setLayout( new BorderLayout() );

    add( txField, BorderLayout.NORTH );
    txField.setEditable( true );

    add( buttonPanel, BorderLayout.CENTER );
    buttonPanel.setLayout( new GridLayout( 5, 4 ));

    //create 20 buttons with corresponding text in bText[] array
    for ( int i = 0; i < 20; i++ ) {
      buttons[ i ] = new Button( bText[ i ]);
    }

    //add buttons to button panel
    for (int i=0; i<20; i++) {
      buttonPanel.add( buttons[ i ]);
    }

    //set up buttons to listen for mouse input
    for ( int i = 0; i < 20; i++ ) {
      buttons[ i ].addActionListener( this );
    }

    setTitle( "Calculator" );
    setSize( 400, 400 );
    setLocationByPlatform( true  );
    setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
    setVisible( true );
  }

  public void actionPerformed( ActionEvent arg0 ) {
    String cmd = arg0.getActionCommand();
    switch ( cmd ) {
      case "C": {
        txField.setText("");
        break;
      }
      case "CE": {
        txField.setText( txField.getText().substring( 0, txField.getText().length()-1 ));
        break;
      }
      case "=": {
        txField.setText( Integer.toString( evaluator.eval( txField.getText() ) ) );
        break;
      }
      default: {
        txField.setText( txField.getText() + cmd );
      }
    }
  }
}
