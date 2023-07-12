package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Wit
 */
public class ShortestBridge {
	/*
	You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
	An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.
	You may change 0's to 1's to connect the two islands to form one island.
	Return the smallest number of 0's you must flip to connect the two islands.

	Example 1:
	Input: grid = [[0,1],[1,0]]
	Output: 1

	Example 2:
	Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
	Output: 2

	Example 3:
	Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
	Output: 1

	Constraints:
	n == grid.length == grid[i].length
	2 <= n <= 100
	grid[i][j] is either 0 or 1.
	There are exactly two islands in grid.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{1, TestUtil.getArray("[[0,1],[1,0]]")},
				{2, TestUtil.getArray("[[0,1,0],[0,0,0],[0,0,1]]")},
				{1, TestUtil.getArray("[[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]")},
		});
	}

	int[][] DIRECTIONS = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

	public int shortestBridge(int[][] grid) {
		Queue<int[]> queue = new ArrayDeque<>();
		int row, col = 0;
		A:
		for (row = 0; row < grid.length; row++)
			for (col = 0; col < grid[0].length; col++)
				if (grid[row][col] == 1) break A;
		dfs(row, col, grid, queue);
		for (int step = 0; !queue.isEmpty(); step++)
			for (int size = queue.size(); size-- > 0; ) {
				int node[] = queue.poll();
				for (int[] direction : DIRECTIONS)
					if ((row = node[0] + direction[0]) < 0 || row >= grid.length || (col = node[1] + direction[1]) < 0 || col >= grid[0].length)
						continue;
					else if (grid[row][col] == 0) {
						queue.offer(new int[]{row, col});
						grid[row][col] = 2;
					} else if (grid[row][col] == 1) return step;
			}
		return -1;
	}

	void dfs(int row, int col, int[][] grid, Queue<int[]> island) {
		if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != 1) return;
		grid[row][col]++;
		island.offer(new int[]{row, col});
		for (int[] direction : DIRECTIONS) dfs(row + direction[0], col + direction[1], grid, island);
	}
}
