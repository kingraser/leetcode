package leetcode;

import leetcode.util.MathUtil;
import leetcode.util.TestUtil;
import org.junit.Test;

import static leetcode.util.ArrayUtil.swap;

/**
 * @author Wit
 */
public class MakeKSubarraySumsEqual {
	/*
	You are given a 0-indexed integer array arr and an integer k. The array arr is circular. In other words, the first element of the array is the next element of the last element, and the last element of the array is the previous element of the first element.
	You can do the following operation any number of times:
	Pick any element from arr and increase or decrease it by 1.
	Return the minimum number of operations such that the sum of each subarray of length k is equal.
	A subarray is a contiguous part of the array.

	Example 1:
	Input: arr = [1,4,1,3], k = 2
	Output: 1
	Explanation: we can do one operation on index 1 to make its value equal to 3.
	The array after the operation is [1,3,1,3]
	- Subarray starts at index 0 is [1, 3], and its sum is 4
	- Subarray starts at index 1 is [3, 1], and its sum is 4
	- Subarray starts at index 2 is [1, 3], and its sum is 4
	- Subarray starts at index 3 is [3, 1], and its sum is 4

	Example 2:
	Input: arr = [2,5,5,7], k = 3
	Output: 5
	Explanation: we can do three operations on index 0 to make its value equal to 5 and two operations on index 3 to make its value equal to 5.
	The array after the operations is [5,5,5,5]
	- Subarray starts at index 0 is [5, 5, 5], and its sum is 15
	- Subarray starts at index 1 is [5, 5, 5], and its sum is 15
	- Subarray starts at index 2 is [5, 5, 5], and its sum is 15
	- Subarray starts at index 3 is [5, 5, 5], and its sum is 15

	Constraints:
	1 <= k <= arr.length <= 10^5
	1 <= arr[i] <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{10L, new int[]{6, 2, 8, 5, 7, 10}, 4},
				{1L, new int[]{1, 4, 1, 3}, 2},
				{5L, new int[]{2, 5, 5, 7}, 3},
		});
	}

	public long makeSubKSumEqual(int[] arr, int k) {
		long result = 0;
		for (int start = 0, step = MathUtil.gcd(arr.length, k), group[] = new int[arr.length / step]; start < step; start++) {
			for (int groupIndex = 0, i = start; groupIndex < group.length; i += k)
				group[groupIndex++] = arr[i > arr.length ? i -= arr.length : i];
			for (int i = 0, mid = findK(group, group.length >> 1); i < group.length; )
				result += Math.abs(group[i++] - mid);
		}
		return result;
	}

	private int findK(int[] nums, int k) {return findK(nums, k, 0, nums.length - 1);}

	/**
	 * @param nums
	 * @param k(search-index)
	 * @param start(inclusive)
	 * @param end(inclusive)
	 * @return the no k+1 element
	 */
	private int findK(int[] nums, int k, int start, int end) {
		if (start >= end) return nums[start];
		int m = partition(nums, start, end);
		return m == k ? nums[m] : m < k ? findK(nums, k, m + 1, end) : findK(nums, k, start, m - 1);
	}

	/**
	 * @param nums
	 * @param from(inclusive)
	 * @param to(inclusive)
	 * @return index k where element k is smaller than elements in [from,k) and is
	 * larger than elements in (k,end]
	 */
	private int partition(int[] nums, int from, int to) {
		int element = nums[from], idx = from;
		for (int i = from + 1; i <= to; i++) if (nums[i] > element) swap(nums, ++idx, i);
		swap(nums, from, idx);
		return idx;
	}
}
