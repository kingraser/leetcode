package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author Wit
 */
public class CountNumberOfBadPairs {
	/*
	You are given a 0-indexed integer array nums. A pair of indices (i, j) is a bad pair if i < j and j - i != nums[j] - nums[i].
	Return the total number of bad pairs in nums.

	Example 1:
	Input: nums = [4,1,3,3]
	Output: 5
	Explanation: The pair (0, 1) is a bad pair since 1 - 0 != 1 - 4.
	The pair (0, 2) is a bad pair since 2 - 0 != 3 - 4, 2 != -1.
	The pair (0, 3) is a bad pair since 3 - 0 != 3 - 4, 3 != -1.
	The pair (1, 2) is a bad pair since 2 - 1 != 3 - 1, 1 != 2.
	The pair (2, 3) is a bad pair since 3 - 2 != 3 - 3, 1 != 0.
	There are a total of 5 bad pairs, so we return 5.

	Example 2:
	Input: nums = [1,2,3,4,5]
	Output: 0
	Explanation: There are no bad pairs.

	Constraints:
	1 <= nums.length <= 10^5
	1 <= nums[i] <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{5L, new int[]{4, 1, 3, 3}},
				{0L, new int[]{1, 2, 3, 4, 5}}
		});
	}

	//Trick to this problem:
	//Total= Valid + Invalid
	//Invalid = Total- Valid
	//real Equation: ( j - i ) != ( A[j] - A[i] )
	//The simple and more intuitive form of above equation: ( j - A[j] ) != ( i - A[i] )
	public long countBadPairs(int[] nums) {
		long result = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int prev = map.getOrDefault(i - nums[i], 0);
			result += i - prev;
			map.put(i - nums[i], prev + 1);
		}
		return result;
	}
}
