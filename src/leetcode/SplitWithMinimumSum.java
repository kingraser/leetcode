package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class SplitWithMinimumSum {

	/*
	Given a positive integer num, split it into two non-negative integers num1 and num2 such that:

	The concatenation of num1 and num2 is a permutation of num.
	In other words, the sum of the number of occurrences of each digit in num1 and num2 is equal to the number of occurrences of that digit in num.
	num1 and num2 can contain leading zeros.
	Return the minimum possible sum of num1 and num2.

	Notes:

	It is guaranteed that num does not contain any leading zeros.
	The order of occurrence of the digits in num1 and num2 may differ from the order of occurrence of num.


	Example 1:
	Input: num = 4325
	Output: 59
	Explanation: We can split 4325 so that num1 is 24 and num2 is 35, giving a sum of 59. We can prove that 59 is indeed the minimal possible sum.

	Example 2:
	Input: num = 687
	Output: 75
	Explanation: We can split 687 so that num1 is 68 and num2 is 7, which would give an optimal sum of 75.

	Constraints:
	10 <= num <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{59, 4325},
				{75, 687}
		});
	}

	public int splitNum(int num) {
		return getSum(getDigits(num));
	}

	int[] getDigits(int num) {
		int[] result = new int[11];
		for (; num != 0; num /= 10) result[++result[0]] = num % 10;
		return result;
	}

	int getSum(int[] digits) {
		int result = 0, i, base = 1;
		Arrays.sort(digits, 1, digits[0] + 1);
		for (i = digits[0], digits[0] = 0; i > 0; base *= 10) result += (digits[i--] + digits[i--]) * base;
		return result;
	}
}
