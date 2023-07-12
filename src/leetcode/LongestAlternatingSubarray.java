package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class LongestAlternatingSubarray {
	/*
	You are given a 0-indexed integer array nums. A subarray s of length m is called alternating if:
	m is greater than 1.
	s1 = s0 + 1.
	The 0-indexed subarray s looks like [s0, s1, s0, s1,...,s(m-1) % 2]. In other words, s1 - s0 = 1, s2 - s1 = -1, s3 - s2 = 1, s4 - s3 = -1, and so on up to s[m - 1] - s[m - 2] = (-1)^m.
	Return the maximum length of all alternating sub-arrays present in nums or -1 if no such subarray exists.
	A subarray is a contiguous non-empty sequence of elements within an array.

	Example 1:
	Input: nums = [2,3,4,3,4]
	Output: 4
	Explanation: The alternating sub-arrays are [3,4], [3,4,3], and [3,4,3,4]. The longest of these is [3,4,3,4], which is of length 4.

	Example 2:
	Input: nums = [4,5,6]
	Output: 2
	Explanation: [4,5] and [5,6] are the only two alternating sub-arrays. They are both of length 2.

	Constraints:
	2 <= nums.length <= 100
	1 <= nums[i] <= 10^4
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{4, new int[]{2, 3, 4, 3, 4}},
				{2, new int[]{4, 5, 6}}
		});
	}

	public int alternatingSubarray(int[] nums) {
		int result = 0;
		for (int i = 0, j; i < nums.length; ) {
			for (j = i + 1; j < nums.length && nums[j] == nums[i] + ((j - i) & 1); ) j++;
			result = Math.max(result, j - i);
			i = (j < nums.length && nums[j] == nums[j - 1] + 1) ? j - 1 : j;
		}
		return result > 1 ? result : -1;
	}
}
