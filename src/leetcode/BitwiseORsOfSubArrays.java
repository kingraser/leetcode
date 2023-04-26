package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Wit
 */
public class BitwiseORsOfSubArrays {
	/*
	Given an integer array arr, return the number of distinct bitwise ORs of all the non-empty sub-arrays of arr.
	The bitwise OR of a subarray is the bitwise OR of each integer in the subarray. The bitwise OR of a subarray of one integer is that integer.
	A subarray is a contiguous non-empty sequence of elements within an array.

	Example 1:
	Input: arr = [0]
	Output: 1
	Explanation: There is only one possible result: 0.

	Example 2:
	Input: arr = [1,1,2]
	Output: 3
	Explanation: The possible sub-arrays are [1], [1], [2], [1, 1], [1, 2], [1, 1, 2].
	These yield the results 1, 1, 2, 1, 3, 3.
	There are 3 unique values, so the answer is 3.

	Example 3:
	Input: arr = [1,2,4]
	Output: 6
	Explanation: The possible results are 1, 2, 3, 4, 6, and 7.

	Constraints:
	1 <= arr.length <= 5 * 10^4
	0 <= arr[i] <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{1, new int[]{0}},
				{3, new int[]{1, 1, 2}},
				{6, new int[]{1, 2, 4}},
		});
	}

	public int subarrayBitwiseORs(int[] arr) {
		Set<Integer> result = new HashSet<>();
		for (int left = arr.length - 1; left >= 0; result.add(arr[left--]))
			for (int right = left + 1; right < arr.length &&  arr[right] != (arr[right] |= arr[left]); ) result.add(arr[right++]);
		return result.size();
	}
}
