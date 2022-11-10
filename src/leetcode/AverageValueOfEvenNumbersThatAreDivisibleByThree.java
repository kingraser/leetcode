package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class AverageValueOfEvenNumbersThatAreDivisibleByThree {
	/*
	Given an integer array nums of positive integers, return the average value of all even integers that are divisible by 3.
	Note that the average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.

	Example 1:
	Input: nums = [1,3,6,10,12,15]
	Output: 9
	Explanation: 6 and 12 are even numbers that are divisible by 3. (6 + 12) / 2 = 9.

	Example 2:
	Input: nums = [1,2,4,7,10]
	Output: 0
	Explanation: There is no single number that satisfies the requirement, so return 0.

	Constraints:
	1 <= nums.length <= 1000
	1 <= nums[i] <= 1000
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{9, new int[]{1, 3, 6, 10, 12, 15}},
				{0, new int[]{1, 2, 4, 7, 10}}
		});
	}

	public int averageValue(int[] nums) {
		int count = 0, sum = 0;
		for (int num : nums)
			if ((num & 1) == 0 && num % 3 == 0) {
				count++;
				sum += num;
			}
		return count == 0 ? 0 : sum / count;
	}
}
