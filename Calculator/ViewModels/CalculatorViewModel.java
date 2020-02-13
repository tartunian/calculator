package Calculator.ViewModels;

import Calculator.Evaluator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CalculatorViewModel {

  PropertyChangeSupport support = new PropertyChangeSupport(this);

  private String expression = "";

  public final Runnable appendZeroCommand = () -> appendExpression( "0" );
  public final Runnable appendOneCommand = () -> appendExpression( "1" );
  public final Runnable appendTwoCommand = () -> appendExpression( "2" );
  public final Runnable appendThreeCommand = () -> appendExpression( "3" );
  public final Runnable appendFourCommand = () -> appendExpression( "4" );
  public final Runnable appendFiveCommand = () -> appendExpression( "5" );
  public final Runnable appendSixCommand = () -> appendExpression( "6" );
  public final Runnable appendSevenCommand = () -> appendExpression( "7" );
  public final Runnable appendEightCommand = () -> appendExpression( "8" );
  public final Runnable appendNineCommand = () -> appendExpression( "9" );
  public final Runnable appendPlusCommand = () -> appendExpression( "+" );
  public final Runnable appendMinusCommand = () -> appendExpression( "-" );
  public final Runnable appendMultiplyCommand = () -> appendExpression( "*" );
  public final Runnable appendDivideCommand = () -> appendExpression( "/" );
  public final Runnable appendExponentCommand = () -> appendExpression( "^" );
  public final Runnable appendOpenParenthesisCommand = () -> appendExpression( "(" );
  public final Runnable appendCloseParenthesisCommand = () -> appendExpression( ")" );

  public final Runnable equalsCommand = () -> setExpression( Integer.toString( Evaluator.getInstance().eval( expression ) ) );
  public final Runnable clearCommand = () -> setExpression( "" );
  public final Runnable clearEntryCommand = () -> clearEntry();

  public String getExpression() {
    return expression;
  }

  private void setExpression( String expression ) {
    String oldValue = this.expression;
    this.expression = expression;
    support.firePropertyChange("expression", oldValue, this.expression );
  }

  private void appendExpression( String chars ) {
    setExpression( expression + chars );
  }

  private void clearEntry() {
    int length = expression.length();
    if( length > 0 ) {
      setExpression( expression.substring( 0, length - 1) );
    }
  }

  public void addPropertyChangeListener( PropertyChangeListener listener ) {
    support.addPropertyChangeListener( listener );
  }

  public void addPropertyChangeListener( String propertyName, PropertyChangeListener listener ) {
    support.addPropertyChangeListener( propertyName, listener );
  }

  public void removePropertyChangeListener( PropertyChangeListener listener ) {
    support.removePropertyChangeListener( listener );
  }

}
