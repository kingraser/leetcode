package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wit
 */
public class CountTheNumberOfGoodSubArrays {
	/*
	Given an integer array nums and an integer k, return the number of good sub_arrays of nums.
	A sub_array arr is good if there are at least k pairs of indices (i, j) such that i < j and arr[i] == arr[j].
	A sub_array is a contiguous non-empty sequence of elements within an array.

	Example 1:
	Input: nums = [1,1,1,1,1], k = 10
	Output: 1
	Explanation: The only good sub_array is the array nums itself.

	Example 2:
	Input: nums = [3,1,4,3,2,2,4], k = 2
	Output: 4
	Explanation: There are 4 different good sub_arrays:
	- [3,1,4,3,2,2] that has 2 pairs.
	- [3,1,4,3,2,2,4] that has 3 pairs.
	- [1,4,3,2,2,4] that has 2 pairs.
	- [4,3,2,2,4] that has 2 pairs.

	Constraints:
	1 <= nums.length <= 10^5
	1 <= nums[i], k <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{1L, new int[]{1, 1, 1, 1, 1}, 10},
				{4L, new int[]{3, 1, 4, 3, 2, 2, 4}, 2}
		});
	}

	public long countGood(int[] nums, int k) {
		long result = 0;
		Map<Integer, Integer> countMap = new HashMap<>();
		for (int left = 0, right = 0, count = 0, num, length = nums.length; right < length; right++)
			for (count += countMap.merge(nums[right], 1, Integer::sum) - 1, num = length - right; count >= k; result += num)
				count -= countMap.merge(nums[left++], -1, Integer::sum);
		return result;
	}
}
