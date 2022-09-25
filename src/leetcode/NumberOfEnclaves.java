package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class NumberOfEnclaves {
	/*
	You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
	A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
	Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

	Example 1:
	Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
	Output: 3
	Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.

	Example 2:
	Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
	Output: 0
	Explanation: All 1s are either on the boundary or can reach the boundary.

	Constraints:
	m == grid.length
	n == grid[i].length
	1 <= m, n <= 500
	grid[i][j] is either 0 or 1.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{0, TestUtil.getArray("[[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]")},
				{3, TestUtil.getArray("[[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]")}
		});
	}

	void dfs(int[][] grid, int row, int col) {
		if (grid[row][col] == 0) return;
		grid[row][col] = 0;
		if (row < grid.length - 1) dfs(grid, row + 1, col);
		if (row > 0) dfs(grid, row - 1, col);
		if (col < grid[0].length - 1) dfs(grid, row, col + 1);
		if (col > 0) dfs(grid, row, col - 1);
	}

	public int numEnclaves(int[][] grid) {
		int result = 0, rowSize = grid.length, colSize = grid[0].length, endRow = rowSize - 1, endCol = colSize - 1;
		for (int row = 0; row < grid.length; dfs(grid, row++, endCol)) dfs(grid, row, 0);
		for (int col = 0; col < colSize; dfs(grid, endRow, col++)) dfs(grid, 0, col);
		for (int row = 1; row < endRow; row++)
			for (int col = 1; col < endCol; ) result += grid[row][col++];
		return result;
	}
}
