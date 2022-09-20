package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class LongestSubsequenceWithLimitedSum {
	/*
	You are given an integer array nums of length n, and an integer array queries of length m.
	Return an array answer of length m where answer[i] is the maximum size of a subsequence that you can take from nums such that the sum of its elements is less than or equal to queries[i].
	A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

	Example 1:
	Input: nums = [4,5,2,1], queries = [3,10,21]
	Output: [2,3,4]
	Explanation: We answer the queries as follows:
	- The subsequence [2,1] has a sum less than or equal to 3. It can be proven that 2 is the maximum size of such a subsequence, so answer[0] = 2.
	- The subsequence [4,5,1] has a sum less than or equal to 10. It can be proven that 3 is the maximum size of such a subsequence, so answer[1] = 3.
	- The subsequence [4,5,2,1] has a sum less than or equal to 21. It can be proven that 4 is the maximum size of such a subsequence, so answer[2] = 4.

	Example 2:
	Input: nums = [2,3,4,5], queries = [1]
	Output: [0]
	Explanation: The empty subsequence is the only subsequence that has a sum less than or equal to 1, so answer[0] = 0.

	Constraints:
	n == nums.length
	m == queries.length
	1 <= n, m <= 1000
	1 <= nums[i], queries[i] <= 106
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{2, 3, 4}, new int[]{4, 5, 2, 1}, new int[]{3, 10, 21}},
				{new int[]{0}, new int[]{2, 3, 4, 5}, new int[]{1}},
		});
	}

	public int[] answerQueries(int[] nums, int[] queries) {
		int[] result = new int[queries.length], sum = new int[nums.length];
		Arrays.sort(nums);
		sum[0] = nums[0];
		for (int i = 1; i < nums.length; i++) sum[i] = sum[i - 1] + nums[i];
		for (int i = 0, insertion; i < queries.length; i++)
			result[i] = (insertion = Arrays.binarySearch(sum, queries[i])) >= 0 ? insertion + 1 : -insertion - 1;
		return result;
	}
}
