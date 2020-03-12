import java.util.Arrays;

/**
 * @author Ryan Lin
 */
public class SudokuPuzzle extends Puzzle{
	public SudokuPuzzle(int K) {
		super(K);
		this.fillValues();
	}
	
	/**
	 * Adds a value that is a guess, and can be changed by the user
	 * @param row Row coordinate
	 * @param col Column coordinate
	 * @param value User's guess
	 */
	public void addGuess(int row, int col, int value) {
		if(board[row][col].getIsInitial())
			System.out.println("That space is pre-set and cannot be changed.");
		else if(checkAllowed(row, col, value) || value == 0)
			board[row][col].setValue(value);
		else
			System.out.println("Duplilcate number in row column or 3x3 square");
	}
	
	/**
	 * Checks if number is allowed in square (basically a re-purposed CheckIfSafe method)
	 * @param row Row coordinate
	 * @param col Column coordinate
	 * @param num Number to be checked
	 * @return True if number is allowed
	 */
	public boolean checkAllowed(int row, int col, int num) {  
		for(int i = 0; i < this.board.length; i++) {
			if(unUsedInRow(row, num) && unUsedInCol(col, num) && unUsedInBox(row - row % 3, col - col % 3, num) ) {
				
			} else {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if a list of nine integers is "valid" (has integers 1-9 and has each only once)
	 * @param check The int[] to be tested
	 * @return True if array is valid
	 */
	public boolean isValid(int[] check) { 
	    int counter = 0;
	    Arrays.sort(check);
	    for (int number : check) {
	        if (number != ++counter)
	            return false;
	    }
	    return true;
	}
	
	/**
	 * Checks if the Sudoku board is solved
	 * @param board Board that is being tested
	 * @return True if solved
	 */
	public boolean checkPuzzle(Square[][] board) {
	    for (int i = 0; i < 9; i++) {

	        int[] row = new int[9];
	        int[] square = new int[9];
	        Square[] temp = board[i].clone();
	        int[] column = new int[temp.length];
	        for(int i1 = 0; i1 < temp.length; i1++) {
	        	column[i1] = temp[i1].getValue();
	        }

	        for (int j = 0; j < 9; j ++) {
	            row[j] = board[j][i].getValue();
	            square[j] = board[(i / 3) * 3 + j / 3][i * 3 % 9 + j % 3].getValue();
	        }
	        if (!(isValid(column) && isValid(row) && isValid(square)))
	            return false;
	    }
	    return true;
	}
	
	
	/**
	 * Returns value in a certain row and column
	 * @param row Row coordinate
	 * @param col Colum coordinate
	 * @return Value in coordinates
	 */
	public int getValueIn(int row, int col) {
		return board[row][col].getValue();
	}
	
	/**
	 * Returns a one-D array of nine booleans that corresponds to each of the
	 * digits and is true if the digit can be placed within the square
	 * @param row Row coordinate
	 * @param col Column coordinate
	 * @return The array of allowed digits
	 */
	public boolean[] getAllowedValues(int row, int col) {
		boolean[] allowed = new boolean[9];
		for(int i = 0; i < allowed.length; i++) {
			if(checkAllowed(row, col, i + 1)) {
				allowed[i] = true;
			}
		}
		return allowed;
	}
	/**
	 * @return Returns true if all the values in the puzzle are filled
	 */
	public boolean isFull() {
		boolean valid = true;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; i++) {
				if(board[i][j].getValue() == 0) {
					valid = false;
					return valid;
				}
			}
		}
		return valid;
	}
	
	/**
	 * Resets all inputed values in board to zero
	 */
	public void reset() {
	 	for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(!board[i][j].getIsInitial())
					board[i][j].setValue(0);
			}
		}
	}
}