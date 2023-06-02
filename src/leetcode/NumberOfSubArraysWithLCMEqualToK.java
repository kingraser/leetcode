package leetcode;

import leetcode.util.MathUtil;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wit
 */
public class NumberOfSubArraysWithLCMEqualToK {
	/*
	Given an integer array nums and an integer k, return the number of sub_arrays of nums where the least common multiple of the sub_array's elements is k.
	A sub_array is a contiguous non-empty sequence of elements within an array.
	The least common multiple of an array is the smallest positive integer that is divisible by all the array elements.

	Example 1:
	Input: nums = [3,6,2,7,1], k = 6
	Output: 4
	Explanation: The sub_arrays of nums where 6 is the least common multiple of all the sub_array's elements are:
	- [3,6,2,7,1]
	- [3,6,2,7,1]
	- [3,6,2,7,1]
	- [3,6,2,7,1]

	Example 2:
	Input: nums = [3], k = 2
	Output: 0
	Explanation: There are no sub_arrays of nums where 2 is the least common multiple of all the sub_array's elements.

	Constraints:
	1 <= nums.length <= 1000
	1 <= nums[i], k <= 1000
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{4, new int[]{3, 6, 2, 7, 1}, 6},
				{0, new int[]{3}, 2}
		});
	}

	public int subarrayLCM(int[] nums, int k) {
		int count = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int a : nums) {
			if (a > k || k % a != 0) {
				map = new HashMap<>();
				continue;
			}
			Map<Integer, Integer> next = new HashMap<>() {{put(a, 1);}};
			map.forEach((key, value) -> next.merge(getLCM(a, key), value, Integer::sum));
			count += (map = next).getOrDefault(k, 0);
		}
		return count;
	}

	int getLCM(int a, int b) {return a * b / MathUtil.gcd(a, b);}
}