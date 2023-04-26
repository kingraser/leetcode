package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class RowWithMaximumOnes {
	/*
	Given an m x n binary matrix `mat`, find the 0-indexed position of the row that contains the maximum count of ones, and the number of ones in that row.
	In case there are multiple rows that have the maximum count of ones, the row with the smallest row number should be selected.
	Return an array containing the index of the row, and the number of ones in it.

	Example 1:
	Input: mat = [[0,1],[1,0]]
	Output: [0,1]
	Explanation: Both rows have the same number of 1's. So we return the index of the smaller row, 0, and the maximum count of ones (1). So, the answer is [0,1].

	Example 2:
	Input: mat = [[0,0,0],[0,1,1]]
	Output: [1,2]
	Explanation: The row indexed 1 has the maximum count of ones (2). So we return its index, 1, and the count. So, the answer is [1,2].

	Example 3:
	Input: mat = [[0,0],[1,1],[0,0]]
	Output: [1,2]
	Explanation: The row indexed 1 has the maximum count of ones (2). So the answer is [1,2].

	Constraints:
	m == mat.length
	n == mat[i].length
	1 <= m, n <= 100
	mat[i][j] is either 0 or 1.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{0, 1}, TestUtil.getArray("[[0,1],[1,0]]")},
				{new int[]{1, 2}, TestUtil.getArray("[[0,0,0],[0,1,1]]")},
				{new int[]{1, 2}, TestUtil.getArray("[[0,0],[1,1],[0,0]]")},
		});
	}

	public int[] rowAndMaximumOnes(int[][] mat) {
		int[] result = new int[2];
		for (int row = 0; row < mat.length; row++) {
			int count = 0;
			for (int i : mat[row]) count += i;
			if (count > result[1]) {
				result[0] = row;
				result[1] = count;
			}
		}
		return result;
	}
}
