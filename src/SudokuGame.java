import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SudokuGame extends JFrame {
  private SudokuBoard board;
  private SudokuController controller;

  public SudokuGame(SudokuBoard sudokuBoard) {
    board = sudokuBoard;
    controller = new SudokuController(sudokuBoard.getBoardDimensions());
    setTitle("Sudoku");
    JPanel panel = new JPanel(new GridBagLayout());
    panel.add(createGrid(), getConstraints());
    getContentPane().add(panel, BorderLayout.CENTER);
    setMinimumSize(new Dimension(500,500));
    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  private JPanel createGrid() {
    JPanel grid = createMatrices();
    for(int row = 0; row < board.getBoxDimensions(); row++) {
      for(int col = 0; col < board.getBoardDimensions(); col++) {
        JPanel matrix = createMatrices();
        populateMatrix(matrix, row, col);
        grid.add(matrix);
      }
    }
    grid.setBorder(new EmptyBorder(new Insets(6,6,6,6)));
    return grid;
  }

  private JPanel createMatrices() {
    GridLayout gridLayout = new GridLayout(3, 3, 1, 1);
    return new JPanel(gridLayout);
  }

  private void populateMatrix(JPanel matrix, int row, int col) {
    for(int boxRow = 0; boxRow < board.getBoxDimensions(); boxRow++) {
      for(int boxCol = 0; boxCol < board.getBoxDimensions(); boxCol++) {
        JFormattedTextField field = createNumberField();
        int rowCoord = (row * 3) + boxRow;
        int colCoord = (col * 3) + boxCol;
        controller.bindCell(rowCoord, colCoord, field);
        matrix.add(field);
      }
    }
  }

  private JFormattedTextField createNumberField() {
    JFormattedTextField field = new JFormattedTextField();
    field.setPreferredSize(new Dimension(15,30));
    field.setHorizontalAlignment(JTextField.CENTER);
    field.setText(" ");
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