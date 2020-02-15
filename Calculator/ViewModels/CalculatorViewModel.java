package Calculator.ViewModels;

import Calculator.Model.Evaluator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.function.Consumer;

public class CalculatorViewModel {

  PropertyChangeSupport support = new PropertyChangeSupport(this);

  private String expression = "";

  public final Consumer<String> appendExpressionCommand = s -> appendExpression( s );
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
