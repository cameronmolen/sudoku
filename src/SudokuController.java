import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SudokuController {

  private JFormattedTextField[][] sudokuGrid;

  public SudokuController(int dimensions) {
    sudokuGrid = new JFormattedTextField[dimensions][dimensions];
  }

  public void bindCell(int row, int col, JFormattedTextField field) {
    field.addPropertyChangeListener("value", new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getNewValue() != null) {
          String newValue = (String)evt.getNewValue();
        }
      }
    });
    sudokuGrid[row][col] = field;
  }

}
