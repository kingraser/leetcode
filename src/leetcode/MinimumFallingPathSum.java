package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MinimumFallingPathSum {
	/*
	Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
	A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).

	Example 1:
	Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
	Output: 13
	Explanation: There are two falling paths with a minimum sum as shown.

	Example 2:
	Input: matrix = [[-19,57],[-40,-5]]
	Output: -59
	Explanation: The falling path with a minimum sum is shown.

	Constraints:
	n == matrix.length == matrix[i].length
	1 <= n <= 100
	-100 <= matrix[i][j] <= 100
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{13, TestUtil.getArray("[[2,1,3],[6,5,4],[7,8,9]]")},
				{-59, TestUtil.getArray("[[-19,57],[-40,-5]]")},
		});
	}

	public int minFallingPathSum(int[][] matrix) {
		for (int row = matrix.length - 2, colSize = matrix[0].length, endCol = colSize - 1; row >= 0; row--)
			for (int col = 0; col < colSize; col++) {
				int original = matrix[row][col];
				matrix[row][col] += matrix[row + 1][col];
				if (col > 0) matrix[row][col] = Math.min(matrix[row][col], original + matrix[row + 1][col - 1]);
				if (col < endCol) matrix[row][col] = Math.min(matrix[row][col], original + matrix[row + 1][col + 1]);
			}
		int result = matrix[0][0];
		for (int i : matrix[0]) result = Math.min(result, i);
		return result;
	}
}
