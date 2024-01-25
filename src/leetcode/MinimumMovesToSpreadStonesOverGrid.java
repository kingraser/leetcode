package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MinimumMovesToSpreadStonesOverGrid {
	/*
	You are given a 0-indexed 2D integer matrix grid of size 3 * 3, representing the number of stones in each cell. The grid contains exactly 9 stones, and there can be multiple stones in a single cell.
	In one move, you can move a single stone from its current cell to any other cell if the two cells share a side.
	Return the minimum number of moves required to place one stone in each cell.

	Example 1:
	Input: grid = [[1,1,0],[1,1,1],[1,2,1]]
	Output: 3
	Explanation: One possible sequence of moves to place one stone in each cell is:
	1- Move one stone from cell (2,1) to cell (2,2).
	2- Move one stone from cell (2,2) to cell (1,2).
	3- Move one stone from cell (1,2) to cell (0,2).
	In total, it takes 3 moves to place one stone in each cell of the grid.
	It can be shown that 3 is the minimum number of moves required to place one stone in each cell.

	Example 2:
	Input: grid = [[1,3,0],[1,0,0],[1,0,3]]
	Output: 4
	Explanation: One possible sequence of moves to place one stone in each cell is:
	1- Move one stone from cell (0,1) to cell (0,2).
	2- Move one stone from cell (0,1) to cell (1,1).
	3- Move one stone from cell (2,2) to cell (1,2).
	4- Move one stone from cell (2,2) to cell (2,1).
	In total, it takes 4 moves to place one stone in each cell of the grid.
	It can be shown that 4 is the minimum number of moves required to place one stone in each cell.

	Constraints:
	grid.length == grid[i].length == 3
	0 <= grid[i][j] <= 9
	Sum of grid is equal to 9.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{8, TestUtil.getArray("[[4,0,0],[0,0,2],[3,0,0]]")},
				{7, TestUtil.getArray("[[3,2,0],[0,1,0],[0,3,0]]")},
				{3, TestUtil.getArray("[[1,1,0],[1,1,1],[1,2,1]]")},
				{4, TestUtil.getArray("[[1,3,0],[1,0,0],[1,0,3]]")},
		});
	}

	int minMoves, grid[][], distanceMap[][], rich[][], richCount, empty[][], emptyCount;

	public int minimumMoves(int[][] grid) {
		init(grid);
		for (int row = 0; row < 3; row++)
			for (int col = 0; col < 3; col++)
				if (grid[row][col] > 1) {
					rich[richCount][0] = row;
					rich[richCount++][1] = col;
				} else if (grid[row][col] == 0) {
					empty[emptyCount][0] = row;
					empty[emptyCount++][1] = col;
				}
		distanceMap = new int[emptyCount][richCount];
		for (int i = 0; i < emptyCount; i++)
			for (int j = 0; j < richCount; j++)
				distanceMap[i][j] = getDistance(empty[i], rich[j]);
		dfs(0, 0);
		return minMoves;
	}

	void init(int[][] grid) {
		this.grid = grid;
		minMoves = Integer.MAX_VALUE;
		rich = new int[4][2];
		empty = new int[8][2];
		richCount = emptyCount = 0;
	}

	int getDistance(int[] cell1, int[] cell2) {
		return Math.abs(cell1[0] - cell2[0]) + Math.abs(cell1[1] - cell2[1]);
	}

	void dfs(int start, int currentMove) {
		if (start == emptyCount) {
			minMoves = Math.min(minMoves, currentMove);
			return;
		}
		for (int i = 0; i < richCount; i++) {
			if (grid[rich[i][0]][rich[i][1]] < 2) continue;
			grid[rich[i][0]][rich[i][1]]--;
			dfs(start + 1, currentMove + distanceMap[start][i]);
			grid[rich[i][0]][rich[i][1]]++;
		}
	}
}
