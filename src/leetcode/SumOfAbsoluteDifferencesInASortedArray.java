package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SumOfAbsoluteDifferencesInASortedArray {
	/*
	You are given an integer array nums sorted in non-decreasing order.
	Build and return an integer array result with the same length as nums such that result[i] is equal to the summation of absolute differences between nums[i] and all the other elements in the array.
	In other words, result[i] is equal to sum(|nums[i]-nums[j]|) where 0 <= j < nums.length and j != i (0-indexed).

	Example 1:
	Input: nums = [2,3,5]
	Output: [4,3,5]
	Explanation: Assuming the arrays are 0-indexed, then
	result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4,
	result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3,
	result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5.

	Example 2:
	Input: nums = [1,4,6,8,10]
	Output: [24,15,13,15,21]

	Constraints:
	2 <= nums.length <= 10^5
	1 <= nums[i] <= nums[i + 1] <= 10^4
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{4, 3, 5}, new int[]{2, 3, 5}},
				{new int[]{24, 15, 13, 15, 21}, new int[]{1, 4, 6, 8, 10}}
		});
	}

	public int[] getSumAbsoluteDifferences(int[] nums) {
		int[] result = new int[nums.length], preSum = new int[nums.length];
		preSum[0] = nums[0];
		for (int i = 1; i < preSum.length; i++) preSum[i] = preSum[i - 1] + nums[i];
		result[0] = preSum[preSum.length - 1] - nums[0] * nums.length;
		for (int i = 1; i < result.length; i++)
			result[i] = (nums[i] * i - preSum[i - 1]) + (preSum[preSum.length - 1] - preSum[i] - nums[i] * (preSum.length - 1 - i));
		return result;
	}
}
