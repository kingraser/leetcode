package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Wit
 */
public class KDivisibleElementsSubArrays {
	/*
	Given an integer array nums and two integers k and p, return the number of distinct sub-arrays which have at most k elements divisible by p.
	Two arrays nums1 and nums2 are said to be distinct if:
	They are of different lengths, or
	There exists at least one index `i` where nums1[i] != nums2[i].
	A sub-array is defined as a non-empty contiguous sequence of elements in an array.

	Example 1:
	Input: nums = [2,3,3,2,2], k = 2, p = 2
	Output: 11
	Explanation:
	The elements at indices 0, 3, and 4 are divisible by p = 2.
	The 11 distinct sub-arrays which have at most k = 2 elements divisible by 2 are:
	[2], [2,3], [2,3,3], [2,3,3,2], [3], [3,3], [3,3,2], [3,3,2,2], [3,2], [3,2,2], and [2,2].
	Note that the sub-arrays [2] and [3] occur more than once in nums, but they should each be counted only once.
	The sub-array [2,3,3,2,2] should not be counted because it has 3 elements that are divisible by 2.

	Example 2:
	Input: nums = [1,2,3,4], k = 4, p = 1
	Output: 10
	Explanation:
	All element of nums are divisible by p = 1.
	Also, every sub-array of nums will have at most 4 elements that are divisible by 1.
	Since all sub-arrays are distinct, the total number of sub-arrays satisfying all the constraints is 10.

	Constraints:
	1 <= nums.length <= 200
	1 <= nums[i], p <= 200
	1 <= k <= nums.length

	Follow up:
	Can you solve this problem in O(n2) time complexity?
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{11, new int[]{2, 3, 3, 2, 2}, 2, 2},
				{10, new int[]{1, 2, 3, 4}, 4, 1},
		});
	}

	final int MAX_P = 200;
	long base = MAX_P + 1, mod = (Long.MAX_VALUE - MAX_P) / base, hash;

	public int countDistinct(int[] nums, int k, int p) {
		Set<Long> set = new HashSet<>();
		for (int left = 0, length = nums.length, right, count; left < length; left++)
			for (hash = 0, right = left, count = 0; right < length && k >= (count += nums[right] % p == 0 ? 1 : 0); )
				set.add(hash = (hash * base + nums[right++]) % mod);
		return set.size();
	}
}
