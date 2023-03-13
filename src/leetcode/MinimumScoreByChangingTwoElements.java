package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class MinimumScoreByChangingTwoElements {
	/*
	You are given a 0-indexed integer array nums.

	The low score of nums is the minimum value of |nums[i] - nums[j]| over all 0 <= i < j < nums.length.
	The high score of nums is the maximum value of |nums[i] - nums[j]| over all 0 <= i < j < nums.length.
	The score of nums is the sum of the high and low scores of nums.
	To minimize the score of nums, we can change the value of at most two elements of nums.

	Return the minimum possible score after changing the value of at most two elements of nums.
	Note that |x| denotes the absolute value of x.

	Example 1:
	Input: nums = [1,4,3]
	Output: 0
	Explanation: Change value of nums[1] and nums[2] to 1 so that nums becomes [1,1,1]. Now, the value of |nums[i] - nums[j]| is always equal to 0, so we return 0 + 0 = 0.

	Example 2:
	Input: nums = [1,4,7,8,5]
	Output: 3
	Explanation: Change nums[0] and nums[1] to be 6. Now nums becomes [6,6,7,8,5].
	Our low score is achieved when i = 0 and j = 1, in which case |nums[i] - nums[j]| = |6 - 6| = 0.
	Our high score is achieved when i = 3 and j = 4, in which case |nums[i] - nums[j]| = |8 - 5| = 3.
	The sum of our high and low score is 3, which we can prove to be minimal.

	Constraints:
	3 <= nums.length <= 10^5
	1 <= nums[i] <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{0, new int[]{1, 4, 3}},
				{3, new int[]{1, 4, 7, 8, 5}}
		});
	}

	public int minimizeSum(int[] nums) {
		Arrays.sort(nums);
		int case1 = nums[nums.length - 1] - nums[2];
		int case2 = nums[nums.length - 3] - nums[0];
		int case3 = nums[nums.length - 2] - nums[1];
		return Integer.min(case1, Integer.min(case2, case3));
	}

	public int minimizeSumII(int[] nums) {
		int min0 = Integer.MAX_VALUE, min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE, max2 = Integer.MIN_VALUE, max1 = Integer.MIN_VALUE, max0 = Integer.MIN_VALUE;
		for (int num : nums) {
			if (num < min2)
				if (num < min1) {
					min2 = min1;
					if (num < min0) {
						min1 = min0;
						min0 = num;
					} else min1 = num;
				} else min2 = num;
			if (num > max2)
				if (num > max1) {
					max2 = max1;
					if (num > max0) {
						max1 = max0;
						max0 = num;
					} else max1 = num;
				} else max2 = num;
		}
		return Math.min(max0 - min2, Math.min(max1 - min1, max2 - min0));
	}
}
