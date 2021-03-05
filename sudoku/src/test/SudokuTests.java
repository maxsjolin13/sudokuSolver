package test;

import static org.junit.jupiter.api.Assertions.*;
import sudoku.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuTests {
	private sudoku s1;

	@BeforeEach
	void setUp() throws Exception {
		this.s1 = new sudoku();
	}

	@AfterEach
	void tearDown() throws Exception {
		this.s1 = null;
	}
	
	/*
	 * Gets empty, sets number, and clears cell.
	 */
	@Test
	void getSetClear() {
		s1.clear();
		assertEquals(0, s1.getNumber(0, 0));
		s1.setNumber(0, 0, 9);
		assertEquals(9, s1.getNumber(0, 0));
		s1.clearNumber(0, 0);
		assertEquals(0, 0);
	}
	
	/*
	 * Sets entire matrix to 9, then clear, then checks each cell individually
	 * both manually and with getMatrix().
	 */
	@Test
	void clearAll() {
		int[][] a = new int[s1.getDimension()][s1.getDimension()];
		for(int i = 0; i < s1.getDimension(); i ++) {
			for (int j = 0; j < s1.getDimension(); j++) {
				a[i][j] = 9;
			}
		}
		s1.setMatrix(a);
		assertFalse(s1.isAllValid(), "Not right");
		s1.clear();
		int[][] grid = s1.getMatrix();
		for(int r = 0; r < s1.getDimension(); r ++) {
			for (int c = 0; c < s1.getDimension(); c++) {
				assertEquals(0, s1.getNumber(r, c));
				assertEquals(0, grid[r][c]);
			}
		}
	}

	/*
	 * Fills s1 with empty board, then solves it.
	 */
	@Test
	void SolveAllZeros() {
		int[][] a = new int[s1.getDimension()][s1.getDimension()];
		for(int i = 0; i < s1.getDimension(); i ++) {
			for (int j = 0; j < s1.getDimension(); j++) {
				a[i][j] = 0;
			}
		}
		s1.setMatrix(a);
		
		assertEquals(true, s1.solve(), "Cannot solve empty board");
	}
	
	/*
	 * Creates unsolvable sudoku with duplicate number in first subgrid.
	 */
	@Test
	void SolveUnsolvable() {
		int[][] a = new int[s1.getDimension()][s1.getDimension()];
		for(int i = 0; i < s1.getDimension(); i ++) {
			for (int j = 0; j < s1.getDimension(); j++) {
				a[i][j] = 0;
			}
		}
		s1.setMatrix(a);
		s1.setNumber(0, 0, 9);
		s1.setNumber(2, 2, 9);
		
		assertEquals(false, s1.solve(), "Solved the unsolvable?!");
	}
	
	/*
	 * Fills grid with sudoku from fig.1 and proceeds to solve.
	 */
	@Test
	void SolveFig1() {
		int[][] b = {
				{0, 0, 8, 0, 0, 9, 0, 6, 2},
			    {0, 0, 0, 0, 0, 0, 0, 0, 5},
			    {1, 0, 2, 5, 0, 0, 0, 0, 0},
			    {0, 0, 0, 2, 1, 0, 0, 9, 0},
			    {0, 5, 0, 0, 0, 0, 6, 0, 0},
			    {6, 0, 0, 0, 0, 0, 0, 2, 8},
			    {4, 1, 0, 6, 0, 8, 0, 0, 0},
			    {8, 6, 0, 0, 3, 0, 1, 0, 0},
			    {0, 0, 0, 0, 0, 0, 4, 0, 0},
			    };
		
		s1.setMatrix(b);
		
		assertEquals(true, s1.solve(), "Cannot solve fig.1 sudoku");
	}
	
	@Test
	void SolveEmpty() {
		s1.clear();
		assertTrue(s1.solve(), "Cannot solve empty sudoku");
	}
}
