package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class LongestStrictlyIncreasingOrStrictlyDecreasingSubArray {
/*
You are given an array of integers nums. Return the length of the longest subarray of nums which is either strictly increasing or strictly decreasing

Example 1:
Input: nums = [1,4,3,3,2]
Output: 2
Explanation:
The strictly increasing subarrays of nums are [1], [2], [3], [3], [4], and [1,4].
The strictly decreasing subarrays of nums are [1], [2], [3], [3], [4], [3,2], and [4,3].
Hence, we return 2.

Example 2:
Input: nums = [3,3,3,3]
Output: 1
Explanation:
The strictly increasing subarrays of nums are [3], [3], [3], and [3].
The strictly decreasing subarrays of nums are [3], [3], [3], and [3].
Hence, we return 1.

Example 3:
Input: nums = [3,2,1]
Output: 3
Explanation:
The strictly increasing subarrays of nums are [3], [2], and [1].
The strictly decreasing subarrays of nums are [3], [2], [1], [3,2], [2,1], and [3,2,1].
Hence, we return 3.

Constraints:
    1 <= nums.length <= 50
    1 <= nums[i] <= 50
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, new int[]{1, 4, 3, 3, 2}},
				{1, new int[]{3, 3, 3, 3}},
				{3, new int[]{3, 2, 1}}});
	}

	public int longestMonotonicSubarray(int[] nums) {
		int max = 1, min = 1, currMax = 0, currMin = 0;
		for (int i = 1; i < nums.length; i++)
			if (nums[i] > nums[i - 1]) max = Math.max(max, (currMin = i) - currMax + 1);
			else if (nums[i] == nums[i - 1]) currMax = currMin = i;
			else min = Math.max(min, (currMax = i) - currMin + 1);
		return Math.max(max, min);
	}
}
