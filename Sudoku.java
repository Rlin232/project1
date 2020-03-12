import java.util.Scanner;

public class Sudoku {

	public static void main(String[] args) {
		Scanner user = new Scanner(System.in);
		SudokuPuzzle puzzle = new SudokuPuzzle(20);
		System.out.println("Welcome to Sudoku!\nIntroduction:\n"
				+ "     The spaces with the value 0 are empty.\n"
				+ "     The coordinates of the top left space are row 1, column 1.\n"
				+ "     The coordinates of the bottom right space are row 9, column 9.\n"
				+ "     To clear a single space, simply enter 0 as the value.\n"
				+ "     Restart the puzzle at any time by entering 100.\n"
				+ "     For a hint, enter 777 as the value and the possible values will be displayed.\n"
				+ "     If you wish to quit the game, enter 999 at any time.\n"
				+ "Enjoy!");
		
		int row, col, val;
		while(!puzzle.checkPuzzle(puzzle.getBoard())) {
			//updates puzzle after each guess
			puzzle.printSudoku();
			
			//inputs row
			System.out.println("Please enter desired row number.");
			row = user.nextInt() - 1;
			if(row==99) {
				puzzle.reset();
				continue;
			} else if(row==998) {
				System.out.println("Come back soon!");
				System.exit(0);
			}
			
			//inputs column
			System.out.println("Please enter desired column number.");
			col = user.nextInt() - 1;
			if(col==99) {
				puzzle.reset();
				continue;
			} else if(col==998) {
				System.out.println("Come back soon!");
				System.exit(0);
			}
			
			//inputs value
			System.out.println("Please enter the value.");
			val = user.nextInt();
			if(val==100)
				puzzle.reset();
			else if(val==999) {
				System.out.println("Come back soon!");
				System.exit(0);
			} else if(val==777) {
				System.out.println("The following numbers may be placed in the chosen space without conflict.");
				for(int i = 1; i <= 9; i++) {
					if(puzzle.checkAllowed(row, col, i))
						System.out.print(i + " ");
				}
				System.out.println();
			} else if(!(0 <= val && val <= 9))//checks for a valid value
				System.out.println(val + " is not a valid value.  Please enter an integer between 0 and 9 for the value.");
			else
				puzzle.addGuess(row, col, val);
		}
		//win message
		System.out.println("CONGRATULATIONS!\nYOU SOLVED SUDOKU!!!");
	}

}