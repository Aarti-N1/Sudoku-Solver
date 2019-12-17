package hard;

public class SudokuSolver_37 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
						   { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, 
						   { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
						   { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, 
						   { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
						   { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, 
						   { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
						   { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, 
						   { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		solveSudoku(board);

	}

	public static void solveSudoku(char[][] board) {
		//displayBoard(board);
		sudokuHelper(board, 0, 0);
		displayBoard(board);
	}

	public static boolean sudokuHelper(char[][] board, int row, int col) {
		if (col == board[row].length) {
			col = 0;
			row++;
			if (row == board.length) {
				return true;
			}
		}
		if (board[row][col] == '.') {
			char[] possibleValues = possibleValues(board, row, col);
			if (possibleValues.length == 0) {
				return false;
			}
			for (int i = 0; i < possibleValues.length; i++) {
				board[row][col] = possibleValues[i];
				if(sudokuHelper(board, row, col + 1))
					return true;
			}
			board[row][col] = '.';
			return false;
		} else {
			if(sudokuHelper(board, row, col + 1))
				return true;
		}
		return false;
	}

	public static char[] possibleValues(char[][] board, int curRow, int curCol) {
		String possibleVals = new String();
		for (int val = 1; val <= board.length; val++) {
			// check if val is valid in row
			if (isValidInRow(board, curRow, curCol, val)) {
				// check if val is valid in col
				if (isValidInCol(board, curRow, curCol, val)) {
					// check if val is valid in box
					if (isValidInBox(board, curRow, curCol, val)) {
						possibleVals = possibleVals + (char) (val + '0');
					}
				}
			}
		}
		return possibleVals.toCharArray();
	}

	public static boolean isValidInRow(char[][] board, int curRow, int curCol, int curVal) {
		for (int i = 0; i < board.length; i++) {
			if (board[curRow][i] == (char) (curVal + '0')) {
				return false;
			}
		}
		return true;
	}

	public static boolean isValidInCol(char[][] board, int curRow, int curCol, int curVal) {
		for (int i = 0; i < board.length; i++) {
			if (board[i][curCol] == curVal + '0') {
				return false;
			}
		}
		return true;
	}

	public static boolean isValidInBox(char[][] board, int curRow, int curCol, int curVal) {
		int startRow = (curRow / 3) * 3, startCol = (curCol / 3) * 3;
		for (int i = startRow; i < startRow + 3; i++) {
			for (int j = startCol; j < startCol + 3; j++) {
				if (board[i][j] == curVal + '0') {
					return false;
				}
			}
		}
		return true;
	}

	public static void displayBoard(char[][] board) {
		System.out.println("********************************");
		for (int i = 0; i < board.length; i++) {
			System.out.print("{ ");
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + ", ");
			}
			System.out.println("}");
		}
	}
}
