import java.util.*;

public class SudokuBoard {

  int[][] board;
  int BOARD_DIMENSIONS = 9;
  int BOX_DIMENSIONS = 3;

  /** Constructor for SudokuBoard class. */
  public SudokuBoard() {
    board = new int[BOARD_DIMENSIONS][BOARD_DIMENSIONS];
    createBoard();
  }

  /** Generates a solvable sudoku puzzle. */
  private void createBoard() {
    // Generate list of values from 1-9
    ArrayList<Integer> values = new ArrayList<>();
    for(int value = 1; value <= BOARD_DIMENSIONS; value++) values.add(value); // TODO: Check if this can be moved into generateDiagonal()?

    generateDiagonalMatrices(values);
    generateOtherMatrices(values);

    System.out.println(toString());
  }

  /**
   * Fills in the matrices on the diagonal of the sudoku board with numbers 1-9.
   * @param values list of integers from 1-9
   */
  private void generateDiagonalMatrices(ArrayList<Integer> values) {
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
  private void generateOtherMatrices(ArrayList<Integer> values) {

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
