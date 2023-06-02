package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MaximumNumberOfMovesInAGrid {
	/*
	You are given a 0-indexed m x n matrix grid consisting of positive integers.
	You can start at any cell in the first column of the matrix, and traverse the grid in the following way:
	From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and (row + 1, col + 1) such that the value of the cell you move to, should be strictly bigger than the value of the current cell.
	Return the maximum number of moves that you can perform.

	Example 1:
	Input: grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
	Output: 3
	Explanation: We can start at the cell (0, 0) and make the following moves:
	- (0, 0) -> (0, 1).
	- (0, 1) -> (1, 2).
	- (1, 2) -> (2, 3).
	It can be shown that it is the maximum number of moves that can be made.

	Example 2:
	Input: grid = [[3,2,4],[2,1,9],[1,1,7]]
	Output: 0
	Explanation: Starting from any cell in the first column we cannot perform any moves.

	Constraints:
	m == grid.length
	n == grid[i].length
	2 <= m, n <= 1000
	4 <= m * n <= 10^5
	1 <= grid[i][j] <= 10^6
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, TestUtil.getArray("[[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]")},
				{0, TestUtil.getArray("[[3,2,4],[2,1,9],[1,1,7]]")},
		});
	}

	int flag = 1 << 31, lastRow, lastCol, grid[][];

	public int maxMoves(int[][] grid) {
		int result = 0;
		lastRow = (this.grid = grid).length - 1;
		lastCol = grid[0].length - 1;
		for (int row = 0; row <= lastRow; ) result = Math.max(result, dfs(row++, 0));
		return result;
	}

	int dfs(int row, int col) {
		if (grid[row][col] < 0) return (flag ^ grid[row][col]) >> 20;
		if (col == lastCol) return 0;
		int val = 0, nextCol = col + 1;
		if (grid[row][nextCol] > grid[row][col]) val = Math.max(val, 1 + dfs(row, nextCol));
		if (row > 0 && grid[row - 1][nextCol] > grid[row][col]) val = Math.max(val, 1 + dfs(row - 1, nextCol));
		if (row < lastRow && grid[row + 1][nextCol] > grid[row][col]) val = Math.max(val, 1 + dfs(row + 1, nextCol));
		grid[row][col] |= (val << 20) | flag;
		return val;
	}
}
