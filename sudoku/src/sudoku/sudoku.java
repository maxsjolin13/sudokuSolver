package sudoku;


/**
 * A simple sudoku implementation of the sudoku interface.
 * 
 * @author erikb, maxsj.
 *
 */
public class sudoku implements SudokuSolver {
	private int [][] grid;
	private int dim;
	 
	/**
	 * Constructs an empty sudoku grid with default dimensions (9x9).
	 * 
	 */
	public sudoku() {
		this.dim = getDimension();
		grid = new int[dim][dim];
	}

	@Override
	public void setNumber(int r, int c, int nbr) {
		if(	r < 0 ||
			r > dim-1 ||
			c < 0 ||
			c > dim-1 ||
			nbr < 0 ||
			nbr > dim) {
			throw new IllegalArgumentException();
		}
		
		grid[r][c] = nbr;
	}

	@Override
	public int getNumber(int r, int c) {
		if(	r < 0 ||
			r > dim-1 ||
			c < 0 ||
			c > dim-1) {
			throw new IllegalArgumentException();
		}
		
		return grid[r][c];
	}

	@Override
	public void clearNumber(int r, int c) {
		if(	r < 0 ||
			r > dim-1 ||
			c < 0 ||
			c > dim-1) {
			throw new IllegalArgumentException();
		}
		
		grid[r][c] = 0;
	}

	@Override
	public boolean isValid(int r, int c, int nbr) {
		if(	r < 0 ||
			r > dim-1 ||
			c < 0 ||
			c > dim-1 ||
			nbr < 0 ||
			nbr > dim) {
			throw new IllegalArgumentException();
		}
		
		for(int i = 0; i < dim; i++) {
			if((getNumber(i, c) == nbr && i != r) || (getNumber(r, i) == nbr && i != c))  {
				return false;
			}
		}
		
		int modr= r%3;
		int modc = c%3;
		
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				if((getNumber(r+i-modr, c+j-modc) == nbr) && (r+i-modr != r && c+j-modc != c)) {
					return false;
				}
			}
		}
		
		return true;
	}

	@Override
	public boolean isAllValid() {
		for(int r = 0; r < dim; r++) {
			for(int c = 0; c < dim; c++) {
				if(getNumber(r, c) != 0 && !isValid(r, c, getNumber(r, c))) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean solve() {
		return solve(0,0);
	}
	
	private boolean solve(int r, int c) {
		if(r == dim) {
			return true;
		}
		
		int nbr = getNumber(r,c);

		boolean solved = false;
		
		if(nbr == 0) {
			for(int i = 1; i <= dim; i++) {
				
				if(isValid(r, c, i)) {
					setNumber(r, c, i);
					solved = next(r, c);
				}
			}
			
			if(!solved) {
				clearNumber(r,c);
			}
			return solved;
		} else {
			if (isValid(r, c, nbr)) {
				return next(r,c);
			} else {
				return false;
			}
		}
	}
	
	private boolean next(int r, int c) {
		if(c+1 == dim) {
			return solve(r+1,0);
		} else {
			return solve(r, c+1);
		}
	}

	@Override
	public void clear() {
		for(int r = 0; r < dim; r++) {
			for(int c = 0; c < dim; c++) {
				clearNumber(r,c);
			}
		}
	}

	@Override
	public int[][] getMatrix() {
		int[][] copyGrid = new int[dim][dim];
		for(int r = 0; r < dim; r++) {
			for(int c = 0; c < dim; c++) {
				copyGrid[r][c] = getNumber(r,c);
			}
		}
		return copyGrid;
	}

	@Override
	public void setMatrix(int[][] nbrs) {
		for(int r = 0; r < dim; r++) {
			for(int c = 0; c < dim; c++) {
				setNumber(r,c, nbrs[r][c]);
			}
		}
	} 
	
	/**
	 * Creates a string representation of the sudoku grid.
	 * 
	 * @return The sudoku grid.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int r = 0; r < dim; r++) {
			for(int c = 0; c < dim; c++) {
				sb.append(getNumber(r,c));
				if (c%3 == 2) {
					sb.append(" | ");
				}else {
					sb.append(", ");
				}
			}
			sb.append("\n");
			if (r%3 == 2) {
				sb.append("-----------------------------\n");
			}
		}
		
		return sb.toString();
	}
}
