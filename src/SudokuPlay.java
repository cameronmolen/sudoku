import java.awt.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SudokuPlay implements Runnable {

  public static void main(String[] args) {
    EventQueue.invokeLater(new SudokuPlay());
  }

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

  private void startNewGame(SudokuController controller) { // TODO: Implement timer
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        counter++;
        System.out.println(counter);
      }
    }, new Date(), 1000);
  }

}
