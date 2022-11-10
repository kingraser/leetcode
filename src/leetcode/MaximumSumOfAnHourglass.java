package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MaximumSumOfAnHourglass {
	/*
    You are given an m x n integer matrix grid.
    We define an hourglass as a part of the matrix with the following form:
    Return the maximum sum of the elements of an hourglass.
    Note that an hourglass cannot be rotated and must be entirely contained within the matrix.

    Example 1:
    Input: grid = [[6,2,1,3],[4,2,1,5],[9,2,8,7],[4,1,2,9]]
    Output: 30
    Explanation: The cells shown above represent the hourglass with the maximum sum: 6 + 2 + 1 + 2 + 9 + 2 + 8 = 30.

    Example 2:
    Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
    Output: 35
    Explanation: There is only one hourglass in the matrix, with the sum: 1 + 2 + 3 + 5 + 7 + 8 + 9 = 35.

    Constraints:
    m == grid.length
    n == grid[i].length
    3 <= m, n <= 150
    0 <= grid[i][j] <= 10^6
    */
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{30, TestUtil.getArray("[[6,2,1,3],[4,2,1,5],[9,2,8,7],[4,1,2,9]]")},
				{35, TestUtil.getArray("[[1,2,3],[4,5,6],[7,8,9]]")},
		});
	}

	public int maxSum(int[][] grid) {
		int result = 0;
		for (int row = 2, rowSize = grid.length, colSize = grid[0].length; row < rowSize; row++)
			for (int col = 2; col < colSize; col++)
				result = Integer.max(result, grid[row][col] + grid[row][col - 1] + grid[row][col - 2] + grid[row - 1][col - 1] + grid[row - 2][col] + grid[row - 2][col - 1] + grid[row - 2][col - 2]);
		return result;
	}
}
