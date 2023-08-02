package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountCompleteSubArraysInAnArray {
	/*
	You are given an array nums consisting of positive integers.
	We call a subarray of an array complete if the following condition is satisfied:
	The number of distinct elements in the subarray is equal to the number of distinct elements in the whole array.
	Return the number of complete sub-arrays.
	A subarray is a contiguous non-empty part of an array.

	Example 1:
	Input: nums = [1,3,1,2,2]
	Output: 4
	Explanation: The complete sub-arrays are the following: [1,3,1,2], [1,3,1,2,2], [3,1,2] and [3,1,2,2].

	Example 2:
	Input: nums = [5,5,5,5]
	Output: 10
	Explanation: The array consists only of the integer 5, so any subarray is complete. The number of sub-arrays that we can choose is 10.

	Constraints:
	1 <= nums.length <= 1000
	1 <= nums[i] <= 2000
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{4, new int[]{1, 3, 1, 2, 2}},
				{10, new int[]{5, 5, 5, 5}},
		});
	}

	static final int MAX = 2000;

	public int countCompleteSubarrays(int[] nums) {
		int result = 0, distinctCount = 0, counts[] = new int[MAX + 1];
		for (int num : nums) if (counts[num]++ == 0) distinctCount++;
		counts = new int[MAX + 1];
		for (int left = 0, right = 0, count = 0; right < nums.length; right++) {
			if (counts[nums[right]]++ == 0) count++;
			while (count == distinctCount) if (--counts[nums[left++]] == 0) count--;
			result += left;
		}
		return result;
	}
}
