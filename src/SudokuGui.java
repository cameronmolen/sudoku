import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

public class SudokuGui extends JFrame {
  private final int GUI_DIMENSIONS = 550;
  private final SudokuBoard board;
  private final SudokuController controller;

  /** Constructor for SudokuGui class. */
  public SudokuGui(SudokuBoard sudokuBoard) {
    board = sudokuBoard;
    controller = new SudokuController(sudokuBoard);
    try {
      System.setProperty("apple.laf.useScreenMenuBar", "true");
      System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Stack");
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    setTitle("Sudoku");
    setJMenuBar(createMenu());

    getContentPane().add(createGrid(), BorderLayout.CENTER);
    getContentPane().add(createStats(), BorderLayout.NORTH);

    setMinimumSize(new Dimension(GUI_DIMENSIONS,GUI_DIMENSIONS));
    setMaximumSize(new Dimension(GUI_DIMENSIONS,GUI_DIMENSIONS));
    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  /** Sets the listener for the SudokuController. */
  public void setListener(SudokuListener listener) {
    controller.setListener(listener);
  }

  /**
   * Creates and returns the GUI menu.
   * @return the JMenuBar to be used by the GUI
   */
  private JMenuBar createMenu() {
    JMenuBar menuBar = new JMenuBar();
    MenuItemListener menuItemListener = new MenuItemListener();
    JMenu newGameMenu = new JMenu("New Game");
    
    JMenuItem easyMenuItem = new JMenuItem("Easy");
    easyMenuItem.setActionCommand("EASY");
    easyMenuItem.addActionListener(menuItemListener);

    JMenuItem regularMenuItem = new JMenuItem("Regular");
    regularMenuItem.setActionCommand("REGULAR");
    regularMenuItem.addActionListener(menuItemListener);

    JMenuItem challengingMenuItem = new JMenuItem("Challenging");
    challengingMenuItem.setActionCommand("CHALLENGING");
    challengingMenuItem.addActionListener(menuItemListener);

    newGameMenu.add(easyMenuItem);
    newGameMenu.add(regularMenuItem);
    newGameMenu.add(challengingMenuItem);
    menuBar.add(newGameMenu);
    return menuBar;
  }

  /**
   * Generates the entire sudoku grid to be displayed in the GUI.
   * @return a JPanel of the sudoku grid
   */
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

  /**
   * Creates a JPanel matrix that contains a GridLayout with the specified dimensions for each matrix in the SudokuBoard.
   * @param distanceBetween the spacing between each element in the grid
   * @return the JPanel matrix
   */
  private JPanel createMatrices(int distanceBetween) {
    GridLayout gridLayout = new GridLayout(board.getBoxDimensions(), board.getBoxDimensions(), 1, 1);
    gridLayout.setHgap(distanceBetween);
    gridLayout.setVgap(distanceBetween);
    return new JPanel(gridLayout);
  }

  /**
   * Populates each field in a matrix with generated values from the SudokuBoard class or creates an
   * editable blank field for the player to enter in guesses.
   * @param matrix a JPanel matrix to be populated with digits
   * @param row the row that the matrix is in on the board
   * @param col the column that the matrix is in on the board
   */
  private void populateMatrix(JPanel matrix, int row, int col) {
    for(int boxRow = 0; boxRow < board.getBoxDimensions(); boxRow++) {
      for(int boxCol = 0; boxCol < board.getBoxDimensions(); boxCol++) {
        JFormattedTextField field;
        int rowCoord = (row * board.getBoxDimensions()) + boxRow;
        int colCoord = (col * board.getBoxDimensions()) + boxCol;
        if(board.getBoard()[rowCoord][colCoord] != 0) {
          field = createLockedNumberField(String.valueOf(board.getBoard()[rowCoord][colCoord]));
        } else {
          field = createEditableNumberField();
        }
        controller.bindCell(rowCoord, colCoord, field);
        matrix.add(field);
      }
    }
  }

  /**
   * Creates an editable field that the user can input a single digit into.
   * @return the editable field
   */
  private JFormattedTextField createEditableNumberField() {
    JFormattedTextField field = new JFormattedTextField();
    field.setPreferredSize(new Dimension(15,15));
    field.setHorizontalAlignment(JTextField.CENTER);
    field.setText("");
    field.setBorder(null);
    field.setInputVerifier(new InputVerifier() {
      public boolean verify(JComponent input) {
        return field.getText().isEmpty() || (field.getText().length() <= 1 &&
                Character.isDigit(field.getText().charAt(0)));
      }
    });
    return field;
  }

  /**
   * Creates a locked field that the displays the locked digits generated by SudokuBoard.
   * @return the locked field
   */
  private JFormattedTextField createLockedNumberField(String value) {
    JFormattedTextField field = new JFormattedTextField();
    field.setPreferredSize(new Dimension(15,15));
    field.setEditable(false);
    field.setBackground(Color.WHITE);
    field.setHorizontalAlignment(JTextField.CENTER);
    field.setText(value);
    field.setBorder(null);
    return field;
  }

  /**
   * Creates the top JPanel that displays the title, time elapsed, and the submit button.
   * @return JPanel with added components
   */
  private JPanel createStats() {
    JPanel layout = new JPanel(new GridBagLayout());

    JLabel titleLabel = new JLabel("Sudoku");
    System.out.println(titleLabel.getFont());
    titleLabel.setFont(new Font("Dialog", Font.BOLD , 16));

    JLabel timeLabel = new JLabel("Time Elapsed: 0:00"); // TODO: Fix the alignment of each of these components. Should be evenly spaced with title in center
    controller.bindTimeLabel(timeLabel);

    JButton submitButton = new JButton("Submit");
    submitButton.setBorderPainted(false);
    submitButton.setBackground(Color.WHITE);
    submitButton.setFocusPainted(false);

    GridBagConstraints constraints = getConstraints();
    constraints.fill = GridBagConstraints.CENTER;
    constraints.anchor = GridBagConstraints.SOUTH;

    layout.add(timeLabel, constraints);
    layout.add(titleLabel, constraints);
    layout.add(submitButton, constraints);
    layout.setPreferredSize(new Dimension(GUI_DIMENSIONS, 30));
    return layout;
  }

  /**
   * Generates new GridBagConstraints and returns them.
   * @return the GridBagConstraints
   */
  private GridBagConstraints getConstraints() {
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1.0;
    constraints.weighty = 1.0;
    return constraints;
  }

}

/** Calls a method based on the menu item that is selected. */
class MenuItemListener implements ActionListener {
  public void actionPerformed(ActionEvent e) {
    // Start new game with the specified difficulty
    SudokuBoard newBoard;
    if(e.getActionCommand().equals(Difficulty.EASY.toString())) {
      newBoard = new SudokuBoard(Difficulty.EASY);
    } else if(e.getActionCommand().equals(Difficulty.REGULAR.toString())) {
      newBoard = new SudokuBoard(Difficulty.REGULAR);
    } else {
      newBoard = new SudokuBoard(Difficulty.CHALLENGING);
    }
    SudokuPlay play = new SudokuPlay();
    play.run(newBoard);
  }
}