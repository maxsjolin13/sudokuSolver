package test;

import static org.junit.jupiter.api.Assertions.*;
import sudoku.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuTests {
	sudoku s1;
	sudoku s2;
	sudoku s3;

	@BeforeEach
	void setUp() throws Exception {
		this.s1 = new sudoku();
		this.s2 = new sudoku();
		this.s3 = new sudoku();
	}

	@AfterEach
	void tearDown() throws Exception {
		this.s1 = null;
		this.s2 = null;
		this.s3 = null;
	}

	/*
	 * Fills s1 with empty board
	 */
	@Test
	void allZerosSolve() {
		int[][] a = new int[s1.getDimension()][s1.getDimension()];
		for(int i = 0; i < s1.getDimension(); i ++) {
			for (int j = 0; j < s1.getDimension(); j++) {
				a[i][j] = 0;
			}
		}
		s1.setMatrix(a);
		
		assertEquals(true, s1.solve(), "Cannot solve empty board");
	}

}
