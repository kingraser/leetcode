package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wit
 */
public class ReconstructA2RowBinaryMatrix {
	/*
	Given the following details of a matrix with n columns and 2 rows :
	The matrix is a binary matrix, which means each element in the matrix can be 0 or 1.
	The sum of elements of the 0-th(upper) row is given as upper.
	The sum of elements of the 1-st(lower) row is given as lower.
	The sum of elements in the i-th column(0-indexed) is col_sum[i], where col_sum is given as an integer array with length n.
	Your task is to reconstruct the matrix with upper, lower and col_sum.
	Return it as a 2-D integer array.
	If there are more than one valid solution, any of them will be accepted.
	If no valid solution exists, return an empty 2-D array.

	Example 1:
	Input: upper = 2, lower = 1, col_sum = [1,1,1]
	Output: [[1,1,0],[0,0,1]]
	Explanation: [[1,0,1],[0,1,0]], and [[0,1,1],[1,0,0]] are also correct answers.

	Example 2:
	Input: upper = 2, lower = 3, col_sum = [2,2,1,1]
	Output: []

	Example 3:
	Input: upper = 5, lower = 5, col_sum = [2,1,2,0,1,0,1,2,0,1]
	Output: [[1,1,1,0,1,0,0,1,0,0],[1,0,1,0,0,0,1,1,0,1]]

	Constraints:
	1 <= col_sum.length <= 10^5
	0 <= upper, lower <= col_sum.length
	0 <= col_sum[i] <= 2
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{TestUtil.transferToInteger("[[1,1,0],[0,0,1]]"), 2, 1, new int[]{1, 1, 1}},
				{new ArrayList<>(), 2, 3, new int[]{2, 2, 1, 1}},
				{TestUtil.transferToInteger("[[1,1,1,0,0,0,1,1,0,0],[1,0,1,0,1,0,0,1,0,1]]"), 5, 5, new int[]{2, 1, 2, 0, 1, 0, 1, 2, 0, 1}},
		});
	}

	public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colSum) {
		int[][] result = new int[2][colSum.length];
		for (int i = 0; i < colSum.length; upper -= result[0][i], lower -= result[1][i++])
			if (colSum[i] == 1) (lower > upper ? result[1] : result[0])[i] = 1;
			else if (colSum[i] == 2) result[0][i] = result[1][i] = 1;
		if (upper != 0 || lower != 0) return new ArrayList<>();
		return Arrays.stream(result).map(a -> Arrays.stream(a).boxed().collect(Collectors.toList())).collect(Collectors.toList());
	}
}
