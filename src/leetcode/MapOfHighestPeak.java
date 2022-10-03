package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Wit
 */
public class MapOfHighestPeak {
	/*
	You are given an integer matrix isWater of size m x n that represents a map of land and water cells.
	If isWater[i][j] == 0, cell (i, j) is a land cell.
	If isWater[i][j] == 1, cell (i, j) is a water cell.
	You must assign each cell a height in a way that follows these rules:
	The height of each cell must be non-negative.
	If the cell is a water cell, its height must be 0.
	Any two adjacent cells must have an absolute height difference of at most 1. A cell is adjacent to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).
	Find an assignment of heights such that the maximum height in the matrix is maximized.
	Return an integer matrix height of size m x n where height[i][j] is cell (i, j)'s height. If there are multiple solutions, return any of them.

	Example 1:
	Input: isWater = [[0,1],[0,0]]
	Output: [[1,0],[2,1]]
	Explanation: The image shows the assigned heights of each cell.
	The blue cell is the water cell, and the green cells are the land cells.

	Example 2:
	Input: isWater = [[0,0,1],[1,0,0],[0,0,0]]
	Output: [[1,1,0],[0,1,1],[1,2,2]]
	Explanation: A height of 2 is the maximum possible height of any assignment.
	Any height assignment that has a maximum height of 2 while still meeting the rules will also be accepted.

	Constraints:
	m == isWater.length
	n == isWater[i].length
	1 <= m, n <= 1000
	isWater[i][j] is 0 or 1.
	There is at least one water cell.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{TestUtil.getArray("[[1,0],[2,1]]"), TestUtil.getArray("[[0,1],[0,0]]")},
				{TestUtil.getArray("[[1,1,0],[0,1,1],[1,2,2]]"), TestUtil.getArray("[[0,0,1],[1,0,0],[0,0,0]]")},
		});
	}

	static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public int[][] highestPeak(int[][] isWater) {
		int rowSize = isWater.length, colSize = isWater[0].length, result[][] = new int[rowSize][colSize];
		Queue<int[]> queue = new ArrayDeque<>(rowSize * colSize);
		for (int row = 0; row < rowSize; row++)
			for (int col = 0; col < colSize; col++)
				if (isWater[row][col] == 1) queue.offer(new int[]{row, col});
				else result[row][col] = -1;
		while (!queue.isEmpty()) {
			int node[] = queue.poll(), row = node[0], col = node[1], nextRow, nextCol;
			for (int[] direction : DIRECTIONS)
				if ((nextRow = row + direction[0]) >= 0 && nextRow < rowSize && (nextCol = col + direction[1]) >= 0 && nextCol < colSize && result[nextRow][nextCol] == -1) {
					result[nextRow][nextCol] = result[row][col] + 1;
					queue.offer(new int[]{nextRow, nextCol});
				}
		}
		return result;
	}
}
