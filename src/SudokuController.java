import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SudokuController {

  private final JFormattedTextField[][] sudokuGrid;
  private final SudokuBoard board;
  SudokuListener listener;
  JLabel timeLabel;
  Thread backgroundThread;

  /** Constructor for SudokuController class. */
  public SudokuController(SudokuBoard sudokuBoard) {
    board = sudokuBoard;
    sudokuGrid = new JFormattedTextField[board.getBoardDimensions()][board.getBoardDimensions()];
  }

  /** Sets the listener for the SudokuController. */
  public void setListener(SudokuListener listener) { this.listener = listener; }

  /**
   * Binds a cell in the GUI to the controller and listens for changes.
   * @param row the row of the cell
   * @param col the column of the cell
   * @param field the FormattedTextField that represents the cell
   */
  public void bindCell(int row, int col, JFormattedTextField field) {
    field.getDocument().addDocumentListener(new DocumentListener() {
      public void insertUpdate(DocumentEvent e) {
        board.decreaseNumBlanksRemaining();
        System.out.println("Value at " + row + ", " + col + " changed. Number of blanks remaining: " + board.getNumBlanksRemaining()); // DEBUGGING
      }

      public void removeUpdate(DocumentEvent e) {
        board.increaseNumBlanksRemaining();
        System.out.println("Value at " + row + ", " + col + " removed. Number of blanks remaining: " + board.getNumBlanksRemaining()); // DEBUGGING
      }

      public void changedUpdate(DocumentEvent e) {}
    });
    sudokuGrid[row][col] = field;
  }

  public void bindTimeLabel(JLabel timeLabel) {
    this.timeLabel = timeLabel;
  }

  public void setTimer(String time) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        timeLabel.setText("Time Elapsed: " + time);
      }
    });
  }

}
