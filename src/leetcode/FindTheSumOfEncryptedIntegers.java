package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class FindTheSumOfEncryptedIntegers {
	/*
	You are given an integer array nums containing positive integers. We define a function encrypt such that encrypt(x) replaces every digit in x with the largest digit in x. For example, encrypt(523) = 555 and encrypt(213) = 333.
	Return the sum of encrypted elements.

	Example 1:
	Input: nums = [1,2,3]
	Output: 6
	Explanation: The encrypted elements are [1,2,3]. The sum of encrypted elements is 1 + 2 + 3 == 6.

	Example 2:
	Input: nums = [10,21,31]
	Output: 66
	Explanation: The encrypted elements are [11,22,33]. The sum of encrypted elements is 11 + 22 + 33 == 66.

	Constraints:
		1 <= nums.length <= 50
		1 <= nums[i] <= 1000
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{6, new int[]{1, 2, 3}},
				{66, new int[]{10, 21, 31}},
		});
	}

	public int sumOfEncryptedInt(int[] nums) {
		int sum = 0;
		for (int num : nums) sum += encrypt(num);
		return sum;
	}

	private int encrypt(int num) {
		int digitCount = 0, maxDigit = 0, result = 0;
		while (num > 0) {
			maxDigit = Math.max(maxDigit, num % 10);
			num /= 10;
			digitCount++;
		}
		while (digitCount-- > 0) result = result * 10 + maxDigit;
		return result;
	}
}
