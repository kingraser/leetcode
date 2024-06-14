package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNullElse;

/**
 * @author Wit
 */
public class FindTheMaximumLengthOfAGoodSubsequenceI {
/*
You are given an integer array nums and a non-negative integer k. A sequence of integers seq is called good if there are at most k indices i in the range [0, seq.length - 2] such that seq[i] != seq[i + 1].
Return the maximum possible length of a good
subsequence
of nums.

Example 1:
Input: nums = [1,2,1,1,3], k = 2
Output: 4
Explanation:
The maximum length subsequence is [1,2,1,1].

Example 2:
Input: nums = [1,2,3,4,5,1], k = 0
Output: 2
Explanation:
The maximum length subsequence is [1,1].

Constraints:
    1 <= nums.length <= 500
    1 <= nums[i] <= 10^9
    0 <= k <= min(nums.length, 25)
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{4, new int[]{1, 2, 1, 1, 3}, 2},
				{2, new int[]{1, 2, 3, 4, 5, 1}, 0},
		});
	}

	/*
	result[i] is the longest good subsequence with i different neighbours.
	dp[i][num] means the longest good subsequence with i different neighbours, and ending with num.
	dp[i][num] = max(dp[i][num] + 1, result[i - 1] + 1).
	*/
	@SuppressWarnings("unchecked")
	public int maximumLength(int[] nums, int k) {
		Map<Integer, Integer>[] dp = new HashMap[k + 1];
		for (int i = 0; i < dp.length; i++) dp[i] = new HashMap<>();
		int[] result = new int[k + 1];
		for (int num : nums) {
			for (int[] i = new int[]{k}; i[0] > 0; )
				result[i[0]] = Math.max(result[i[0]], dp[i[0]].compute(num, (key, v) -> 1 + Math.max(result[--i[0]], requireNonNullElse(v, 0))));
			result[0] = Math.max(result[0], dp[0].merge(num, 1, Integer::sum));
		}
		return result[k];
	}
}
