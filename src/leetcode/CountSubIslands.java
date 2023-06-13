package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountSubIslands {
	/*
	You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.
	An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.
	Return the number of islands in grid2 that are considered sub-islands.

	Example 1:
	Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
	Output: 3
	Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
	The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.

	Example 2:
	Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
	Output: 2
	Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
	The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.

	Constraints:
	m == grid1.length == grid2.length
	n == grid1[i].length == grid2[i].length
	1 <= m, n <= 500
	grid1[i][j] and grid2[i][j] are either 0 or 1.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, TestUtil.getArray("[[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]]"), TestUtil.getArray("[[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]")},
				{2, TestUtil.getArray("[[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]]"), TestUtil.getArray("[[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]")},
		});
	}

	int grid[][], rowSize, colSize;

	public int countSubIslands(int[][] grid1, int[][] grid2) {
		rowSize = (grid = grid2).length;
		colSize = grid[0].length;
		int result = 0;
		for (int row = 0; row < rowSize; row++)
			for (int col = 0; col < colSize; col++)
				if (grid2[row][col] == 1 && grid1[row][col] == 0) dfs(row, col);
		for (int row = 0; row < rowSize; row++)
			for (int col = 0; col < colSize; col++)
				if (grid2[row][col] == 1) {
					dfs(row, col);
					result++;
				}
		return result;
	}

	void dfs(int row, int col) {
		if (row < 0 || row >= rowSize || col < 0 || col >= colSize || grid[row][col] == 0) return;
		grid[row][col] = 0;
		dfs(row + 1, col);
		dfs(row, col + 1);
		dfs(row, col - 1);
		dfs(row - 1, col);
	}
}
