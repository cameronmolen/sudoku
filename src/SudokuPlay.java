import javax.swing.*;
import java.awt.*;

public class SudokuPlay implements Runnable {

  public void run() {
    SudokuBoard sudokuBoard = new SudokuBoard(Difficulty.REGULAR);
    SudokuGui gui = new SudokuGui(sudokuBoard);
    gui.setVisible(true);
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(new SudokuPlay());
  }
}
