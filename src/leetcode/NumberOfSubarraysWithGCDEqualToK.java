package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class NumberOfSubarraysWithGCDEqualToK {
	/*
	Given an integer array nums and an integer k, return the number of subarrays of nums where the greatest common divisor of the subarray's elements is k.
	A subarray is a contiguous non-empty sequence of elements within an array.
	The greatest common divisor of an array is the largest integer that evenly divides all the array elements.

	Example 1:
	Input: nums = [9,3,1,2,6,3], k = 3
	Output: 4
	Explanation: The subarrays of nums where 3 is the greatest common divisor of all the subarray's elements are:
	- [9,3,1,2,6,3]
	- [9,3,1,2,6,3]
	- [9,3,1,2,6,3]
	- [9,3,1,2,6,3]

	Example 2:
	Input: nums = [4], k = 7
	Output: 0
	Explanation: There are no subarrays of nums where 7 is the greatest common divisor of all the subarray's elements.

	Constraints:
	1 <= nums.length <= 1000
	1 <= nums[i], k <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{1, new int[]{1}, 1},
				{0, new int[]{5}, 1},
				{10, new int[]{3, 3, 4, 1, 2}, 1},
				{7, new int[]{3, 12, 9, 6}, 3},
				{4, new int[]{9, 3, 1, 2, 6, 3}, 3},
				{0, new int[]{4}, 7}
		});
	}

	public int subarrayGCD(int[] nums, int k) {
		int result = 0, list[][] = new int[64][2], previousIdx = 0, currentIdx = 0;
		for (int gcd : nums) {
			if (gcd < k || gcd % k != 0) {
				previousIdx = previousIdx >= 32 ? 32 : 0;// clear the previous gcd list
				continue;
			}
			list[currentIdx++] = new int[]{gcd, 1};
			for (int i = previousIdx >= 32 ? 32 : 0; i < previousIdx; i++)
				if (list[currentIdx - 1][0] == (gcd = getGcd(gcd, list[i][0]))) list[currentIdx - 1][1] += list[i][1];
				else list[currentIdx++] = new int[]{gcd, list[i][1]};
			if (list[currentIdx - 1][0] == k) result += list[currentIdx - 1][1];
			currentIdx = (previousIdx = currentIdx) >= 32 ? 0 : 32;// swap the previous gcd list and the current gcd list
		}
		return result;
	}

	int getGcd(int a, int b) {return a == 0 ? b : getGcd(b % a, a);}
}
