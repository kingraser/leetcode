package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CheckKnightTourConfiguration {
	/*
	There is a knight on an n x n chessboard. In a valid configuration, the knight starts at the top-left cell of the board and visits every cell on the board exactly once.
	You are given an n x n integer matrix grid consisting of distinct integers from the range [0, n * n - 1] where grid[row][col] indicates that the cell (row, col) is the grid[row][col]th cell that the knight visited. The moves are 0-indexed.
	Return true if grid represents a valid configuration of the knight's movements or false otherwise.
	Note that a valid knight move consists of moving two squares vertically and one square horizontally, or two squares horizontally and one square vertically. The figure below illustrates all the possible eight moves of a knight from some cell.

	Example 1:
	Input: grid = [[0,11,16,5,20],[17,4,19,10,15],[12,1,8,21,6],[3,18,23,14,9],[24,13,2,7,22]]
	Output: true
	Explanation: The above diagram represents the grid. It can be shown that it is a valid configuration.

	Example 2:
	Input: grid = [[0,3,6],[5,8,1],[2,7,4]]
	Output: false
	Explanation: The above diagram represents the grid. The 8th move of the knight is not valid considering its position after the 7th move.

	Constraints:
	n == grid.length == grid[i].length
	3 <= n <= 7
	0 <= grid[row][col] < n * n
	All integers in grid are unique.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{true, TestUtil.getArray("[[0,11,16,5,20],[17,4,19,10,15],[12,1,8,21,6],[3,18,23,14,9],[24,13,2,7,22]]")},
				{false, TestUtil.getArray("[[0,3,6],[5,8,1],[2,7,4]]")}
		});
	}

	public boolean checkValidGrid(int[][] grid) {
		if (grid[0][0] != 0) return false;
		int length = grid.length, map[] = new int[length * length];
		for (int row = 0; row < length; row++)
			for (int col = 0; col < length; col++) map[grid[row][col]] = (row << 3) | col;
		for (int i = 1, lastRow = 0, lastCol = 0, row, col, rowDiff, colDiff; i < map.length; lastRow = row, lastCol = col)
			if (Math.min(rowDiff = Math.abs(lastRow - (row = map[i] >> 3)), colDiff = Math.abs(lastCol - (col = map[i++] & 7))) != 1 || Math.max(rowDiff, colDiff) != 2)
				return false;
		return true;
	}
}
