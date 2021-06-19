public class Main {

  public static void main(String[] args) {
    SudokuGame gui = new SudokuGame();
    gui.playGame();
    SudokuBoard sudokuBoard = new SudokuBoard(Difficulty.REGULAR);
  }

}
