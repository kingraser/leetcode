package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountNumberOfNiceSubArrays {
	/*
	Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.
	Return the number of nice sub-arrays.

	Example 1:
	Input: nums = [1,1,2,1,1], k = 3
	Output: 2
	Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].

	Example 2:
	Input: nums = [2,4,6], k = 1
	Output: 0
	Explanation: There is no odd numbers in the array.

	Example 3:
	Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
	Output: 16

	Constraints:
	    1 <= nums.length <= 50000
	    1 <= nums[i] <= 10^5
	    1 <= k <= nums.length
	*/


	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, new int[]{1, 1, 2, 1, 1}, 3},
				{0, new int[]{2, 4, 6}, 1},
				{16, new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2},
		});
	}

	public int numberOfSubarrays(int[] nums, int k) {
		int result = 0;
		for (int left = 0, right = 0, count = 0; right < nums.length; result += count)
			if ((nums[right++] & 1) == 1) for (k--, count = 0; k == 0; count++) k += nums[left++] & 1;
		return result;
	}

	public int numberOfSubarraysII(int[] nums, int k) {
		int result = 0, prefixOddCount[] = new int[nums.length + 1], currentOddCount = 0; // Array to keep track of the prefix sums of odd numbers
		prefixOddCount[0] = 1; // There's one way to have zero odd numbers - by taking no elements
		for (int num : nums) {
			if ((currentOddCount += (num & 1)) >= k) result += prefixOddCount[currentOddCount - k];
			prefixOddCount[currentOddCount]++;
		}
		return result;
	}


}
