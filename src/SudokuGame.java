import javax.swing.*;
import java.awt.*;

public class SudokuGame {
  private JFrame frame;

  public void playGame(SudokuBoard board) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
      }
    });
  }

}

/* Notes:
    - Should I make a panel for the menu that shows when the app is started?
*/