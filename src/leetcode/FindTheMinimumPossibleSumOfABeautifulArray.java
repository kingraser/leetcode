package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class FindTheMinimumPossibleSumOfABeautifulArray {
	/*
	You are given positive integers n and target.
	An array nums is beautiful if it meets the following conditions:
	nums.length == n.
	nums consists of pairwise distinct positive integers.
	There doesn't exist two distinct indices, i and j, in the range [0, n - 1], such that nums[i] + nums[j] == target.
	Return the minimum possible sum that a beautiful array could have.

	Example 1:
	Input: n = 2, target = 3
	Output: 4
	Explanation: We can see that nums = [1,3] is beautiful.
	- The array nums has length n = 2.
	- The array nums consists of pairwise distinct positive integers.
	- There doesn't exist two distinct indices, i and j, with nums[i] + nums[j] == 3.
	It can be proven that 4 is the minimum possible sum that a beautiful array could have.

	Example 2:
	Input: n = 3, target = 3
	Output: 8
	Explanation: We can see that nums = [1,3,4] is beautiful.
	- The array nums has length n = 3.
	- The array nums consists of pairwise distinct positive integers.
	- There doesn't exist two distinct indices, i and j, with nums[i] + nums[j] == 3.
	It can be proven that 8 is the minimum possible sum that a beautiful array could have.

	Example 3:
	Input: n = 1, target = 1
	Output: 1
	Explanation: We can see, that nums = [1] is beautiful.

	Constraints:
	1 <= n <= 10^5
	1 <= target <= 10^5
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{4L, 2, 3},
				{8L, 3, 3},
				{1L, 1, 1}
		});
	}

	public long minimumPossibleSum(int n, int target) {
		int half = target >> 1;
		return n <= half ? sum(1, n) : sum(1, half) + sum(target, n - half);
	}
	long sum(int start, int count) {return (((long) start << 1) + count - 1) * count >> 1;}
}
