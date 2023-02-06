package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MaximumCountOfPositiveIntegerAndNegativeInteger {
	/*
	Given an array nums sorted in non-decreasing order, return the maximum between the number of positive integers and the number of negative integers.
	In other words, if the number of positive integers in nums is pos and the number of negative integers is neg, then return the maximum of pos and neg.
	Note that 0 is neither positive nor negative.

	Example 1:
	Input: nums = [-2,-1,-1,1,2,3]
	Output: 3
	Explanation: There are 3 positive integers and 3 negative integers. The maximum count among them is 3.

	Example 2:
	Input: nums = [-3,-2,-1,0,0,1,2]
	Output: 3
	Explanation: There are 2 positive integers and 3 negative integers. The maximum count among them is 3.

	Example 3:
	Input: nums = [5,20,66,1314]
	Output: 4
	Explanation: There are 4 positive integers and 0 negative integers. The maximum count among them is 4.

	Constraints:
	1 <= nums.length <= 2000
	-2000 <= nums[i] <= 2000
	nums is sorted in a non-decreasing order.

	Follow up: Can you solve the problem in O(log(n)) time complexity?
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, new int[]{-2, -1, -1, 1, 2, 3}},
				{3, new int[]{-3, -2, -1, 0, 0, 1, 2}},
				{4, new int[]{5, 20, 66, 1314}},
		});
	}

	private static int binarySearch(int[] nums, int target) {
		int left = 0, right = nums.length;
		for (int mid; left < right; )
			if (nums[mid = (left + right) >> 1] < target) left = mid + 1;
			else right = mid;
		return left;
	}

	public int maximumCount(int[] nums) {
		return Math.max(binarySearch(nums, 0), nums.length - binarySearch(nums, 1));
	}
}
