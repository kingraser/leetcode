package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SumOfSubarrayRanges {
	/*
	You are given an integer array nums. The range of a subarray of nums is the difference between the largest and smallest element in the subarray.
	Return the sum of all subarray ranges of nums.
	A subarray is a contiguous non-empty sequence of elements within an array.

	Example 1:
	Input: nums = [1,2,3]
	Output: 4
	Explanation: The 6 sub-arrays of nums are the following:
	[1], range = largest - smallest = 1 - 1 = 0
	[2], range = 2 - 2 = 0
	[3], range = 3 - 3 = 0
	[1,2], range = 2 - 1 = 1
	[2,3], range = 3 - 2 = 1
	[1,2,3], range = 3 - 1 = 2
	So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.

	Example 2:
	Input: nums = [1,3,3]
	Output: 4
	Explanation: The 6 sub-arrays of nums are the following:
	[1], range = largest - smallest = 1 - 1 = 0
	[3], range = 3 - 3 = 0
	[3], range = 3 - 3 = 0
	[1,3], range = 3 - 1 = 2
	[3,3], range = 3 - 3 = 0
	[1,3,3], range = 3 - 1 = 2
	So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.

	Example 3:
	Input: nums = [4,-2,-3,4,1]
	Output: 59
	Explanation: The sum of all subarray ranges of nums is 59.

	Constraints:
	1 <= nums.length <= 1000
	-10^9 <= nums[i] <= 10^9

	Follow-up: Could you find a solution with O(n) time complexity?
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{4L, new int[]{1, 2, 3}},
				{4L, new int[]{1, 3, 3}},
				{59L, new int[]{4, -2, -3, 4, 1}},
		});
	}

	public long subArrayRanges(int[] nums) {
		long sum = 0;
		int[] leftBoundMin = new int[nums.length], rightBoundMin = new int[nums.length], leftBoundMax = new int[nums.length], rightBoundMax = new int[nums.length];
		leftBoundMax[0] = leftBoundMin[0] = -1;
		for (int i = 1; i < nums.length; i++) {
			leftBoundMax[i] = leftBoundMin[i] = i - 1;
			while (leftBoundMin[i] >= 0 && nums[leftBoundMin[i]] >= nums[i])
				leftBoundMin[i] = leftBoundMin[leftBoundMin[i]];
			while (leftBoundMax[i] >= 0 && nums[leftBoundMax[i]] <= nums[i])
				leftBoundMax[i] = leftBoundMax[leftBoundMax[i]];
		}
		rightBoundMax[nums.length - 1] = rightBoundMin[nums.length - 1] = nums.length;
		for (int i = nums.length - 2; i >= 0; i--) {
			rightBoundMax[i] = rightBoundMin[i] = i + 1;
			while (rightBoundMin[i] < nums.length && nums[rightBoundMin[i]] > nums[i])
				rightBoundMin[i] = rightBoundMin[rightBoundMin[i]];
			while (rightBoundMax[i] < nums.length && nums[rightBoundMax[i]] < nums[i])
				rightBoundMax[i] = rightBoundMax[rightBoundMax[i]];
		}
		for (int i = 0; i < nums.length; i++)
			sum += nums[i] * ((long) (rightBoundMax[i] - i) * (i - leftBoundMax[i]) - (long) (rightBoundMin[i] - i) * (i - leftBoundMin[i]));
		return sum;
	}
}
