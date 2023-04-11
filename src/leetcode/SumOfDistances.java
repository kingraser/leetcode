package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wit
 */
public class SumOfDistances {
	/*
	You are given a 0-indexed integer array nums. There exists an array arr of length nums.length, where arr[i] is the sum of |i - j| over all j such that nums[j] == nums[i] and j != i. If there is no such j, set arr[i] to be 0.
	Return the array arr.

	Example 1:
	Input: nums = [1,3,1,1,2]
	Output: [5,0,3,4,0]
	Explanation:
	When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. Therefore, arr[0] = |0 - 2| + |0 - 3| = 5.
	When i = 1, arr[1] = 0 because there is no other index with value 3.
	When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. Therefore, arr[2] = |2 - 0| + |2 - 3| = 3.
	When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. Therefore, arr[3] = |3 - 0| + |3 - 2| = 4.
	When i = 4, arr[4] = 0 because there is no other index with value 2.

	Example 2:
	Input: nums = [0,5,3]
	Output: [0,0,0]
	Explanation: Since each element in nums is distinct, arr[i] = 0 for all i.

	Constraints:
	1 <= nums.length <= 10^5
	0 <= nums[i] <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new long[]{5, 0, 3, 4, 0}, new int[]{1, 3, 1, 1, 2}},
				{new long[3], new int[]{0, 5, 3}},
		});
	}

	public long[] distance(int[] nums) {
		long[] result = new long[nums.length];
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
		map.forEach((k, v) -> {
			for (long i = 0, size = v.size(), index, totalSum = v.stream().mapToInt(j -> j).sum(), preSum = 0; i < size; preSum += index, i++)
				result[(int) (index = v.get((int) i))] = totalSum - (preSum << 1) + ((i << 1) - size) * index;
		});
		return result;
	}
}
