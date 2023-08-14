package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MaxPairSumInAnArray {
	/*
	You are given a 0-indexed integer array nums. You have to find the maximum sum of a pair of numbers from nums such that the maximum digit in both numbers are equal.
	Return the maximum sum or -1 if no such pair exists.

	Example 1:
	Input: nums = [51,71,17,24,42]
	Output: 88
	Explanation:
	For i = 1 and j = 2, nums[i] and nums[j] have equal maximum digits with a pair sum of 71 + 17 = 88.
	For i = 3 and j = 4, nums[i] and nums[j] have equal maximum digits with a pair sum of 24 + 42 = 66.
	It can be shown that there are no other pairs with equal maximum digits, so the answer is 88.

	Example 2:
	Input: nums = [1,2,3,4]
	Output: -1
	Explanation: No pair exists in nums with equal maximum digits.

	Constraints:
	2 <= nums.length <= 100
	1 <= nums[i] <= 10^4
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{88, new int[]{51, 71, 17, 24, 42}},
				{-1, new int[]{1, 2, 3, 4}}
		});
	}

	public int maxSum(int[] nums) {
		int result = -1, digits[] = new int[10];
		for (int num : nums) {
			int maxDigit = getMaxDigit(num);
			if (digits[maxDigit] != 0) result = Math.max(result, digits[maxDigit] + num);
			digits[maxDigit] = Math.max(digits[maxDigit], num);
		}
		return result;
	}

	int getMaxDigit(int num) {
		int result = 0;
		for (; num != 0; num /= 10) result = Math.max(result, num % 10);
		return result;
	}
}
