package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashSet;

/**
 * @author Wit
 */
public class FindSubArraysWithEqualSum {
	/*
	Given a 0-indexed integer array nums, determine whether there exist two sub-arrays of length 2 with equal sum. Note that the two sub-arrays must begin at different indices.
	Return true if these sub-arrays exist, and false otherwise.
	A subarray is a contiguous non-empty sequence of elements within an array.

	Example 1:
	Input: nums = [4,2,4]
	Output: true
	Explanation: The sub-arrays with elements [4,2] and [2,4] have the same sum of 6.

	Example 2:
	Input: nums = [1,2,3,4,5]
	Output: false
	Explanation: No two sub-arrays of size 2 have the same sum.

	Example 3:
	Input: nums = [0,0,0]
	Output: true
	Explanation: The sub-arrays [nums[0],nums[1]] and [nums[1],nums[2]] have the same sum of 0.
	Note that even though the sub-arrays have the same content, the two sub-arrays are considered different because they are in different positions in the original array.

	Constraints:
	2 <= nums.length <= 1000
	-10^9 <= nums[i] <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{true, new int[]{4, 2, 4}},
				{false, new int[]{1, 2, 3, 4, 5}},
				{true, new int[3]}
		});
	}

	public boolean findSubarrays(int[] nums) {
		HashSet<Integer> set = new HashSet<>(nums.length);
		for (int i = 1; i < nums.length; i++) if (!set.add(nums[i] + nums[i - 1])) return true;
		return false;
	}
}
