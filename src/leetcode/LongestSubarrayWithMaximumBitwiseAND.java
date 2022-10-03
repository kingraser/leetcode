package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class LongestSubarrayWithMaximumBitwiseAND {
	/*
	You are given an integer array nums of size n.
	Consider a non-empty subarray from nums that has the maximum possible bitwise AND.
	In other words, let k be the maximum value of the bitwise AND of any subarray of nums. Then, only subarrays with a bitwise AND equal to k should be considered.
	Return the length of the longest such subarray.
	The bitwise AND of an array is the bitwise AND of all the numbers in it.
	A subarray is a contiguous sequence of elements within an array.

	Example 1:
	Input: nums = [1,2,3,3,2,2]
	Output: 2
	Explanation:
	The maximum possible bitwise AND of a subarray is 3.
	The longest subarray with that value is [3,3], so we return 2.

	Example 2:
	Input: nums = [1,2,3,4]
	Output: 1
	Explanation:
	The maximum possible bitwise AND of a subarray is 4.
	The longest subarray with that value is [4], so we return 1.

	Constraints:
	1 <= nums.length <= 10^5
	1 <= nums[i] <= 10^6
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, new int[]{1, 2, 3, 3, 2, 2}},
				{1, new int[]{1, 2, 3, 4}}
		});
	}

	//Notice that the bitwise AND of two different numbers will always be strictly less than the maximum of those two numbers.
	//What does that tell us about the nature of the subarray that we should choose?
	public int longestSubarray(int[] nums) {
		int result = 1;
		for (int i = 1, max = nums[0], lastMaxIndex = 0, current = 1; i < nums.length; i++)
			if (nums[i] > max) {
				max = nums[lastMaxIndex = i];
				result = current = 1;
			} else if (nums[i] == max) {
				if (lastMaxIndex == i - 1) result = Integer.max(result, ++current);
				else current = 1;
				lastMaxIndex = i;
			}
		return result;
	}
}
