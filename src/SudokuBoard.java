import java.util.*;

public class SudokuBoard {

  int[][] board;
  int BOARD_DIMENSIONS = 9;

  /** Constructor for SudokuBoard class. */
  public SudokuBoard() {
    board = new int[BOARD_DIMENSIONS][BOARD_DIMENSIONS];
    createBoard();
  }

  /** Generates a solvable sudoku puzzle. */
  private void createBoard() {
    // Generate the matrices along the diagonal
    for(int diagonal = 0; diagonal < 3; diagonal++) {
      for(int row = diagonal * 3; row < (diagonal * 3) + 3; row++) {
        for(int col = diagonal * 3; col < (diagonal * 3); col++) {
          board[row][col] = (int)(Math.random() * 9);
        }
      }
    }
    System.out.println(board);
    System.out.println("TEST");
  }

  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for(int row = 0; row < 9; row++) {
      for(int col = 0; col < 9; col++) {
        stringBuilder.append(board[row][col] + " ");
        if(col == 8) stringBuilder.append("\n");
      }
      if(row == 8) stringBuilder.append("\n");
    }
    return stringBuilder.toString();
  }

}
