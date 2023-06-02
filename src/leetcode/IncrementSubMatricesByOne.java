package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class IncrementSubMatricesByOne {
/*
You are given a positive integer n, indicating that we initially have an n x n 0-indexed integer matrix mat filled with zeroes.
You are also given a 2D integer array query. For each query[i] = [row1_i, col1i, row2i, col2i], you should do the following operation:
Add 1 to every element in the sub_matrix with the top left corner (row1_i, col1_i) and the bottom right corner (row2i, col2i). That is, add 1 to mat[x][y] for all row1_i <= x <= row2i and col1i <= y <= col2i.
Return the matrix mat after performing every query.

Example 1:
Input: n = 3, queries = [[1,1,2,2],[0,0,1,1]]
Output: [[1,1,0],[1,2,1],[0,1,1]]
Explanation: The diagram above shows the initial matrix, the matrix after the first query, and the matrix after the second query.
- In the first query, we add 1 to every element in the sub_matrix with the top left corner (1, 1) and bottom right corner (2, 2).
- In the second query, we add 1 to every element in the sub_matrix with the top left corner (0, 0) and bottom right corner (1, 1).

Example 2:
Input: n = 2, queries = [[0,0,1,1]]
Output: [[1,1],[1,1]]
Explanation: The diagram above shows the initial matrix and the matrix after the first query.
- In the first query we add 1 to every element in the matrix.

Constraints:
1 <= n <= 500
1 <= queries.length <= 10^4
0 <= row1_i <= row2i < n
0 <= col1i <= col2i < n
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{TestUtil.getArray("[[1,1,0],[1,2,1],[0,1,1]]"), 3, TestUtil.getArray("[[1,1,2,2],[0,0,1,1]]")},
				{TestUtil.getArray("[[1,1],[1,1]]"), 2, TestUtil.getArray("[[0,0,1,1]]")},
		});
	}

	public int[][] rangeAddQueries(int n, int[][] queries) {
		int[][] result = new int[n][n];
		for (int[] query : queries) {
			int r1 = query[0], c1 = query[1], r2 = query[2], c2 = query[3];
			result[r1][c1]++;
			if (r2 + 1 < n) {
				result[r2 + 1][c1]--;
				if (c2 + 1 < n) {
					result[r1][c2 + 1]--;
					result[r2 + 1][c2 + 1]++;
				}
			} else if (c2 + 1 < n) result[r1][c2 + 1]--;
		}
		for (int row = 1; row < n; row++)
			for (int col = 0; col < n; col++)
				result[row][col] += result[row - 1][col];
		for (int row = 0; row < n; row++)
			for (int col = 1; col < n; col++)
				result[row][col] += result[row][col - 1];
		return result;
	}
}
