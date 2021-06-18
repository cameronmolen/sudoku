import java.util.*;

public class SudokuBoard {

  private static int[][] board;
  private static final int BOARD_DIMENSIONS = 9;
  private static final int BOX_DIMENSIONS = 3;

  /** Constructor for SudokuBoard class. */
  public SudokuBoard() {
    board = new int[BOARD_DIMENSIONS][BOARD_DIMENSIONS];
    createBoard();
  }

  /** Returns the 2-dimensional array that contains the sudoku board. */
  public int[][] getBoard() { return board; }

  /** Generates a solvable sudoku puzzle. */
  private void createBoard() {
    generateDiagonalMatrices();
    generateOtherMatrices(0, BOX_DIMENSIONS);

    System.out.println("FINAL BOARD:\n" + toString());
  }

  /** Fills in the matrices on the diagonal of the sudoku board with numbers from 1 to the board size. */
  private void generateDiagonalMatrices() {
    // Generate list of values from 1-BOARD_DIMENSIONS
    ArrayList<Integer> values = new ArrayList<>();
    for(int value = 1; value <= BOARD_DIMENSIONS; value++) values.add(value);

    for(int diagonal = 0; diagonal < BOX_DIMENSIONS; diagonal++) {
      // Randomize number order
      Collections.shuffle(values);
      int i = 0;
      for(int row = diagonal * BOX_DIMENSIONS; row < (diagonal * BOX_DIMENSIONS) + BOX_DIMENSIONS; row++) {
        for (int col=diagonal * BOX_DIMENSIONS; col < (diagonal * BOX_DIMENSIONS) + BOX_DIMENSIONS; col++) {
          board[row][col]=values.get(i++);
        }
      }
    }
  }

  /**
   * Fills in the non-diagonal matrices of the sudoku board with numbers 1-9, following the rules of where
   * numbers can be placed.
   */
  private boolean generateOtherMatrices(int row, int col) {
    // If the column is out of bounds, move to the next row
    if(row < BOARD_DIMENSIONS - 1 && col >= BOARD_DIMENSIONS) {
      row++;
      col = 0;
    }
    // If the row and column are out of bounds, stop recursion
    if(row / BOX_DIMENSIONS == col / BOX_DIMENSIONS) {
      if(col + BOX_DIMENSIONS < BOARD_DIMENSIONS) col += BOX_DIMENSIONS;
      else {
        row++;
        col = 0;
      }
    }
    if(row >= BOARD_DIMENSIONS) return true;

    for(int val = 1; val <= BOARD_DIMENSIONS; val++) {
      if(validCell(row, col, val)) {
        board[row][col] = val;
        // Recursively check if the other cells in the board work with this value, end recursion if they do
        if(generateOtherMatrices(row, col + 1)) {
          return true;
        }
        // If this val doesn't work at this row and column, set the value of the cell to 0 and try again
        board[row][col] = 0;
      }
    }
    return false;
  }

  /**
   * Checks if a cell is valid (no other instances of the value appear in the same row, column, or box).
   * @param row the row of the cell
   * @param col the column of the cell
   * @param val the value of the cell to be checked
   * @return true if the cell is valid
   */
  private boolean validCell(int row, int col, int val) {
    if(board[row][col] != 0) return false;
    return validRow(row, val) && validCol(col, val) &&
            validBox(row - (row % BOX_DIMENSIONS), col - (col % BOX_DIMENSIONS), val);
  }

  /**
   * Checks if a row contains the specified value and returns true if the row is valid (no other
   * instances of the value appear).
   * @param row row to check
   * @param val value of the cell
   * @return true if the row is valid
   */
  private boolean validRow(int row, int val) {
    for(int col = 0; col < BOARD_DIMENSIONS; col++) {
      if(board[row][col] == val) return false;
    }
    return true;
  }

  /**
   * Checks if a column contains the specified value and returns true if the column is valid (no other
   * instances of the value appear).
   * @param col column to check
   * @param val value of the cell
   * @return true if the column is valid
   */
  private boolean validCol(int col, int val) {
    for(int row = 0; row < BOARD_DIMENSIONS; row++) {
      if(board[row][col] == val) return false;
    }
    return true;
  }

  /**
   * Checks if a box contains the specified value and returns true if the box is valid (no other instances
   * of the value appear in the box).
   * @param startingRow the row at which the box begins (top left corner)
   * @param startingCol the column at which the box begins (top left corner)
   * @param val value of the cell
   * @return true if the box is valid
   */
  private boolean validBox(int startingRow, int startingCol, int val) {
    for(int row = startingRow; row < startingRow + BOX_DIMENSIONS; row++) {
      for(int col = startingCol; col < startingCol + BOX_DIMENSIONS; col++) {
        if(board[row][col] == val) return false;
      }
    }
    return true;
  }

  /**
   * Returns a String representation of the sudoku board.
   * @return String of sudoku board
   */
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for(int row = 0; row < 9; row++) {
      for(int col = 0; col < 9; col++) {
        stringBuilder.append(board[row][col]).append(" ");
        if(col == 8) stringBuilder.append("\n");
      }
      if(row == 8) stringBuilder.append("\n");
    }
    return stringBuilder.toString();
  }

}
