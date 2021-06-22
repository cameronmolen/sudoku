import javax.swing.*;
import java.awt.*;

public class SudokuPanel extends JPanel {

  private int value;
  private int x, y;

  public SudokuPanel(int x, int y) {
    super();
    this.x = x;
    this.y = y;

    setBorder(BorderFactory.createLineBorder(Color.BLACK));
    setPreferredSize(new Dimension(30,30));
  }

  public int getValue() { return value; }
  public int getX() { return x; }
  public int getY() { return y; }

  public void setValue(int value) {
    this.value = value;
  }

}
