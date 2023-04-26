package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SubarraySumsDivisibleByK {
	/*
	Given an integer array nums and an integer k, return the number of non-empty sub-arrays that have a sum divisible by k.
	A subarray is a contiguous part of an array.

	Example 1:
	Input: nums = [4,5,0,-2,-3,1], k = 5
	Output: 7
	Explanation: There are 7 sub-arrays with a sum divisible by k = 5:
	[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]

	Example 2:
	Input: nums = [5], k = 9
	Output: 0

	Constraints:
	1 <= nums.length <= 3 * 10^4
	-104 <= nums[i] <= 10^4
	2 <= k <= 10^4
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{7, new int[]{4, 5, 0, -2, -3, 1}, 5},
				{0, new int[]{5}, 9},
		});
	}

	public int subarraysDivByK(int[] nums, int k) {
		int result = 0, freq[] = new int[k], sum = 0, remainder;
		freq[0] = 1;
		for (int num : nums)
			result += freq[(remainder = (sum += num) % k) < 0 ? remainder + k : remainder]++;
		return result;
	}
}
