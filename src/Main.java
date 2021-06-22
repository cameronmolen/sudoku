public class Main {

  public static void main(String[] args) {
    SudokuBoard sudokuBoard = new SudokuBoard(Difficulty.REGULAR);
    SudokuGame gui = new SudokuGame();
    gui.playGame(sudokuBoard);
  }

}
