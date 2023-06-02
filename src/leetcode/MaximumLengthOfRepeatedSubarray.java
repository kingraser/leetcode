package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MaximumLengthOfRepeatedSubarray {
	/*
	Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.

	Example 1:
	Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
	Output: 3
	Explanation: The repeated subarray with maximum length is [3,2,1].

	Example 2:
	Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
	Output: 5
	Explanation: The repeated subarray with maximum length is [0,0,0,0,0].

	Constraints:
	1 <= nums1.length, nums2.length <= 1000
	0 <= nums1[i], nums2[i] <= 100
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7}},
				{5, new int[5], new int[5]},
		});
	}

	public int findLength(int[] nums1, int[] nums2) {
		int ans = 0, dp[][] = new int[nums1.length + 1][nums2.length + 1];
		for (int i = 1; i <= nums1.length; i++)
			for (int j = 1; j <= nums2.length; j++)
				ans = Math.max(ans, dp[i][j] = nums1[i - 1] == nums2[j - 1] ? dp[i - 1][j - 1] + 1 : 0);
		return ans;
	}
}
