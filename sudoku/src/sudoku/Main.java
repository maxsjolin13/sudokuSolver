package sudoku;

public class Main {	
	final static int [] [] board1 = {
			{5, 3, 0, 0, 7, 0, 0, 0, 0},
		    {6, 0, 0, 1, 9, 5, 0, 0, 0},
		    {0, 9, 8, 0, 0, 0, 0, 6, 0},
		    {8, 0, 0, 0, 6, 0, 0, 0, 3},
		    {4, 0, 0, 8, 0, 3, 0, 0, 1},
		    {7, 0, 0, 0, 2, 0, 0, 0, 6},
		    {0, 6, 0, 0, 0, 0, 2, 8, 0},
		    {0, 0, 0, 4, 1, 9, 0, 0, 5},
		    {0, 0, 0, 0, 8, 0, 0, 7, 9},
		    };

	public static void main(String[] args) {
		sudoku s = new sudoku();
		s.setMatrix(board1);
		System.out.println(s.solve());
		System.out.print(s.toString());
	}

}
