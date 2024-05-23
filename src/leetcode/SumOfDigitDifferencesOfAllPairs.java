package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SumOfDigitDifferencesOfAllPairs {
/*
You are given an array nums consisting of positive integers where all integers have the same number of digits.
The digit difference between two integers is the count of different digits that are in the same position in the two integers.
Return the sum of the digit differences between all pairs of integers in nums.

Example 1:
Input: nums = [13,23,12]
Output: 4
Explanation:
We have the following:
- The digit difference between 13 and 23 is 1.
- The digit difference between 13 and 12 is 1.
- The digit difference between 23 and 12 is 2.
So the total sum of digit differences between all pairs of integers is 1 + 1 + 2 = 4.

Example 2:
Input: nums = [10,10,10,10]
Output: 0
Explanation:
All the integers in the array are the same. So the total sum of digit differences between all pairs of integers will be 0.

Constraints:
    2 <= nums.length <= 10^5
    1 <= nums[i] < 10^9
    All integers in nums have the same number of digits.
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{5L, new int[]{50, 28, 48}},
				{4L, new int[]{13, 23, 12}},
				{0L, new int[]{10, 10, 10, 10}},
		});
	}

	public long sumDigitDifferences(int[] nums) {
		long result = 0, map[][] = new long[getLength(nums[0])][10];
		for (int num : nums) for (int i = 0; num > 0; num /= 10) map[i++][num % 10]++;
		for (long[] count : map)
			for (int i = 0; i < count.length; i++)
				if (count[i] != 0) for (int j = i + 1; j < count.length; j++)
					if (count[j] != 0) result += count[i] * count[j];
		return result;
	}

	int getLength(int n) {
		int result = 0;
		for (; n != 0; n /= 10) result++;
		return result;
	}
}
