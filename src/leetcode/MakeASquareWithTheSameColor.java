package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.stream.Stream;

/**
 * @author Wit
 */
public class MakeASquareWithTheSameColor {
/*
You are given a 2D matrix grid of size 3 x 3 consisting only of characters 'B' and 'W'. Character 'W' represents the white color, and character 'B' represents the black color.
Your task is to change the color of at most one cell so that the matrix has a 2 x 2 square where all cells are of the same color.
Return true if it is possible to create a 2 x 2 square of the same color, otherwise, return false.

Example 1:
Input: grid = [["B","W","B"],["B","W","W"],["B","W","B"]]
Output: true
Explanation:
It can be done by changing the color of the grid[0][2].

Example 2:
Input: grid = [["B","W","B"],["W","B","W"],["B","W","B"]]
Output: false
Explanation:
It cannot be done by changing at most one cell.

Example 3:
Input: grid = [["B","W","B"],["B","W","W"],["B","W","W"]]
Output: true
Explanation:
The grid already contains a 2 x 2 square of the same color.

Constraints:
    grid.length == 3
    grid[i].length == 3
    grid[i][j] is either 'W' or 'B'.
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{true, Stream.of("BWB", "BWW", "BWB").map(String::toCharArray).toArray(char[][]::new)},
				{false, Stream.of("BWB", "WBW", "BWB").map(String::toCharArray).toArray(char[][]::new)},
				{true, Stream.of("BWB", "BWW", "BWW").map(String::toCharArray).toArray(char[][]::new)},
		});
	}

	public boolean canMakeSquare(char[][] grid) {
		for (int row = 0; row < 2; row++)
			for (int col = 0; col < 2; col++)
				if (canMakeSquare(grid, row, col)) return true;
		return false;
	}

	private boolean canMakeSquare(char[][] grid, int rowStart, int colStart) {
		int whiteCount = 0;
		for (int row = rowStart, rowEnd = rowStart + 2, colEnd = colStart + 2; row < rowEnd; row++)
			for (int col = colStart; col < colEnd; col++) if (grid[row][col] == 'W') whiteCount++;
		return whiteCount != 2;
	}
}
