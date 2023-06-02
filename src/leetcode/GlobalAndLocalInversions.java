package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class GlobalAndLocalInversions {
	/*
	You are given an integer array nums of length n which represents a permutation of all the integers in the range [0, n - 1].
	The number of global inversions is the number of the different pairs (i, j) where:
	0 <= i < j < n
	nums[i] > nums[j]
	The number of local inversions is the number of indices `i` where:
	0 <= i < n - 1
	nums[i] > nums[i + 1]
	Return true if the number of global inversions is equal to the number of local inversions.

	Example 1:
	Input: nums = [1,0,2]
	Output: true
	Explanation: There is 1 global inversion and 1 local inversion.

	Example 2:
	Input: nums = [1,2,0]
	Output: false
	Explanation: There are 2 global inversions and 1 local inversion.

	Constraints:
	n == nums.length
	1 <= n <= 10^5
	0 <= nums[i] < n
	All the integers of nums are unique.
	nums is a permutation of all the numbers in the range [0, n - 1].
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{true, new int[]{1, 0, 2}},
				{false, new int[]{1, 2, 0}},
		});
	}

	//All local inversions are global inversions.
	//If the number of global inversions is equal to the number of local inversions,
	//it means that all global inversions in permutations are local inversions.
	//It also means that we can not find A[i] > A[j] with i+2<=j.
	//In other words, max(A[i]) < A[i+2]
	public boolean isIdealPermutation(int[] nums) {
		for (int i = 0, max = 0; i < nums.length - 2; )
			if ((max = Math.max(max, nums[i])) > nums[i++ + 2]) return false;
		return true;
	}
}
