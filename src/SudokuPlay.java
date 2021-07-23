import java.awt.*;

public class SudokuPlay implements Runnable {

  public static void main(String[] args) {
    EventQueue.invokeLater(new SudokuPlay());
  }

  /** Generates the SudokuBoard and runs the GUI with listener. */
  public void run() {
    SudokuBoard sudokuBoard=new SudokuBoard(Difficulty.REGULAR);
    SudokuGui gui=new SudokuGui(sudokuBoard);
    gui.setVisible(true);
  }

  /** Runs the GUI with the board already passed in. */
  public void run(SudokuBoard sudokuBoard) {
    SudokuGui gui=new SudokuGui(sudokuBoard);
    gui.setVisible(true);
  }

}
