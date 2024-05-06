package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class ShortestSubArrayWithORatLeastKI {
/*
You are given an array nums of non-negative integers and an integer k.
An array is called special if the bitwise OR of all of its elements is at least k.
Return the length of the shortest special non-empty subarray of nums, or return -1 if no special subarray exists.

Example 1:
Input: nums = [1,2,3], k = 2
Output: 1
Explanation:
The subarray [3] has OR value of 3. Hence, we return 1.

Example 2:
Input: nums = [2,1,8], k = 10
Output: 3
Explanation:
The subarray [2,1,8] has OR value of 11. Hence, we return 3.

Example 3:
Input: nums = [1,2], k = 0
Output: 1
Explanation:
The subarray [1] has OR value of 1. Hence, we return 1.

Constraints:
    1 <= nums.length <= 50
    0 <= nums[i] <= 50
    0 <= k < 64
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[]{1, 3, 1}, new Object[][]{{new int[]{1, 2, 3}, 2}, {new int[]{2, 1, 8}, 10}, {new int[]{1, 2}, 0}});
	}

	public int minimumSubarrayLength(int[] nums, int k) {
		for (int size = 1; size <= nums.length; size++)
			for (int left = 0; left <= nums.length - size; left++)
				for (int right = left, val = 0; right - left < size; ) if ((val |= nums[right++]) >= k) return size;
		return -1;
	}
}
