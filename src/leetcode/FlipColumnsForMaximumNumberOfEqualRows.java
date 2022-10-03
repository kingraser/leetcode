package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class FlipColumnsForMaximumNumberOfEqualRows {
	/*
	You are given an m x n binary matrix.
	You can choose any number of columns in the matrix and flip every cell in that column (i.e., Change the value of the cell from 0 to 1 or vice versa).
	Return the maximum number of rows that have all values equal after some number of flips.

	Example 1:
	Input: matrix = [[0,1],[1,1]]
	Output: 1
	Explanation: After flipping no values, 1 row has all values equal.

	Example 2:
	Input: matrix = [[0,1],[1,0]]
	Output: 2
	Explanation: After flipping values in the first column, both rows have equal values.

	Example 3:
	Input: matrix = [[0,0,0],[0,0,1],[1,1,0]]
	Output: 2
	Explanation: After flipping values in the first two columns, the last two rows have equal values.

	Constraints:
	m == matrix.length
	n == matrix[i].length
	1 <= m, n <= 300
	matrix[i][j] is either 0 or 1.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{1, TestUtil.getArray("[[0,1],[1,1]]")},
				{2, TestUtil.getArray("[[0,1],[1,0]]")},
				{2, TestUtil.getArray("[[0,0,0],[0,0,1],[1,1,0]]")},
		});
	}

	//Assume the i-th row is an all-0s row after flipping x columns.
	//
	//If there are any other all-0s row, say j-th row, then the j-th row before flipping should be the same as the i-th row.
	//If there are any other all-1s row, say k-th row, then the k-th row before flipping should be totally different from the i-th row.
	//For example:
	//
	// [1,0,0,1,0]                                                       [0,0,0,0,0]  // all-0s
	// [1,0,0,1,0]  after flipping every cell in 0-th and 3-th columns   [0,0,0,0,0]  // all-0s
	// [1,0,1,1,1] ----------------------------------------------------> [0,0,1,0,1]
	// [0,1,1,0,1]                                                       [1,1,1,1,1]  // all-1s
	// [1,0,0,1,1]                                                       [0,0,0,0,1]
	//
	//1st, 2nd, and 4th rows have all values equal.
	//After flipping, the 1st and 2nd rows are all-0s, and the 4th row are all-1s. We can find that before flipping:
	//the 2nd row is the same as the 1st row.
	//the 4th row is totally different from the 1st row.
	//
	//So, the problem is transformed to: Find the i-th row, which has the most same or totally different rows in the matrix.
	public int maxEqualRowsAfterFlips(int[][] matrix) {
		int result = 0, rowSize = matrix.length, colSize = matrix[0].length, visited[] = new int[rowSize];
		for (int row = 0; row < rowSize; row++) {
			if (visited[row]++ > 0) continue;
			int count = 1, flip[] = new int[colSize];
			for (int col = 0; col < colSize; ) flip[col] = matrix[row][col++] ^ 1;
			for (int nextRow = row + 1; nextRow < rowSize; nextRow++)
				if (visited[nextRow] == 0 && (Arrays.equals(matrix[nextRow], matrix[row]) || Arrays.equals(matrix[nextRow], flip))) {
					visited[nextRow]++;
					count++;
				}
			result = Math.max(result, count);
		}
		return result;
	}
}
