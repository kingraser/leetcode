package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class AppendKIntegersWithMinimalSum {
	/*
	You are given an integer array nums and an integer k. Append k unique positive integers that do not appear in nums to nums such that the resulting total sum is minimum.
	Return the sum of the k integers appended to nums.

	Example 1:
	Input: nums = [1,4,25,10,25], k = 2
	Output: 5
	Explanation: The two unique positive integers that do not appear in nums which we append are 2 and 3.
	The resulting sum of nums is 1 + 4 + 25 + 10 + 25 + 2 + 3 = 70, which is the minimum.
	The sum of the two integers appended is 2 + 3 = 5, so we return 5.

	Example 2:
	Input: nums = [5,6], k = 6
	Output: 25
	Explanation: The six unique positive integers that do not appear in nums which we append are 1, 2, 3, 4, 7, and 8.
	The resulting sum of nums is 5 + 6 + 1 + 2 + 3 + 4 + 7 + 8 = 36, which is the minimum.
	The sum of the six integers appended is 1 + 2 + 3 + 4 + 7 + 8 = 25, so we return 25.

	Constraints:
	1 <= nums.length <= 10^5
	1 <= nums[i] <= 10^9
	1 <= k <= 10^8
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{5L, new int[]{1, 1}, 2},
				{794L, new int[]{96, 44, 99, 25, 61, 84, 88, 18, 19, 33, 60, 86, 52, 19, 32, 47, 35, 50, 94, 17, 29, 98, 22, 21, 72, 100, 40, 84}, 35},
				{5L, new int[]{1, 4, 25, 10, 25}, 2},
				{25L, new int[]{5, 6}, 6},
		});
	}

	public long minimalKSum(int[] nums, int k) {
		long result = 0, cursor = 1, count;
		Arrays.sort(nums);
		for (int i = 0; i < nums.length && k > 0; cursor = Math.max(cursor, nums[i++] + 1)) {
			if (nums[i] <= cursor) continue;
			k -= count = Math.min(k, nums[i] - cursor);
			result += ((cursor << 1) + count - 1) * count >> 1;
		}
		if (k > 0) result += ((cursor << 1) + k - 1) * k >> 1;
		return result;
	}
}
