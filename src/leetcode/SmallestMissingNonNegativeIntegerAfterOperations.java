package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SmallestMissingNonNegativeIntegerAfterOperations {
/*
You are given a 0-indexed integer array nums and an integer value.
In one operation, you can add or subtract value from any element of nums.
For example, if nums = [1,2,3] and value = 2, you can choose to subtract value from nums[0] to make nums = [-1,2,3].
The MEX (minimum excluded) of an array is the smallest missing non-negative integer in it.
For example, the MEX of [-1,2,3] is 0 while the MEX of [1,0,3] is 2.
Return the maximum MEX of nums after applying the mentioned operation any number of times.

Example 1:
Input: nums = [1,-10,7,13,6,8], value = 5
Output: 4
Explanation: One can achieve this result by applying the following operations:
- Add value to nums[1] twice to make nums = [1,0,7,13,6,8]
- Subtract value from nums[2] once to make nums = [1,0,2,13,6,8]
- Subtract value from nums[3] twice to make nums = [1,0,2,3,6,8]
The MEX of nums is 4. It can be shown that 4 is the maximum MEX we can achieve.

Example 2:
Input: nums = [1,-10,7,13,6,8], value = 7
Output: 2
Explanation: One can achieve this result by applying the following operation:
- subtract value from nums[2] once to make nums = [1,-10,0,13,6,8]
The MEX of nums is 2. It can be shown that 2 is the maximum MEX we can achieve.

Constraints:
1 <= nums.length, value <= 10^5
-10^9 <= nums[i] <= 10^9
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{10, new int[]{3, 0, 3, 2, 4, 2, 1, 1, 0, 4}, 5},
				{4, new int[]{1, -10, 7, 13, 6, 8}, 5},
				{2, new int[]{1, -10, 7, 13, 6, 8}, 7},
		});
	}

	public int findSmallestInteger(int[] nums, int value) {
		int remainders[] = new int[value];
		for (int num : nums) remainders[((num % value) + value) % value]++;
		for (int result = 0; result < nums.length; result++)
			if (remainders[result % value]-- <= 0) return result;
		return nums.length;
	}
}
