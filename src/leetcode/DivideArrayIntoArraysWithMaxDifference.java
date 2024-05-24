package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class DivideArrayIntoArraysWithMaxDifference {
/*
You are given an integer array nums of size n and a positive integer k.
Divide the array into one or more arrays of size 3 satisfying the following conditions:
    Each element of nums should be in exactly one array.
    The difference between any two elements in one array is less than or equal to k.
Return a 2D array containing all the arrays. If it is impossible to satisfy the conditions, return an empty array. And if there are multiple answers, return any of them.

Example 1:
Input: nums = [1,3,4,8,7,9,3,5,1], k = 2
Output: [[1,1,3],[3,4,5],[7,8,9]]
Explanation: We can divide the array into the following arrays: [1,1,3], [3,4,5] and [7,8,9].
The difference between any two elements in each array is less than or equal to 2.
Note that the order of elements is not important.

Example 2:
Input: nums = [1,3,3,2,7,3], k = 3
Output: []
Explanation: It is not possible to divide the array satisfying all the conditions.

Constraints:
    n == nums.length
    1 <= n <= 10^5
    n is a multiple of 3.
    1 <= nums[i] <= 10^5
    1 <= k <= 10^5
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{TestUtil.getArray("[[1,1,3],[3,4,5],[7,8,9]]"), new int[]{1, 3, 4, 8, 7, 9, 3, 5, 1}, 2},
				{new int[][]{}, new int[]{1, 3, 3, 2, 7, 3}, 3},
		});
	}

	public int[][] divideArray(int[] nums, int k) {
		if (nums.length % 3 != 0) return new int[][]{};
		int result[][] = new int[nums.length / 3][3];
		Arrays.sort(nums);
		for (int i = 0, j = 0, a[], aIndex; i < result.length; ) {
			for (a = result[i++], aIndex = 0; aIndex < 3; ) a[aIndex++] = nums[j++];
			if (a[2] - a[0] > k) return new int[][]{};
		}
		return result;
	}
}
