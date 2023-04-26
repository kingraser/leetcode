package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Wit
 */
public class RottingOranges {
	/*
	You are given an m x n grid where each cell can have one of three values:
	0 representing an empty cell,
	1 representing a fresh orange, or
	2 representing a rotten orange.
	Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
	Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

	Example 1:
	Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
	Output: 4

	Example 2:
	Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
	Output: -1
	Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

	Example 3:
	Input: grid = [[0,2]]
	Output: 0
	Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.

	Constraints:
	m == grid.length
	n == grid[i].length
	1 <= m, n <= 10
	grid[i][j] is 0, 1, or 2.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{4, TestUtil.getArray("[[2,1,1],[1,1,0],[0,1,1]]")},
				{-1, TestUtil.getArray("[[2,1,1],[0,1,1],[1,0,1]]")},
				{0, TestUtil.getArray("[[0,2]]")},
		});
	}

	int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public int orangesRotting(int[][] grid) {
		int freshCount = 0, time = 0, rowSize = grid.length, colSize = grid[0].length;
		Queue<Integer> rottens = new ArrayDeque<>(100);
		for (int row = 0; row < rowSize; row++)
			for (int col = 0; col < colSize; col++)
				if (grid[row][col] == 2) rottens.offer((row << 4) | col);
				else if (grid[row][col] == 1) freshCount++;
		for (; freshCount > 0 && !rottens.isEmpty(); time++)
			for (int size = rottens.size(); freshCount > 0 && size-- > 0; )
				for (int i = 0, position = rottens.poll(), col = position & 15, row = position >> 4, newRow, newCol; i < directions.length; i++)
					if ((newRow = row + directions[i][0]) >= 0 && newRow < rowSize && (newCol = col + directions[i][1]) >= 0 && newCol < colSize && grid[newRow][newCol] == 1) {
						grid[newRow][newCol] = 2;
						freshCount--;
						rottens.offer((newRow << 4) | newCol);
					}
		return freshCount == 0 ? time : -1;
	}
}
