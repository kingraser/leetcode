package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class CountTheNumberOfFairPairs {
	/*
	Given a 0-indexed integer array nums of size n and two integers lower and upper, return the number of fair pairs.
	A pair (i, j) is fair if:
	0 <= i < j < n, and
	lower <= nums[i] + nums[j] <= upper

	Example 1:
	Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
	Output: 6
	Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).

	Example 2:
	Input: nums = [1,7,9,2,5], lower = 11, upper = 11
	Output: 1
	Explanation: There is a single fair pair: (2,3).

	Constraints:
	1 <= nums.length <= 10^5
	nums.length == n
	-10^9 <= nums[i] <= 10^9
	-10^9 <= lower <= upper <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{6L, new int[]{0, 1, 7, 4, 4, 5}, 3, 6},
				{1L, new int[]{1, 7, 9, 2, 5}, 11, 11}
		});
	}

	public long countFairPairs(int[] nums, int lower, int upper) {
		Arrays.sort(nums);
		return smaller(nums, upper) - smaller(nums, --lower);
	}

	private long smaller(int[] nums, int value) {
		long result = 0;
		for (int l = 0, r = nums.length - 1; l < r; )
			if (nums[l] + nums[r] <= value) result += r - l++;
			else r--;
		return result;
	}
}
