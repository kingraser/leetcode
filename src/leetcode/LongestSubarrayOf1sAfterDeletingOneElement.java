package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class LongestSubarrayOf1sAfterDeletingOneElement {
	/*
	Given a binary array nums, you should delete one element from it.
	Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.

	Example 1:
	Input: nums = [1,1,0,1]
	Output: 3
	Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.

	Example 2:
	Input: nums = [0,1,1,1,0,1,1,0,1]
	Output: 5
	Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].

	Example 3:
	Input: nums = [1,1,1]
	Output: 2
	Explanation: You must delete one element.

	Constraints:
	1 <= nums.length <= 10^5
	nums[i] is either 0 or 1.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, new int[]{1, 1, 0, 1}},
				{5, new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1}},
				{2, new int[]{1, 1, 1}},
		});
	}

	public int longestSubarray(int[] nums) {
		int result = 0, lastOnes = 0, zeros = 0, ones = 0;
		for (int num : nums)
			if (num == 0) {
				if (ones > 0) {
					result = Integer.max(result, (zeros == 1 ? lastOnes : 0) + ones);
					lastOnes = ones;
					ones = 0;
					zeros = 1;
				} else zeros++;
			} else ones++;
		if (ones > 0) result = Integer.max(result, (zeros == 1 ? lastOnes : 0) + ones);
		return ones == nums.length ? nums.length - 1 : result;
	}
}
