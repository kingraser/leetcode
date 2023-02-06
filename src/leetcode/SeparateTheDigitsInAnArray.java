package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SeparateTheDigitsInAnArray {
	/*
	Given an array of positive integers nums, return an array answer that consists of the digits of each integer in nums after separating them in the same order they appear in nums.
	To separate the digits of an integer is to get all the digits it has in the same order.
	For example, for the integer 10921, the separation of its digits is [1,0,9,2,1].

	Example 1:
	Input: nums = [13,25,83,77]
	Output: [1,3,2,5,8,3,7,7]
	Explanation:
	- The separation of 13 is [1,3].
	- The separation of 25 is [2,5].
	- The separation of 83 is [8,3].
	- The separation of 77 is [7,7].
	answer = [1,3,2,5,8,3,7,7]. Note that answer contains the separations in the same order.

	Example 2:
	Input: nums = [7,1,3,9]
	Output: [7,1,3,9]
	Explanation: The separation of each integer in nums is itself.
	answer = [7,1,3,9].

	Constraints:
	1 <= nums.length <= 1000
	1 <= nums[i] <= 10^5
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{1, 3, 2, 5, 8, 3, 7, 7}, new int[]{13, 25, 83, 77}},
				{new int[]{7, 1, 3, 9}, new int[]{7, 1, 3, 9}},
		});
	}

	public int[] separateDigits(int[] nums) {
		int idx = 0, result[] = new int[getLength(nums)];
		for (int num : nums)
			for (int base = (int) Math.pow(10, getLength(num) - 1); base != 0; num %= base, base /= 10)
				result[idx++] = num / base;
		return result;
	}


	int getLength(int[] nums) {
		int result = 0;
		for (int num : nums) result += getLength(num);
		return result;
	}

	int getLength(int n) {
		return n < 10 ? 1 : n < 100 ? 2 : n < 1000 ? 3 : n < 10_000 ? 4 : n < 100_000 ? 5 : 6;
	}
}
