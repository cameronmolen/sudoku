import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SudokuController {

  private JFormattedTextField[][] sudokuGrid;
  private SudokuBoard board;
  SudokuListener listener;
  Thread backgroundThread;

  public SudokuController(SudokuBoard sudokuBoard) {
    board = sudokuBoard;
    sudokuGrid = new JFormattedTextField[board.getBoardDimensions()][board.getBoardDimensions()];
  }

  public void setListener(SudokuListener listener) {
    this.listener = listener;
  }

  public void bindCell(int row, int col, JFormattedTextField field) {
    field.addPropertyChangeListener("value", new PropertyChangeListener() {
      public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getNewValue() != null) {
          String newValue = (String)evt.getNewValue();
          System.out.println("Value at " + row + ", " + col + " changed to " + newValue);
          // TODO: Make something happen if player edits a field
        }
      }
    });
    sudokuGrid[row][col] = field;
  }

}
