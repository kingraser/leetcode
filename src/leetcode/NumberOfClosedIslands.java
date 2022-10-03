package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class NumberOfClosedIslands {
	/*
	Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
	Return the number of closed islands.

	Example 1:
	Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
	Output: 2
	Explanation:
	Islands in gray are closed because they are completely surrounded by water (group of 1s).

	Example 2:
	Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
	Output: 1

	Example 3:
	Input: grid = [[1,1,1,1,1,1,1],
				   [1,0,0,0,0,0,1],
				   [1,0,1,1,1,0,1],
				   [1,0,1,0,1,0,1],
				   [1,0,1,1,1,0,1],
				   [1,0,0,0,0,0,1],
				   [1,1,1,1,1,1,1]]
	Output: 2

	Constraints:
	1 <= grid.length, grid[0].length <= 100
	0 <= grid[i][j] <=1
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, TestUtil.getArray("[[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]")},
				{2, TestUtil.getArray("[[1,1,1,1,1,1,1],[1,0,0,0,0,0,1],[1,0,1,1,1,0,1],[1,0,1,0,1,0,1],[1,0,1,1,1,0,1],[1,0,0,0,0,0,1],[1,1,1,1,1,1,1]]")},
				{1, TestUtil.getArray("[[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]")},
		});
	}

	public int closedIsland(int[][] grid) {
		int result = 0;
		for (int row = 1, lastRow = grid.length - 1, lastCol = grid[0].length - 1; row < lastRow; row++)
			for (int col = 1; col < lastCol; col++)
				if (grid[row][col] == 0 && dfs(grid, row, col) < 2) result++;
		return result;
	}

	int dfs(int[][] grid, int row, int col) {
		if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return 2;
		if (grid[row][col] == 1) return 1;
		grid[row][col] = 1;
		return dfs(grid, row - 1, col) | dfs(grid, row + 1, col) | dfs(grid, row, col - 1) | dfs(grid, row, col + 1);
	}

}
