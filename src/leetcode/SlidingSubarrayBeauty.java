package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SlidingSubarrayBeauty {
	/*
	Given an integer array nums containing n integers, find the beauty of each subarray of size k.
	The beauty of a subarray is the `x-th smallest` integer in the subarray if it is negative, or 0 if there are fewer than x negative integers.
	Return an integer array containing n - k + 1 integers, which denote the beauty of the sub_arrays in order from the first index in the array.
	A subarray is a contiguous non-empty sequence of elements within an array.

	Example 1:
	Input: nums = [1,-1,-3,-2,3], k = 3, x = 2
	Output: [-1,-2,-2]
	Explanation: There are 3 sub_arrays with size k = 3.
	The first subarray is [1, -1, -3] and the 2nd smallest negative integer is -1.
	The second subarray is [-1, -3, -2] and the 2nd smallest negative integer is -2.
	The third subarray is [-3, -2, 3] and the 2nd smallest negative integer is -2.

	Example 2:
	Input: nums = [-1,-2,-3,-4,-5], k = 2, x = 2
	Output: [-1,-2,-3,-4]
	Explanation: There are 4 sub_arrays with size k = 2.
	For [-1, -2], the 2nd smallest negative integer is -1.
	For [-2, -3], the 2nd smallest negative integer is -2.
	For [-3, -4], the 2nd smallest negative integer is -3.
	For [-4, -5], the 2nd smallest negative integer is -4.

	Example 3:
	Input: nums = [-3,1,2,-3,0,-3], k = 2, x = 1
	Output: [-3,0,-3,-3,-3]
	Explanation: There are 5 sub_arrays with size k = 2.
	For [-3, 1], the 1st smallest negative integer is -3.
	For [1, 2], there is no negative integer so the beauty is 0.
	For [2, -3], the 1st smallest negative integer is -3.
	For [-3, 0], the 1st smallest negative integer is -3.
	For [0, -3], the 1st smallest negative integer is -3.

	Constraints:
	n == nums.length
	1 <= n <= 10^5
	1 <= k <= n
	1 <= x <= k
	-50 <= nums[i] <= 50
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{-43, -8, -44, -8, -8, -2}, new int[]{-43, 8, -44, 36, -8, 17, 16, -44, -2, 28, 49}, 6, 2},
				{new int[]{-1, -2, -2}, new int[]{1, -1, -3, -2, 3}, 3, 2},
				{new int[]{-1, -2, -3, -4}, new int[]{-1, -2, -3, -4, -5}, 2, 2},
				{new int[]{-3, 0, -3, -3, -3}, new int[]{-3, 1, 2, -3, 0, -3}, 2, 1},
		});
	}

	public int[] getSubarrayBeauty(int[] nums, int k, int x) {
		int current = 50, currentNeed = x, result[] = new int[nums.length - k + 1], frequency[] = new int[51];
		for (int i = 0; i < k; ) frequency[abs(nums[i++])]++;
		while (current >= 0 && currentNeed > frequency[current]) currentNeed -= frequency[current--];
		result[0] = -current;
		for (int i = k, num, poll, j = 0; i < nums.length; result[j] = -current) {
			frequency[poll = abs(nums[j++])]--;
			frequency[num = abs(nums[i++])]++;
			if (poll > current) currentNeed++;
			if (num > current && --currentNeed < 1) {
				while (frequency[++current] < 1) ;
				currentNeed = frequency[current];
			}
			while (currentNeed > frequency[current]) currentNeed -= frequency[current--];
		}
		return result;
	}

	int abs(int n) {return n >= 0 ? 0 : -n;}
}
