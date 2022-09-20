package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MaximumRowsCoveredByColumns {
	/*
	You are given a 0-indexed m x n binary matrix mat and an integer cols, which denotes the number of columns you must choose.
	A row is covered by a set of columns if each cell in the row that has a value of 1 also lies in one of the columns of the chosen set.
	Return the maximum number of rows that can be covered by a set of cols columns.

	Example 1:
	Input: mat = [[0,0,0],[1,0,1],[0,1,1],[0,0,1]], cols = 2
	Output: 3
	Explanation:
	As shown in the diagram above, one possible way of covering 3 rows is by selecting the 0th and 2nd columns.
	It can be shown that no more than 3 rows can be covered, so we return 3.

	Example 2:
	Input: mat = [[1],[0]], cols = 1
	Output: 2
	Explanation:
	Selecting the only column will result in both rows being covered, since the entire matrix is selected.
	Therefore, we return 2.

	Constraints:
	m == mat.length
	n == mat[i].length
	1 <= m, n <= 12
	mat[i][j] is either 0 or 1.
	1 <= cols <= n
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{6, TestUtil.getArray("[[1,0,1,1,0,0],[1,1,1,0,1,0],[0,0,1,1,1,1],[1,0,1,1,1,0],[1,0,0,1,0,1],[0,0,1,1,0,0],[0,1,0,1,0,0],[0,0,1,1,0,1]]"), 5},
				{3, TestUtil.getArray("[[0,0,0],[1,0,1],[0,1,1],[0,0,1]]"), 2},
				{2, TestUtil.getArray("[[1],[0]]"), 1},
		});
	}

	public int maximumRows(int[][] mat, int cols) {
		int result = 0, rowSize = mat.length, colSize = mat[0].length, rows[] = new int[rowSize];
		for (int row = 0; row < rowSize; row++) for (int bit : mat[row]) rows[row] = (rows[row] << 1) | bit;
		Mask mask = new Mask(cols, colSize);
		for (int rowsCovered = 0; mask.value != -1; mask.next(), rowsCovered = 0) {
			for (int row : rows) if ((row & mask.value) == row) rowsCovered++;
			result = Integer.max(result, rowsCovered);
		}
		return result;
	}

	public static class Mask {
		int count, value;

		public Mask(int count, int size) {value = ((1 << count) - 1) << (size - (this.count = count));}

		public int next() {
			for (int lowest1Bit, round = 0; round < count; value -= lowest1Bit)
				if ((lowest1Bit = Integer.lowestOneBit(value)) > (1 << round++)) return value -= lowest1Bit >> round;
			return value = -1;
		}
	}
}