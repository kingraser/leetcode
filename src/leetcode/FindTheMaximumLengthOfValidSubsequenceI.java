package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class FindTheMaximumLengthOfValidSubsequenceI {
/*
You are given an integer array nums.
A subsequence sub of nums with length x is called valid if it satisfies:
    (sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2.
Return the length of the longest valid subsequence of nums.
A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

Example 1:
Input: nums = [1,2,3,4]
Output: 4
Explanation:
The longest valid subsequence is [1, 2, 3, 4].

Example 2:
Input: nums = [1,2,1,1,2,1,2]
Output: 6
Explanation:
The longest valid subsequence is [1, 2, 1, 2, 1, 2].

Example 3:
Input: nums = [1,3]
Output: 2
Explanation:
The longest valid subsequence is [1, 3].

Constraints:
    2 <= nums.length <= 2 * 10^5
    1 <= nums[i] <= 10^7
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{4, new int[]{1, 2, 3, 4}},
				{6, new int[]{1, 2, 1, 1, 2, 1, 2}},
				{2, new int[]{1, 3}},
		});
	}

	public int maximumLength(int[] nums) {
		// 00 for even and last even; 10 for even and last odd; 10 for odd and last even; 11 for odd and last odd
		int length = nums.length, dp[] = new int[4];
		for (int firstParity = nums[0] & 1, i = dp[2 | firstParity] = dp[firstParity] = 1; i < length; i++)
			if ((nums[i] & 1) == 0) {
				dp[0]++;
				dp[2] = Math.max(dp[3] + 1, dp[2]);
			} else {
				dp[1]++;
				dp[3] = Math.max(dp[3], dp[2] + 1);
			}
		return Math.max(Math.max(dp[0], dp[1]), Math.max(dp[2], dp[3]));
	}
}
