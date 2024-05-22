package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SpecialArrayI {
/*
An array is considered special if every pair of its adjacent elements contains two numbers with different parity.
You are given an array of integers nums. Return true if nums is a special array, otherwise, return false.

Example 1:
Input: nums = [1]
Output: true
Explanation:
There is only one element. So the answer is true.

Example 2:
Input: nums = [2,1,4]
Output: true
Explanation:
There is only two pairs: (2,1) and (1,4), and both of them contain numbers with different parity. So the answer is true.

Example 3:
Input: nums = [4,3,1,6]
Output: false
Explanation:
nums[1] and nums[2] are both odd. So the answer is false.

Constraints:
    1 <= nums.length <= 100
    1 <= nums[i] <= 100
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{true, new int[]{1}},
				{true, new int[]{2, 1, 4}},
				{false, new int[]{4, 3, 1, 6}},
		});
	}

	public boolean isArraySpecial(int[] nums) {
		for (int i = 0, last = nums.length - 1; i < last; )
			if (((nums[i] ^ nums[++i]) & 1) == 0) return false;
		return true;
	}
}
