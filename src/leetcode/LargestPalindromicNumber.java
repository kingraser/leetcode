package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class LargestPalindromicNumber {
	/*
	You are given a string num consisting of digits only.
	Return the largest palindromic integer (in the form of a string) that can be formed using digits taken from num. It should not contain leading zeroes.
	Notes:
	You do not need to use all the digits of num, but you must use at least one digit.
	The digits can be reordered.

	Example 1:
	Input: num = "444947137"
	Output: "7449447"
	Explanation:
	Use the digits "4449477" from "444947137" to form the palindromic integer "7449447".
	It can be shown that "7449447" is the largest palindromic integer that can be formed.

	Example 2:
	Input: num = "00009"
	Output: "9"
	Explanation:
	It can be shown that "9" is the largest palindromic integer that can be formed.
	Note that the integer returned should not contain leading zeroes.

	Constraints:
	1 <= num.length <= 10^5
	num consists of digits.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{"0", "0000"},
				{"6006", "6006"},
				{"1005001", "00001105"},
				{"7449447", "444947137"},
				{"9", "00009"},
		});
	}

	public String largestPalindromic(String num) {
		int digits[] = new int[10], index = 0, first, max = -1;
		num.chars().forEach(c -> digits[c - '0']++);
		char c, result[] = new char[num.length()];
		for (int i = 9, times; i >= 0; i--) {
			if (i > 0 || index > 0)
				for (times = digits[i] >> 1, digits[i] &= 1, c = (char) (i + '0'); times-- > 0; ) result[index++] = c;
			if (max == -1 && digits[i] > 0) max = i;
		}
		first = index;
		if (max != -1) result[index++] = (char) (max + '0');
		while (first > 0) result[index++] = result[--first];
		return new String(result, 0, index);
	}


}
