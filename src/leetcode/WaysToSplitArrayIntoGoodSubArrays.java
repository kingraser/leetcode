package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class WaysToSplitArrayIntoGoodSubArrays {
	/*
	You are given a binary array nums.
	A subarray of an array is good if it contains exactly one element with the value 1.
	Return an integer denoting the number of ways to split the array nums into good sub-arrays. As the number may be too large, return it modulo 10^9 + 7.
	A subarray is a contiguous non-empty sequence of elements within an array.
	
	Example 1:
	Input: nums = [0,1,0,0,1]
	Output: 3
	Explanation: There are 3 ways to split nums into good sub-arrays:
	- [0,1] [0,0,1]
	- [0,1,0] [0,1]
	- [0,1,0,0] [1]
	
	Example 2:
	Input: nums = [0,1,0]
	Output: 1
	Explanation: There is 1 way to split nums into good sub-arrays:
	- [0,1,0]
	
	Constraints:
	1 <= nums.length <= 10^5
	0 <= nums[i] <= 1
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, new int[]{0, 1, 0, 0, 1}},
				{1, new int[]{0, 1, 0}},
		});
	}

	int module = 1000_000_007;

	public int numberOfGoodSubarraySplits(int[] nums) {
		long result = 1;
		int index = 0;
		while (index < nums.length && nums[index] == 0) index++;
		int prev1Index = index++;
		while (index < nums.length) {
			while (index < nums.length && nums[index] == 0) index++;
			if (index == nums.length) break;
			result = (result * (index - prev1Index)) % module;
			prev1Index = index++;
		}
		return prev1Index == nums.length ? 0 : (int) result;
	}
}
