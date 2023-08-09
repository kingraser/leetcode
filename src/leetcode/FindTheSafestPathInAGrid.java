package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Wit
 */
public class FindTheSafestPathInAGrid {
	/*
	You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:
	A cell containing a thief if grid[r][c] = 1
	An empty cell if grid[r][c] = 0
	You are initially positioned at cell (0, 0). In one move, you can move to any adjacent cell in the grid, including cells containing thieves.
	The safeness factor of a path on the grid is defined as the minimum manhattan distance from any cell in the path to any thief in the grid.
	Return the maximum safeness factor of all paths leading to cell (n - 1, n - 1).
	An adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) and (r - 1, c) if it exists.
	The Manhattan distance between two cells (a, b) and (x, y) is equal to |a - x| + |b - y|, where |val| denotes the absolute value of val.

	Example 1:
	Input: grid = [[1,0,0],[0,0,0],[0,0,1]]
	Output: 0
	Explanation: All paths from (0, 0) to (n - 1, n - 1) go through the thieves in cells (0, 0) and (n - 1, n - 1).

	Example 2:
	Input: grid = [[0,0,1],[0,0,0],[0,0,0]]
	Output: 2
	Explanation: The path depicted in the picture above has a safeness factor of 2 since:
	- The closest cell of the path to the thief at cell (0, 2) is cell (0, 0). The distance between them is | 0 - 0 | + | 0 - 2 | = 2.
	It can be shown that there are no other paths with a higher safeness factor.

	Example 3:
	Input: grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
	Output: 2
	Explanation: The path depicted in the picture above has a safeness factor of 2 since:
	- The closest cell of the path to the thief at cell (0, 3) is cell (1, 2). The distance between them is | 0 - 1 | + | 3 - 2 | = 2.
	- The closest cell of the path to the thief at cell (3, 0) is cell (3, 2). The distance between them is | 3 - 3 | + | 0 - 2 | = 2.
	It can be shown that there are no other paths with a higher safeness factor.

	Constraints:
	1 <= grid.length == n <= 400
	grid[i].length == n
	grid[i][j] is either 0 or 1.
	There is at least one thief in the grid.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{0, TestUtil.transferToInteger("[[1,0,0],[0,0,0],[0,0,1]]")},
				{2, TestUtil.transferToInteger("[[0,0,1],[0,0,0],[0,0,0]]")},
				{2, TestUtil.transferToInteger("[[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]")},
		});
	}

	int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public int maximumSafenessFactor(List<List<Integer>> grid) {
		int size = grid.size(), map[][] = new int[size][size], distance = 2;
		Queue<int[]> queue = new ArrayDeque<>();
		for (int row = 0; row < size; row++) {
			List<Integer> list = grid.get(row);
			for (int col = 0; col < size; col++)
				if (list.get(col) == 1) {
					queue.add(new int[]{row, col});
					map[row][col]++;
				}
		}
		for (; !queue.isEmpty(); distance++) {
			for (int queueSize = queue.size(); queueSize-- > 0; ) {
				int poll[] = queue.poll(), row = poll[0], col = poll[1];
				for (int[] direction : directions) {
					int newRow = row + direction[0], newCol = col + direction[1];
					if (newRow < 0 || newRow >= size || newCol < 0 || newCol >= size || map[newRow][newCol] != 0)
						continue;
					map[newRow][newCol] = distance;
					queue.add(new int[]{newRow, newCol});
				}
			}
		}
		return dijkstra(map);
	}

	int dijkstra(int[][] map) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
		boolean[][] visited = new boolean[map.length][map.length];
		pq.offer(new int[]{map[0][0], 0, 0});
		visited[0][0] = true;
		while (!pq.isEmpty()) {
			int[] pos = pq.poll();
			int score = pos[0], row = pos[1], col = pos[2];
			if (row == map.length - 1 && col == map.length - 1) return score - 1;
			for (int[] dir : directions) {
				int newRow = row + dir[0], newCol = col + dir[1];
				if (newRow < 0 || newRow >= map.length || newCol < 0 || newCol >= map.length || visited[newRow][newCol]) continue;
				int newScore = Math.min(score, map[newRow][newCol]);
				pq.offer(new int[]{newScore, newRow, newCol});
				visited[newRow][newCol] = true;
			}
		}
		return -1;
	}

}
