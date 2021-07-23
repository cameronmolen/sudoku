import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

class Toast extends JFrame {

  /** Constructor for the Toast class. */
  public Toast(boolean correct, SudokuGui gui) {
    setUndecorated(true);
    if (correct) {
      setBackground(new Color(144, 238, 144));
    } else {
      setBackground(new Color(240, 128, 128));
    }
    setSize(gui.getGuiDimensions(), gui.getGuiDimensions());
    setLocationRelativeTo(gui);
    setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
  }

  /** Displays the toast which slowly fades away. */
  public void display() {
    try {
      // Display the toast message
      setOpacity((float) 0.5);
      setVisible(true);
      Thread.sleep(300);

      // Slowly fade out toast message
      for (double i=0.5; i >= 0.0; i-=0.05) {
        Thread.sleep(60);
        setOpacity((float) i);
      }

      setVisible(false);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}