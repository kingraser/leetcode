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
		int dp[] = new int[4];
		for (int currentParity = nums[0] & 1, i = dp[2 | currentParity] = dp[currentParity] = 1, length = nums.length; i < length; dp[currentParity |= 2] = Math.max(dp[currentParity], dp[currentParity ^ 1] + 1))
			dp[currentParity = nums[i++] & 1]++;
		return Math.max(Math.max(dp[0], dp[1]), Math.max(dp[2], dp[3]));
	}
/*
Intuition

	Dynamic programming is the commonest solution for the questions in the category of sub-sequence.

Approach

	Let dp[length][state] represents the max length of the valid sub-sequence which generated from the original sub-string[0, length] with the state.
	state has 4 enumerations.
    00 for the parity of the sum of the first 2 nums is even, and the last num's parity is even.
    01 for the parity of the sum of the first 2 nums is even, and the last num's parity is odd.
    10 for the parity of the sum of the first 2 nums is odd, and the last num's parity is even.
    11 for the parity of the sum of the first 2 nums is odd, and the last num's parity is odd.

	In short, the first bit represents the parity of the sum of the first 2 nums.
	The second bit represents the parity of the last num.
	0 for even, 1 for odd.

	Let int[] current = dp[i], prev = dp[i - 1], then obviously we have.
	    If nums[i] is even
	        current[0b00] = prev[0b00] + 1
	        current[0b10] = Math.max(prev[0b11] + 1, prev[0b10])
	        current[0b01] = prev[0b01]
	        current[0b11] = prev[0b11]
	    Else if nums[i] is odd
	        current[0b01] = prev[0b01] + 1
	        current[0b11] = Math.max(prev[0b10] + 1, prev[0b11])
	        current[0b00] = prev[0b00]
	        current[0b10] = prev[0b10]

Complexity
    Time complexity:
    O(n)
    Space complexity:
    O(1)
    You can optimise the space to O(1).(actually 4 integers for the 4 enumerations of the state)
*/
}
