package leetcode;

import leetcode.util.MathUtil;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MinimumNumberOfOperationsToMakeAllArrayElementsEqualTo1 {
	/*
	You are given a 0-indexed array nums consisting of positive integers. You can do the following operation on the array any number of times:
	Select an index i such that 0 <= i < n - 1 and replace either of nums[i] or nums[i+1] with their gcd value.
	Return the minimum number of operations to make all elements of nums equal to 1. If it is impossible, return -1.
	The gcd of two integers is the greatest common divisor of the two integers.

	Example 1:
	Input: nums = [2,6,3,4]
	Output: 4
	Explanation: We can do the following operations:
	- Choose index i = 2 and replace nums[2] with gcd(3,4) = 1. Now we have nums = [2,6,1,4].
	- Choose index i = 1 and replace nums[1] with gcd(6,1) = 1. Now we have nums = [2,1,1,4].
	- Choose index i = 0 and replace nums[0] with gcd(2,1) = 1. Now we have nums = [1,1,1,4].
	- Choose index i = 2 and replace nums[3] with gcd(1,4) = 1. Now we have nums = [1,1,1,1].

	Example 2:
	Input: nums = [2,10,6,14]
	Output: -1
	Explanation: It can be shown that it is impossible to make all the elements equal to 1.

	Constraints:
	2 <= nums.length <= 50
	1 <= nums[i] <= 10^6
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{4, new int[]{2, 6, 3, 4}},
				{-1, new int[]{2, 10, 6, 14}},
		});
	}

	public int minOperations(int[] nums) {
		int res = Integer.MAX_VALUE, oneCount = 0;
		for (int num : nums) if (num == 1) oneCount++;
		if (oneCount != 0) return nums.length - oneCount;
		for (int i = 0, num, j, k; i < nums.length - 1 && res > 1; res = num == 1 && k < res ? k : res, i++)
			for (num = nums[i], j = i + 1, k = 1; j < nums.length && k < res && (num = MathUtil.gcd(num, nums[j++])) != 1; k++) ;
		return res == Integer.MAX_VALUE ? -1 : res + nums.length - 1;
	}
}
