package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class ValidTicTacToeState {
	/*
	Given a Tic-Tac-Toe board as a string array board, return true if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.
	The board is a 3 x 3 array that consists of characters ' ', 'X', and 'O'. The ' ' character represents an empty square.
	Here are the rules of Tic-Tac-Toe:
	Players take turns placing characters into empty squares ' '.
	The first player always places 'X' characters, while the second player always places 'O' characters.
	'X' and 'O' characters are always placed into empty squares, never filled ones.
	The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
	The game also ends if all squares are non-empty.
	No more moves can be played if the game is over.

	Example 1:
	Input: board = ["O  ","   ","   "]
	Output: false
	Explanation: The first player always plays "X".

	Example 2:
	Input: board = ["XOX"," X ","   "]
	Output: false
	Explanation: Players take turns making moves.

	Example 3:
	Input: board = ["XOX","O O","XOX"]
	Output: true

	Constraints:
	board.length == 3
	board[i].length == 3
	board[i][j] is either 'X', 'O', or ' '.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{false, new String[]{"O  ", "   ", "   "}},
				{false, new String[]{"XOX", " X ", "   "}},
				{true, new String[]{"XOX", "O O", "XOX"}},
		});
	}

	public boolean validTicTacToe(String[] board) {
		int turns = 0, diag1 = 0, diag2 = 0, rows[] = new int[3], cols[] = new int[3];
		for (int row = 0, c, val; row < 3; row++)
			for (int col = 0; col < 3; col++)
				if ((c = board[row].charAt(col)) == ' ') continue;
				else {
					turns += (val = (c == 'X' ? 1 : -1));
					rows[row] += val;
					cols[col] += val;
					if (row == col) diag1 += val;
					if (row + col == 2) diag2 += val;
				}
		if (turns != 0 && turns != 1) return false;
		boolean firstWin = rows[0] == 3 ^ rows[1] == 3 ^ rows[2] == 3 ^
				cols[0] == 3 ^ cols[1] == 3 ^ cols[2] == 3 ^
				diag1 == 3 ^ diag2 == 3;
		boolean secondWin = rows[0] == -3 || rows[1] == -3 || rows[2] == -3 ||
				cols[0] == -3 || cols[1] == -3 || cols[2] == -3 ||
				diag1 == -3 || diag2 == -3;
		return firstWin ? (!secondWin && turns == 1) : (!secondWin || turns == 0);
	}
}
