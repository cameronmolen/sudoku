import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SudokuGui extends JFrame {
  private final SudokuBoard board;
  private final SudokuController controller;

  public SudokuGui(SudokuBoard sudokuBoard) {
    board = sudokuBoard;
    controller = new SudokuController(sudokuBoard);
    setTitle("Sudoku");
    JPanel panel = new JPanel(new GridBagLayout());
    panel.add(createGrid(), getConstraints());
    getContentPane().add(panel, BorderLayout.CENTER);
    setMinimumSize(new Dimension(500,500));
    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public void setListener(SudokuListener listener) {
    controller.setListener(listener);
  }

  private JPanel createGrid() {
    JPanel grid = createMatrices(2);
    for(int row = 0; row < board.getBoxDimensions(); row++) {
      for(int col = 0; col < board.getBoxDimensions(); col++) {
        JPanel matrix = createMatrices(1);
        populateMatrix(matrix, row, col);
        grid.add(matrix);
      }
    }
    grid.setBorder(new EmptyBorder(new Insets(6,6,6,6)));
    return grid;
  }

  private JPanel createMatrices(int distanceBetween) {
    GridLayout gridLayout = new GridLayout(3, 3, 1, 1);
    gridLayout.setHgap(distanceBetween);
    gridLayout.setVgap(distanceBetween);
    return new JPanel(gridLayout);
  }

  private void populateMatrix(JPanel matrix, int row, int col) {
    for(int boxRow = 0; boxRow < board.getBoxDimensions(); boxRow++) {
      for(int boxCol = 0; boxCol < board.getBoxDimensions(); boxCol++) {
        int rowCoord = (row * 3) + boxRow;
        int colCoord = (col * 3) + boxCol;
        JFormattedTextField field = createNumberField(String.valueOf(board.getBoard()[rowCoord][colCoord]));
        controller.bindCell(rowCoord, colCoord, field);
        matrix.add(field);
      }
    }
  }

  private JFormattedTextField createNumberField(String value) {
    JFormattedTextField field = new JFormattedTextField();
    try {
      field.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("#")));
    } catch (java.text.ParseException ex) {}
    field.setPreferredSize(new Dimension(15,15));
    field.setHorizontalAlignment(JTextField.CENTER);
    if(!value.equals("0")) {
      field.setText(value);
    } else {
      field.setText("");
    }
    field.setBorder(null);
    return field;
  }

  private GridBagConstraints getConstraints() {
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1.0;
    constraints.weighty = 1.0;
    return constraints;
  }

}