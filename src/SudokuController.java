import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SudokuController {

  private JFormattedTextField[][] sudokuGrid;
  private SudokuBoard board;

  public SudokuController(SudokuBoard sudokuBoard) {
    board = sudokuBoard;
    sudokuGrid = new JFormattedTextField[board.getBoardDimensions()][board.getBoardDimensions()];
  }

  public void bindCell(int row, int col, JFormattedTextField field) {
    field.addPropertyChangeListener("value", new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getNewValue() != null) {
          String newValue = (String)evt.getNewValue();
          // TODO: Make something happen if player ediits a field
        }
      }
    });
    sudokuGrid[row][col] = field;
  }

}
