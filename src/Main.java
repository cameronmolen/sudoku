public class Main {

  public static void main(String[] args) {
    SudokuBoard sudokuBoard = new SudokuBoard(Difficulty.REGULAR);
    System.out.println(sudokuBoard.toString());
    SudokuGame gui = new SudokuGame();
    gui.playGame();
  }

}
