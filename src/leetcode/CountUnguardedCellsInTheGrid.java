package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountUnguardedCellsInTheGrid {
	/*
	You are given two integers m and n representing a 0-indexed m x n grid. You are also given two 2D integer arrays guards and walls where guards[i] = [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the ith guard and jth wall respectively.
	A guard can see every cell in the four cardinal directions (north, east, south, or west) starting from their position unless obstructed by a wall or another guard. A cell is guarded if there is at least one guard that can see it.
	Return the number of unoccupied cells that are not guarded.

	Example 1:
	Input: m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
	Output: 7
	Explanation: The guarded and unguarded cells are shown in red and green respectively in the above diagram.
	There are a total of 7 unguarded cells, so we return 7.

	Example 2:
	Input: m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
	Output: 4
	Explanation: The unguarded cells are shown in green in the above diagram.
	There are a total of 4 unguarded cells, so we return 4.

	Constraints:
	1 <= m, n <= 10^5
	2 <= m * n <= 10^5
	1 <= guards.length, walls.length <= 5 * 10^4
	2 <= guards.length + walls.length <= m * n
	guards[i].length == walls[j].length == 2
	0 <= rowi, rowj < m
	0 <= coli, colj < n
	All the positions in guards and walls are unique.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, 5, 5, TestUtil.getArray("[[1,4],[4,1],[0,3]]"), TestUtil.getArray("[[3,2]]")},
				{7, 4, 6, TestUtil.getArray("[[0,0],[1,1],[2,3]]"), TestUtil.getArray("[[0,1],[2,2],[1,4]]")},
		});
	}

	// left 1 up 2 right 3 down 4
	int[][] DIRECTIONS = new int[][]{{}, {0, -1}, {-1, 0}, {0, 1}, {1, 0}};

	public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
		int result = m * n - guards.length - walls.length, grid[][] = new int[m][n], guardOrWallTag = 5;
		for (int[] wall : walls) grid[wall[0]][wall[1]] = guardOrWallTag;
		for (int[] guard : guards) {
			if (grid[guard[0]][guard[1]] != 0) result++;
			grid[guard[0]][guard[1]] = guardOrWallTag;
			for (int direction = 1; direction <= 4; direction++)
				for (int row = guard[0], col = guard[1], rowStep = DIRECTIONS[direction][0], colStep = DIRECTIONS[direction][1];
				     (row += rowStep) >= 0 && row < m && (col += colStep) >= 0 && col < n; )
					if (grid[row][col] == 0) {
						grid[row][col] = direction;
						result--;
					} else if (grid[row][col] >= guardOrWallTag || ((grid[row][col] ^ direction) & 1) == 0) break;
		}
		return result;
	}
}
