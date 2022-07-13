package leetcode;

import leetcode.common.ListNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SpiralMatrixIV {
	/*
	You are given two integers m and n, which represent the dimensions of a matrix.
	You are also given the head of a linked list of integers.
	Generate an m x n matrix that contains the integers in the linked list presented in spiral order (clockwise), starting from the top-left of the matrix. If there are remaining empty spaces, fill them with -1.
	Return the generated matrix.

	Example 1:
	Input: m = 3, n = 5, head = [3,0,2,6,8,1,7,9,4,2,5,5,0]
	Output: [[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]
	Explanation: The diagram above shows how the values are printed in the matrix.
	Note that the remaining spaces in the matrix are filled with -1.

	Example 2:
	Input: m = 1, n = 4, head = [0,1,2]
	Output: [[0,1,2,-1]]
	Explanation: The diagram above shows how the values are printed from left to right in the matrix.
	The last space in the matrix is set to -1.

	Constraints:
	1 <= m, n <= 10^5
	1 <= m * n <= 10^5
	The number of nodes in the list is in the range [1, m * n].
	0 <= Node.val <= 1000
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{TestUtil.getArray("[[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]"), 3, 5, ListNode.list(3, 0, 2, 6, 8, 1, 7, 9, 4, 2, 5, 5, 0)},
				{TestUtil.getArray("[[0,1,2,-1]]"), 1, 4, ListNode.list(0, 1, 2)}
		});
	}

	public int[][] spiralMatrix(int m, int n, ListNode head) {
		int[][] result = new int[m][n];
		for (int top = 0, bottom = m, left = 0, right = n; top < bottom && left < right; left++) {
			for (int i = left; i < right; i++)
				if (head != null) {
					result[top][i] = head.val;
					head = head.next;
				} else result[top][i] = -1;
			if (++top == bottom) break;
			for (int i = top; i < bottom; i++)
				if (head != null) {
					result[i][right - 1] = head.val;
					head = head.next;
				} else result[i][right - 1] = -1;
			if (--right == left) break;
			for (int i = right - 1; i >= left; i--)
				if (head != null) {
					result[bottom - 1][i] = head.val;
					head = head.next;
				} else result[bottom - 1][i] = -1;
			if (--bottom == top) break;
			for (int i = bottom - 1; i >= top; i--)
				if (head != null) {
					result[i][left] = head.val;
					head = head.next;
				} else result[i][left] = -1;
		}
		return result;
	}
}
