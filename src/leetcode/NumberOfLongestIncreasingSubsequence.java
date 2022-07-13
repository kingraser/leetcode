package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class NumberOfLongestIncreasingSubsequence {
	/*
	Given an integer array nums, return the number of longest increasing subsequences.
	Notice that the sequence has to be strictly increasing.

	Example 1:
	Input: nums = [1,3,5,4,7]
	Output: 2
	Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].

	Example 2:
	Input: nums = [2,2,2,2,2]
	Output: 5
	Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, new int[]{1, 3, 5, 4, 7}},
				{5, new int[]{2, 2, 2, 2, 2}}
		});
	}

	public int findNumberOfLIS(int[] nums) {
		// 0 for longest length, 1 for the count of sub-sequence with the longest count
		int[] result = new int[]{1, 1}, dp[] = new int[nums.length][2];
		for (int[] array : dp) Arrays.fill(array, 1);
		for (int current = 1; current < nums.length; storeMax(dp[current][0], dp[current++][1], result))
			for (int before = 0; before < current; before++)
				if (nums[before] < nums[current]) storeMax(dp[before][0] + 1, dp[before][1], dp[current]);
		return result[1];
	}

	void storeMax(int currentVal, int currentCount, int[] max) {
		if (currentVal > max[0]) {
			max[0] = currentVal;
			max[1] = currentCount;
		} else if (currentVal == max[0]) max[1] += currentCount;
	}

}
