import javax.swing.*;

public class SudokuGame {
  private JFrame frame;

  public void playGame() {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        frame = new MainFrame("Sudoku");
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