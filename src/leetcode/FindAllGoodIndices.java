package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wit
 */
public class FindAllGoodIndices {
	/*
	You are given a 0-indexed integer array nums of size n and a positive integer k.
	We call an index i in the range k <= i < n - k good if the following conditions are satisfied:
	The k elements that are just before the index i are in non-increasing order.
	The k elements that are just after the index i are in non-decreasing order.
	Return an array of all good indices sorted in increasing order.

	Example 1:
	Input: nums = [2,1,1,1,3,4,1], k = 2
	Output: [2,3]
	Explanation: There are two good indices in the array:
	- Index 2. The subarray [2,1] is in non-increasing order, and the subarray [1,3] is in non-decreasing order.
	- Index 3. The subarray [1,1] is in non-increasing order, and the subarray [3,4] is in non-decreasing order.
	Note that the index 4 is not good because [4,1] is not non-decreasing.

	Example 2:
	Input: nums = [2,1,1,2], k = 2
	Output: []
	Explanation: There are no good indices in this array.

	Constraints:
	n == nums.length
	3 <= n <= 10^5
	1 <= nums[i] <= 10^6
	1 <= k <= n / 2
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{List.of(2, 3), new int[]{2, 1, 1, 1, 3, 4, 1}, 2},
				{List.of(), new int[]{2, 1, 1, 2}, 2},
		});
	}

	public List<Integer> goodIndices(int[] nums, int k) {
		int length = nums.length, leftDecrease[] = newIntArrayWithDefault1(length), rightIncrease[] = newIntArrayWithDefault1(length);
		List<Integer> result = new ArrayList<>(length);
		for (int i = 1; i < length; i++) if (nums[i - 1] >= nums[i]) leftDecrease[i] = leftDecrease[i - 1] + 1;
		for (int i = length - 2; i >= 0; i--) if (nums[i] <= nums[i + 1]) rightIncrease[i] = rightIncrease[i + 1] + 1;
		for (int i = k, end = length - k; i < end; i++)
			if (leftDecrease[i - 1] >= k && rightIncrease[i + 1] >= k) result.add(i);
		return result;
	}

	int[] newIntArrayWithDefault1(int length) {
		int[] result = new int[length];
		Arrays.fill(result, 1);
		return result;
	}
}
