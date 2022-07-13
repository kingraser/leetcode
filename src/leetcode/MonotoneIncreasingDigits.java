package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MonotoneIncreasingDigits {
	/*
	An integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.
	Given an integer n, return the largest number that is less than or equal to n with monotone increasing digits.

	Example 1:
	Input: n = 10
	Output: 9

	Example 2:
	Input: n = 1234
	Output: 1234

	Example 3:
	Input: n = 332
	Output: 299

	Constraints:
	0 <= n <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{113999999, 114191537},
				{9, 10},
				{1234, 1234},
				{299, 332}
		});
	}

	public int monotoneIncreasingDigits(int n) {
		// flag is the first descending index calculating from right to left
		int digits[] = getArray(n), flag = digits.length;
		for (int i = digits.length - 1, first = digits[0] + 1; i > first; i--)
			if (digits[i] < digits[i - 1]) digits[(flag = i) - 1]--;
		for (int i = flag; i < digits.length; i++) digits[i] = 9;
		return toString(digits);
	}


	int[] getArray(int n) {
		int[] result = new int[11];
		for (result[0] = result.length - 1; n != 0; n /= 10) result[result[0]--] = n % 10;
		return result;
	}

	int toString(int[] digits) {
		int result = 0;
		for (int i = digits.length - 1, base = 1; i > digits[0]; base *= 10) result += digits[i--] * base;
		return result;
	}
}
