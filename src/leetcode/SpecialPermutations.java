package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SpecialPermutations {
	/*
	You are given a 0-indexed integer array nums containing n distinct positive integers. A permutation of nums is called special if:
	For all indexes 0 <= i < n - 1, either nums[i] % nums[i+1] == 0 or nums[i+1] % nums[i] == 0.
	Return the total number of special permutations. As the answer could be large, return it modulo 109 + 7.

	Example 1:
	Input: nums = [2,3,6]
	Output: 2
	Explanation: [3,6,2] and [2,6,3] are the two special permutations of nums.

	Example 2:
	Input: nums = [1,4,3]
	Output: 2
	Explanation: [3,1,4] and [4,1,3] are the two special permutations of nums.

	Constraints:
	2 <= nums.length <= 14
	1 <= nums[i] <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, new int[]{2, 3, 6}},
				{2, new int[]{1, 4, 3}}
		});
	}

	int module = 1_000_000_007, length, nums[], memo[][];

	public int specialPerm(int[] nums) {
		memo = new int[length = (this.nums = nums).length][1 << length];
		return backtrack(0, 0);
	}

	private int backtrack(int preIndex, int mask) {
		if (mask == (1 << length) - 1) return 1;
		if (memo[preIndex][mask] != 0) return memo[preIndex][mask] - 1;
		int count = 0;
		for (int i = 0; i < length; i++)
			if ((mask & (1 << i)) == 0 && (mask == 0 || nums[i] % nums[preIndex] == 0 || nums[preIndex] % nums[i] == 0))
				count = (count + backtrack(i, mask | (1 << i))) % module;
		memo[preIndex][mask] = count + 1;
		return count;
	}
}
