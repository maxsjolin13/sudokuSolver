package GUI;

import sudoku.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class sudokuController {
	public sudokuController(SudokuSolver sudoku) {
        SwingUtilities.invokeLater(() -> createWindow(sudoku, "Sudoku", 600, 600));
    }
    private void createWindow(SudokuSolver sudoku, String title, int width, int height)  {
    	int[][] grid = sudoku.getMatrix();
    	int dim = sudoku.getDimension();
    	
    	
    	JFrame frame = new JFrame(title);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	Container pane = frame.getContentPane();
    	JOptionPane opt = new JOptionPane();
    	
    	
    	Font font1 = new Font("SansSerif", Font.BOLD, 28);
    	
    	JPanel fields = new JPanel();
    	fields.setLayout(new GridLayout(dim, dim));
    	JTextField[][] textGrid = new JTextField[dim][dim];
    	for (int r = 0; r < dim; r ++) {
    		for (int c = 0; c < dim; c ++) {
    			textGrid[r][c] = new JTextField(1);
    			textGrid[r][c].setHorizontalAlignment(JTextField.CENTER);
    			textGrid[r][c].setFont(font1);
    			if (grid[r][c] != 0) {
    				textGrid[r][c].setText(Integer.toString(grid[r][c]));
    			}
    			fields.add(textGrid[r][c]);
        	}
    	}
    	int count = 0;
    	for (int r = 0; r < dim; r = r + 3) {
    		for (int c = 0; c < dim; c = c + 3) {
    			if (count % 2 == 0) {
    				for (int x = 0; x < 3; x ++) {
    					for (int y = 0; y < 3; y ++) {
    						textGrid[r+x][c+y].setBackground(new Color(250, 91, 0));
    					}
    				}
    			}
    			count ++;
    		}
    	}
    	
    	
    	JPanel buttons = new JPanel();
    	JButton clear = new JButton("Clear");
    	JButton solve = new JButton("Solve");
    	buttons.add(solve);
    	buttons.add(clear);
    	solve.addActionListener(event -> {
    		try {
    	  		for (int r = 0; r < dim; r ++) {
            		for (int c = 0; c < dim; c ++) {
            			String text = textGrid[r][c].getText(); 
            			if (text.isEmpty()) {
            				grid[r][c] = 0;
            			} else {
            				int nbr = Integer.parseInt(text);
            				if (nbr == 0) {
            					throw new IllegalArgumentException();
            				} else {
            					grid[r][c] = Integer.parseInt(text);
            				}
            			}
                	}
            	}
    			sudoku.setMatrix(grid);
        		if (!sudoku.isAllValid()) {
        			throw new IllegalArgumentException();
        		}
    			if (sudoku.solve()) {
        			int[][] solved = sudoku.getMatrix();
        	    	for (int r = 0; r < dim; r ++) {
        	    		for (int c = 0; c < dim; c ++) {
        	    			textGrid[r][c].setText(Integer.toString(solved[r][c]));
        	        	}
        	    	}
        		} else {
        			opt.setMessage("No possible solution!");
        			JDialog dialog = opt.createDialog("Error");
        			dialog.setVisible(true);
        		}
    		} catch (Exception e) {
    			opt.setMessage("Invalid input");
    			JDialog dialog = opt.createDialog("Error");
    			dialog.setVisible(true);
    		}

    	});
    	clear.addActionListener(event -> {
    		sudoku.clear();
    		for (int r = 0; r < dim; r ++) {
	    		for (int c = 0; c < dim; c ++) {
	    			textGrid[r][c].setText("");
	        	}
	    	}
    	});

    	    	
    	
    	
    	pane.add(fields);
    	pane.add(buttons, BorderLayout.SOUTH);
    	
    	frame.getRootPane().setDefaultButton(solve);
    	frame.setSize(new Dimension(600, 600));
    	frame.setVisible(true);
    }
}
