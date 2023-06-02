package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MaximumNumberOfFishInAGrid {
	/*
	You are given a 0-indexed 2D matrix grid of size m x n, where (r, c) represents:

	A land cell if grid[r][c] = 0, or
	A water cell containing grid[r][c] fish, if grid[r][c] > 0.
	A fisher can start at any water cell (r, c) and can do the following operations any number of times:

	Catch all the fish at cell (r, c), or
	Move to any adjacent water cell.
	Return the maximum number of fish the fisher can catch if he chooses his starting cell optimally, or 0 if no water cell exists.

	An adjacent cell of the cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) or (r - 1, c) if it exists.

	Example 1:
	Input: grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
	Output: 7
	Explanation: The fisher can start at cell (1,3) and collect 3 fish, then move to cell (2,3) and collect 4 fish.

	Example 2:
	Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
	Output: 1
	Explanation: The fisher can start at cells (0,0) or (3,3) and collect a single fish.

	Constraints:
	m == grid.length
	n == grid[i].length
	1 <= m, n <= 10
	0 <= grid[i][j] <= 10
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{7, TestUtil.getArray("[[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]")},
				{1, TestUtil.getArray("[[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]")},
		});
	}

	public int findMaxFish(int[][] grid) {
		int result = 0;
		for (int row = 0; row < grid.length; row++)
			for (int col = 0; col < grid[0].length; ) result = Math.max(result, dfs(grid, row, col++));
		return result;
	}

	private int dfs(int[][] grid, int row, int col) {
		if (grid[row][col] == 0) return 0;
		int result = grid[row][col];
		grid[row][col] = 0;
		if (col < grid[0].length - 1) result += dfs(grid, row, col + 1);
		if (row < grid.length - 1) result += dfs(grid, row + 1, col);
		if (col > 0) result += dfs(grid, row, col - 1);
		if (row > 0) result += dfs(grid, row - 1, col);
		return result;
	}
}
