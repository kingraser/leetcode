package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Wit
 */
public class NearestExitFromEntranceInMaze {
	/*
	You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). You are also given the entrance of the maze, where entrance = [entrance_row, entrance_col] denotes the row and column of the cell you are initially standing at.
	In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.
	Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.

	Example 1:
	Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
	Output: 1
	Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
	Initially, you are at the entrance cell [1,2].
	- You can reach [1,0] by moving 2 steps left.
	- You can reach [0,2] by moving 1 step up.
	It is impossible to reach [2,3] from the entrance.
	Thus, the nearest exit is [0,2], which is 1 step away.

	Example 2:
	Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
	Output: 2
	Explanation: There is 1 exit in this maze at [1,2].
	[1,0] does not count as an exit since it is the entrance cell.
	Initially, you are at the entrance cell [1,0].
	- You can reach [1,2] by moving 2 steps right.
	Thus, the nearest exit is [1,2], which is 2 steps away.

	Example 3:
	Input: maze = [[".","+"]], entrance = [0,0]
	Output: -1
	Explanation: There are no exits in this maze.

	Constraints:
	maze.length == m
	maze[i].length == n
	1 <= m, n <= 100
	maze[i][j] is either '.' or '+'.
	entrance.length == 2
	0 <= entrance_row < m
	0 <= entrance_col < n
	entrance will always be an empty cell.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{1, TestUtil.getCharArray("[[\"+\",\"+\",\".\",\"+\"],[\".\",\".\",\".\",\"+\"],[\"+\",\"+\",\"+\",\".\"]]"), new int[]{1, 2}},
				{2, TestUtil.getCharArray("[[\"+\",\"+\",\"+\"],[\".\",\".\",\".\"],[\"+\",\"+\",\"+\"]]"), new int[]{1, 0}},
				{-1, TestUtil.getCharArray("[[\".\",\"+\"]]"), new int[2]},
		});
	}

	static final char WALL = '+';
	//since 2^7=128>100
	int shift = 7, max = (1 << shift) - 1, directions[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public int nearestExit(char[][] maze, int[] entrance) {
		int rowSize = maze.length, colSize = maze[0].length, entranceMask = getMask(entrance[0], entrance[1]);
		Queue<Integer> queue = new ArrayDeque<>(200);//since rowSize, colSize < 100
		addQueue(queue, entrance[0], entrance[1], maze);
		for (int round = 0; !queue.isEmpty(); round++)
			for (int size = queue.size(); size-- > 0; ) {
				int mask = queue.poll(), col = mask & max, row = mask >> shift, newRow, newCol;
				for (int[] direction : directions)
					if (((newRow = row + direction[0]) < 0 || newRow == rowSize || (newCol = col + direction[1]) < 0 || newCol == colSize)) {
						if (mask != entranceMask) return round;
					} else if (maze[newRow][newCol] != WALL) addQueue(queue, newRow, newCol, maze);
			}
		return -1;
	}

	int getMask(int row, int col) {return (row << 7) + col;}

	void addQueue(Queue<Integer> queue, int row, int col, char[][] maze) {
		queue.offer(getMask(row, col));
		maze[row][col] = WALL;
	}
}
