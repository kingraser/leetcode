package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class FindTheWidthOfColumnsOfAGrid {
	/*
	You are given a 0-indexed m x n integer matrix grid. The width of a column is the maximum length of its integers.
	For example, if grid = [[-10], [3], [12]], the width of the only column is 3 since -10 is of length 3.
	Return an integer array ans of size n where ans[i] is the width of the ith column.
	The length of an integer x with len digits is equal to len if x is non-negative, and len + 1 otherwise.

	Example 1:
	Input: grid = [[1],[22],[333]]
	Output: [3]
	Explanation: In the 0th column, 333 is of length 3.

	Example 2:
	Input: grid = [[-15,1,3],[15,7,12],[5,6,-2]]
	Output: [3,1,2]
	Explanation:
	In the 0th column, only -15 is of length 3.
	In the 1st column, all integers are of length 1.
	In the 2nd column, both 12 and -2 are of length 2.

	Constraints:
	m == grid.length
	n == grid[i].length
	1 <= m, n <= 100
	-10^9 <= grid[r][c] <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{3}, TestUtil.getArray("[[1],[22],[333]]")},
				{new int[]{3, 1, 2}, TestUtil.getArray("[[-15,1,3],[15,7,12],[5,6,-2]]")},
		});
	}

	public int[] findColumnWidth(int[][] grid) {
		int[] result = new int[grid[0].length];
		for (int col = 0, row, min, max; col < result.length; result[col++] = Math.max(getLength(min), getLength(max)))
			for (row = 1, max = min = grid[0][col]; row < grid.length; max = Math.max(max, grid[row++][col]))
				min = Math.min(min, grid[row][col]);
		return result;
	}

	private int getLength(int n) {
		if (n < 0) return 1 + getLength(-n);
		int result = 1;
		while ((n /= 10) > 0) result++;
		return result;
	}
}
