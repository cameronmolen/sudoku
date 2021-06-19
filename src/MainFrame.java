import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
  public MainFrame(String title) {
    super(title);

    // Set the layout manager
    setLayout(new BorderLayout());

    // Create Swing components
    JTextArea textArea = new JTextArea();
    JButton button = new JButton("Click me!");

    // Add Swing components to the content pane
    Container c = getContentPane();

    c.add(textArea, BorderLayout.CENTER);
    c.add(button, BorderLayout.SOUTH);

    // Add listener
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textArea.append("Hello\n");
      }
    });
  }
}
