package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class JumpGameIII {
	/*
	Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
	Notice that you can not jump outside the array at any time.

	Example 1:
	Input: arr = [4,2,3,0,3,1,2], start = 5
	Output: true
	Explanation:
	All possible ways to reach at index 3 with value 0 are:
	index 5 -> index 4 -> index 1 -> index 3
	index 5 -> index 6 -> index 4 -> index 1 -> index 3

	Example 2:
	Input: arr = [4,2,3,0,3,1,2], start = 0
	Output: true
	Explanation:
	One possible way to reach at index 3 with value 0 is:
	index 0 -> index 4 -> index 1 -> index 3

	Example 3:
	Input: arr = [3,0,2,1,2], start = 2
	Output: false
	Explanation: There is no way to reach at index 1 with value 0.

	Constraints:
	1 <= arr.length <= 5 * 10^4
	0 <= arr[i] < arr.length
	0 <= start < arr.length
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{true, new int[]{0, 3, 0, 6, 3, 3, 4}, 6},
				{true, new int[]{4, 2, 3, 0, 3, 1, 2}, 5},
				{true, new int[]{4, 2, 3, 0, 3, 1, 2}, 0},
				{false, new int[]{3, 0, 2, 1, 2}, 2},
		});
	}

	public boolean canReach(int[] arr, int start) {
		return 0 <= start && start < arr.length && arr[start] >= 0 && ((arr[start] = -arr[start]) == 0 || canReach(arr, start + arr[start]) || canReach(arr, start - arr[start]));
	}

}
