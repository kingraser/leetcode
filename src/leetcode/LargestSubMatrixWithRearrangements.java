package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class LargestSubMatrixWithRearrangements {
/*
You are given a binary matrix `matrix` of size m x n, and you are allowed to rearrange the columns of the matrix in any order.
Return the area of the largest sub-matrix within matrix where every element of the sub-matrix is 1 after reordering the columns optimally.

Example 1:
Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
Output: 4
Explanation: You can rearrange the columns as shown above.
The largest sub-matrix of 1s, in bold, has an area of 4.

Example 2:
Input: matrix = [[1,0,1,0,1]]
Output: 3
Explanation: You can rearrange the columns as shown above.
The largest sub-matrix of 1s, in bold, has an area of 3.

Example 3:
Input: matrix = [[1,1,0],[1,0,1]]
Output: 2
Explanation: Notice that you must rearrange entire columns, and there is no way to make a sub-matrix of 1s larger than an area of 2.

Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= m * n <= 10^5
    matrix[i][j] is either 0 or 1.
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{4, TestUtil.getArray("[[0,0,1],[1,1,1],[1,0,1]]")},
				{3, TestUtil.getArray("[[1,0,1,0,1]]")},
				{2, TestUtil.getArray("[[1,1,0],[1,0,1]]")},
		});
	}

	public int largestSubmatrix(int[][] matrix) {
		int result = 0, rowSize = matrix.length, colSize = matrix[0].length;
		for (int row = 1; row < rowSize; row++)
			for (int col = 0; col < colSize; col++)
				if (matrix[row][col] == 1) matrix[row][col] = matrix[row - 1][col] + 1;
		for (int[] row : matrix) {
			Arrays.sort(row);
			for (int col = 1; col <= colSize; col++)
				result = Math.max(result, col * row[colSize - col]);
		}
		return result;
	}
}
