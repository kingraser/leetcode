package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class AlphabetBoardPath {
	/*
	On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].
	Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.

	We may make the following moves:
	'U' moves our position up one row, if the position exists on the board;
	'D' moves our position down one row, if the position exists on the board;
	'L' moves our position left one column, if the position exists on the board;
	'R' moves our position right one column, if the position exists on the board;
	'!' adds the character board[r][c] at our current position (r, c) to the answer.
	(Here, the only positions that exist on the board are positions with letters on them.)
	Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return any path that does so.

	Example 1:
	Input: target = "leet"
	Output: "DDR!UURRR!!DDD!"

	Example 2:
	Input: target = "code"
	Output: "RR!DDRR!UUL!R!"

	Constraints:
	1 <= target.length <= 100
	target consists only of English lowercase letters.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{{"RDD!UURRR!!DDD!", "leet"}, {"RR!RRDD!UUL!R!", "code"}});
	}

	public String alphabetBoardPath(String target) {
		int length = target.length(), size = 0;
		char[] result = new char[length * 10];
		for (int i = 0, lastRow = 0, lastCol = 0, row, col, c, diff; i < length; result[size++] = '!', lastRow = row, lastCol = col) {
			for (row = (c = target.charAt(i++) - 'a') / 5, col = c % 5, diff = lastRow - row; diff-- > 0; ) result[size++] = 'U';
			for (diff = col - lastCol; diff-- > 0; ) result[size++] = 'R';
			for (diff = lastCol - col; diff-- > 0; ) result[size++] = 'L';
			for (diff = row - lastRow; diff-- > 0; ) result[size++] = 'D';
		}
		return new String(result, 0, size);
	}
}
