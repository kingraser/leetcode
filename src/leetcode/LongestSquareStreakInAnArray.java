package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class LongestSquareStreakInAnArray {
	/*
	You are given an integer array nums. A subsequence of nums is called a square streak if:
	The length of the subsequence is at least 2, and
	after sorting the subsequence, each element (except the first element) is the square of the previous number.
	Return the length of the longest square streak in nums, or return -1 if there is no square streak.
	A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

	Example 1:
	Input: nums = [4,3,6,16,8,2]
	Output: 3
	Explanation: Choose the subsequence [4,16,2]. After sorting it, it becomes [2,4,16].
	- 4 = 2 * 2.
	- 16 = 4 * 4.
	Therefore, [4,16,2] is a square streak.
	It can be shown that every subsequence of length 4 is not a square streak.

	Example 2:
	Input: nums = [2,3,5,6,7]
	Output: -1
	Explanation: There is no square streak in nums so return -1.

	Constraints:
	2 <= nums.length <= 10^5
	2 <= nums[i] <= 10^5
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, new int[]{4, 3, 6, 16, 8, 2}},
				{-1, new int[]{2, 3, 5, 6, 7}},
		});
	}

	public int longestSquareStreak(int[] nums) {
		int res = 0, map[] = new int[100_001], root;
		Arrays.sort(nums);
		for (int num : nums)
			res = Math.max(res, map[num] = (root = (int) Math.sqrt(num)) * root == num ? map[root] + 1 : 1);
		return res < 2 ? -1 : res;
	}

	public int longestSquareStreakII(int[] nums) {
		boolean[] exists = new boolean[100_001];
		for (int num : nums) exists[num] = true;
		long result = 0;
		for (long i = 2, length, num; i < 317; result = Math.max(result, length))
			for (length = 0, num = i++; num < 100001 && exists[(int) num]; num = num * num) length++;
		return result >= 2 ? (int) result : -1;
	}


}
