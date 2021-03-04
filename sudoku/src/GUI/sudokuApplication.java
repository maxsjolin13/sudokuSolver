package GUI;

import sudoku.*;

public class sudokuApplication {

	public static void main(String[] args) {
		SudokuSolver s1 = new sudoku();
		s1.clear();
		
		sudokuController window = new sudokuController(s1);
	}

}
