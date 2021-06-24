import java.awt.*;

public class SudokuPlay implements Runnable {

  /** Generates the SudokuBoard and runs the GUI with listener. */
  public void run() {
    SudokuListener listener = new SudokuListener();
    SudokuBoard sudokuBoard = new SudokuBoard(Difficulty.REGULAR);
    SudokuGui gui = new SudokuGui(sudokuBoard);
    gui.setListener(listener);
    gui.setVisible(true);
  }

  /** Runs the GUI with the board already passed in. */
  public void run(SudokuBoard sudokuBoard) {
    SudokuListener listener = new SudokuListener();
    SudokuGui gui = new SudokuGui(sudokuBoard);
    gui.setListener(listener);
    gui.setVisible(true);
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(new SudokuPlay());
  }
}
