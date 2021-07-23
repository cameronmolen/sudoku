import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;

public class SudokuTimer implements ActionListener {

  private final SudokuController controller;
  private final Instant startTime;

  /** Constructor for SudokuListener class. */
  public SudokuTimer(SudokuController controller) {
    this.controller = controller;
    startTime = Instant.now();
    Timer timer = new Timer(1000, this);
    timer.start();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Duration runningTime = Duration.between(startTime, Instant.now());
    long minutes = runningTime.toMinutes();
    long seconds = runningTime.toSeconds();
    controller.setTimer(String.format("%d:%02d", minutes, seconds));
  }
}
