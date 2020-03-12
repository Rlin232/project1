/**
 * @author Ryan Lin
 */
public class Puzzle { 
    protected Square[][] board;  //the board itself
    int K; // No. Of missing digits(higher the K, higher the difficulty)
  
    /**
     * Creates an empty board of new objects of type Square
     * @param K number of digits that is taken away from the board
     */
    public Puzzle(int K) {
        this.K = K; 
        board = new Square[9][9]; 
        for(int i = 0; i < board.length; i++) {
        	for(int j = 0; j < board[i].length; j++) {
        		board[i][j] = new Square();
        	}
        }
    }
//Board creation methods
    /**
     * Postcondition: a filled board
     */
    public void fillValues() { 
        // Fill the diagonals 
        fillDiagonal(); 
  
        // Fill remaining
        fillRemaining(0, 3); 
  
        // Remove random
        removeKDigits(); 
    }
    /**
     * Postcondition: a board that has a diagonal configuration of 3x3 matrices filled
     */
    private void fillDiagonal() {  
        for (int i = 0; i < 9; i=i+3) {
            // for diagonal box, start coordinates->i==j 
            fillBox(i, i); 
        }
    } 
    /**
     * Postcondition: a 3x3 matrix is filled randomly with digits 1-9
     * @param row the row of the box you want to fill
     * @param col the column of the box you want to fill
     */
    private void fillBox(int row,int col) { 
        int num; 
        for (int i = 0; i < 3; i++) { 
            for (int j = 0; j < 3; j++) { 
                do { 
                    num = randomGenerator(9);
                }
                while (!unUsedInBox(row, col, num)); 
  
                board[row + i][col + j].setValue(num);
                board[row + i][col + j].setIsInitial(true);
            } 
        } 
    }
    /**
     * Precondition: a board that has its diagonal 3x3 matrices filled
     * Postcondition: a fully filled board
     * @param i
     * @param j
     */
    private boolean fillRemaining(int i, int j) { 
        //  System.out.println(i+" "+j); 
        if (j >= 9 && i < 8) { 
            i = i + 1; 
            j = 0; 
        } 
        if (i >= 9 && j >= 9) {
            return true; 
        }
        if (i < 3) { 
            if (j < 3) {
            	j = 3; 
            }
        } else if (i < 6) { 
            if (j == (int)(i/3)*3) { 
                j =  j + 3; 
            }
        } else { 
            if (j == 6) { 
                i = i + 1; 
                j = 0; 
                if (i >= 9) { 
                    return true; 
                }
            } 
        } 
  
        for (int num = 1; num <= 9; num++) { 
            if (checkIfSafe(i, j, num)) { 
                board[i][j].setValue(num); 
                board[i][j].setIsInitial(true);
                if (fillRemaining(i, j+1)) {
                    return true;
                }
                board[i][j].setValue(0); 
            } 
        } 
        return false; 
    }
    /**
     * Precondition: a full board of randomly generated digits 1-9 is created
     * Postcondition: According to the amount of digits(previously specified)
     * a certain amount of numbers will be removed from the full board
     */
    public void removeKDigits() {
    	int count = K; 
    	while (count != 0) { 
            int cellId = randomGenerator(81); 
            // extract coordinates i  and j 
            int i = (cellId / 9); 
            int j = cellId % 9; 
            if (j != 0) {
            	j = j - 1;
            }
            if (i != 0) {
            	i = i -1;
            }
            // System.out.println(i+" "+j); 
            if (board[i][j].getValue() != 0) { 
            	count--; 
            	board[i][j].setValue(0); 
            	board[i][j].setIsInitial(false);
            }
        } 
    } 

    // Random generator method(for convenience purposes)
    public int randomGenerator(int num) { 
        return (int) Math.floor((Math.random()*num+1)); 
    } 

//Checking methods
    /**
     * Tells the program if a certain number is used in the box
     * @param rowStart starting row of the box
     * @param colStart starting column of the box
     * @param num the number we want to know if it is used
     * @return true if the number is unused, false if number is used
     */
    boolean unUsedInBox(int rowStart, int colStart, int num) { 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[rowStart+i][colStart+j].getValue() == num) {
                    return false; 
                }
            }
        }
        return true; 
    } 
    /**
     * Tests if a number is unused in a row
     * @param row the row we want to test
     * @param num the number we want to know if it is used
     * @return true if the number is unused, false if number is used
     */
    boolean unUsedInRow(int row,int num) { 
        for (int j = 0; j < 9; j++) {
           if (board[row][j].getValue() == num) {
                return false; 
           }
        }
        return true; 
    } 
    /**
     * Tests if a number is unused in a column
     * @param col the column we want to test
     * @param num the number we want to know if it is used
     * @return true if the number is unused, false if number is used
     */
    boolean unUsedInCol(int col,int num) { 
        for (int i = 0; i < 9; i++) {
            if (board[i][col].getValue() == num) {
                return false; 
            }
        }
        return true; 
    }
    /**
     * Tests if a number is "safe" to put in a square
     * @param row row the number is put in
     * @param col column the number is put in
     * @param num number that is to be put in board
     * @return
     */
    private boolean checkIfSafe(int row,int col,int num) { 
        return (unUsedInRow(row, num) && 
                unUsedInCol(col, num) && 
                unUsedInBox(row-row%3, col-col%3, num)); 
    }

//Misc Methods
    /**
     * This method is for use of other methods that wish to modify the board
     * @return the array that is the board
     */
    public Square[][] getBoard() {
    	return this.board;
    }   
    /**
     * Prints out the current state of the board(with borders for ease of reading)
     */
    public void printSudoku() {
    	System.out.println();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j].getValue() + " "); 
                if(j==2 || j==5) {
                	System.out.print("| ");
                }
            }
            System.out.println();
            if(i==2 || i==5)
            	System.out.println("------+-------+------");
        } 
        System.out.println(); 
    } 
}