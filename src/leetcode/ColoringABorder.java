package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class ColoringABorder {
	/*
	You are given an m x n integer matrix grid, and three integers row, col, and color. Each value in the grid represents the color of the grid square at that location.
	Two squares belong to the same connected component if they have the same color and are next to each other in any of the 4 directions.
	The border of a connected component is all the squares in the connected component that are either 4-directionally adjacent to a square not in the component, or on the boundary of the grid (the first or last row or column).
	You should color the border of the connected component that contains the square grid[row][col] with color.
	Return the final grid.

	Example 1:
	Input: grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
	Output: [[3,3],[3,2]]

	Example 2:
	Input: grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
	Output: [[1,3,3],[2,3,3]]

	Example 3:
	Input: grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
	Output: [[2,2,2],[2,1,2],[2,2,2]]

	Constraints:
	m == grid.length
	n == grid[i].length
	1 <= m, n <= 50
	1 <= grid[i][j], color <= 1000
	0 <= row < m
	0 <= col < n
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{TestUtil.getArray("[[3,3],[3,2]]"), TestUtil.getArray("[[1,1],[1,2]]"), 0, 0, 3},
				{TestUtil.getArray("[[1,3,3],[2,3,3]]"), TestUtil.getArray("[[1,2,2],[2,3,2]]"), 0, 1, 3},
				{TestUtil.getArray("[[2,2,2],[2,1,2],[2,2,2]]"), TestUtil.getArray("[[1,1,1],[1,1,1],[1,1,1]]"), 1, 1, 2},
		});
	}

	public int[][] colorBorder(int[][] grid, int row, int col, int color) {
		dfs(grid, row, col, grid[row][col], color, new boolean[grid.length][grid[0].length]);
		return grid;
	}

	// return true if not in the connected component
	boolean dfs(int[][] grid, int row, int col, int originalColor, int color, boolean[][] reached) {
		if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return true;
		if (reached[row][col]) return false;
		if (grid[row][col] != originalColor) return true;
		reached[row][col] = true;
		boolean shouldUpdate = false;
		shouldUpdate |= dfs(grid, row + 1, col, originalColor, color, reached);
		shouldUpdate |= dfs(grid, row - 1, col, originalColor, color, reached);
		shouldUpdate |= dfs(grid, row, col + 1, originalColor, color, reached);
		shouldUpdate |= dfs(grid, row, col - 1, originalColor, color, reached);
		if (shouldUpdate) grid[row][col] = color;
		return false;
	}
}
