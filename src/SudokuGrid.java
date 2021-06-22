import javax.swing.*;
import java.awt.*;

public class SudokuGrid extends JPanel {

  SudokuBoard board;

  public SudokuGrid(SudokuBoard board) {
    super(new GridBagLayout());
    this.board = board;

    GridBagConstraints c = new GridBagConstraints();

    for(int x = 0; x < board.getBoardDimensions(); x++) {
      for(int y = 0; y < board.getBoardDimensions(); y++) {
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = x;
        c.gridy = y;
        add(new SudokuPanel(x, y), c);
      }
    }

    setBorder(BorderFactory.createLineBorder(Color.BLACK));
  }

}
