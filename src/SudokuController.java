import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SudokuController {

  private final JFormattedTextField[][] sudokuGrid;
  private final SudokuBoard board;
  private SudokuGui gui;
  SudokuListener listener;
  JLabel timeLabel;
  Thread backgroundThread;

  /** Constructor for SudokuController class. */
  public SudokuController(SudokuBoard sudokuBoard, SudokuGui gui) {
    board = sudokuBoard;
    this.gui = gui;
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
        try {
          board.updateBoard(row, col, Integer.parseInt(e.getDocument().getText(e.getOffset(), e.getLength())));
        } catch (BadLocationException badLocationException) {
          badLocationException.printStackTrace();
        }
      }

      public void removeUpdate(DocumentEvent e) {
        board.increaseNumBlanksRemaining();
        board.updateBoard(row, col, 0);
      }

      public void changedUpdate(DocumentEvent e) {}
    });
    sudokuGrid[row][col] = field;
  }

  public void bindSubmitButton(JButton button) {
    button.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        if(board.checkIfCorrect()) {
          Toast toast = new Toast("The board is correct!", gui);
          toast.display();
//          System.out.println("The board is correct!");
        } else {
          Toast toast = new Toast("The board is incorrect", gui);
          toast.display();
//          System.out.println("The board is incorrect.");
        }
      }

    });
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
