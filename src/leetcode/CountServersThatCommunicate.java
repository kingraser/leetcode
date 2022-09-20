package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountServersThatCommunicate {
	/*
	You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that on that cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the same row or on the same column.
	Return the number of servers that communicate with any other server.

	Example 1:
	Input: grid = [[1,0],[0,1]]
	Output: 0
	Explanation: No servers can communicate with others.

	Example 2:
	Input: grid = [[1,0],[1,1]]
	Output: 3
	Explanation: All three servers can communicate with at least one other server.

	Example 3:
	Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
	Output: 4
	Explanation: The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other. The server at right bottom corner can't communicate with any other server.

	Constraints:
	m == grid.length
	n == grid[i].length
	1 <= m <= 250
	1 <= n <= 250
	grid[i][j] == 0 or 1
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{0, TestUtil.getArray("[[1,0],[0,1]]")},
				{3, TestUtil.getArray("[[1,0],[1,1]]")},
				{4, TestUtil.getArray("[[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]")},
		});
	}

	public int countServers(int[][] grid) {
		int result = 0, rowCount = grid.length, colCount = grid[0].length, rowServers[] = new int[rowCount], colServers[] = new int[colCount];
		for (int row = 0; row < rowCount; row++)
			for (int col = 0; col < colCount; col++)
				if (grid[row][col] == 1) {
					rowServers[row]++;
					colServers[col]++;
				}
		for (int row = 0; row < rowCount; row++)
			for (int col = 0; col < colCount; col++)
				if (grid[row][col] == 1 && rowServers[row] + colServers[col] > 2) result++;
		return result;
	}
}
