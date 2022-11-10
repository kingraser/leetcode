package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SumOfNumberAndItsReverse {
	/*
	Given a non-negative integer num, return true if num can be expressed as the sum of any non-negative integer and its reverse, or false otherwise.

	Example 1:
	Input: num = 443
	Output: true
	Explanation: 172 + 271 = 443 so we return true.

	Example 2:
	Input: num = 63
	Output: false
	Explanation: 63 cannot be expressed as the sum of a non-negative integer and its reverse so we return false.

	Example 3:
	Input: num = 181
	Output: true
	Explanation: 140 + 041 = 181 so we return true. Note that when a number is reversed, there may be leading zeros.

	Constraints:
	0 <= num <= 10^5
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{true, 99988},
				{true, 1291},
				{true, 443},
				{false, 63},
				{true, 181},
				{true, 10},
				{true, 12},
				{true, 11},
				{false, 20},
				{false, 100},
				{true, 187},
				{false, 308}
		});
	}


	public boolean sumOfNumberAndReverse(int num) {
		if (num < 19) {
			if ((num & 1) == 0) return true;
			if (num < 11) return false;
		}
		if (num < 199) {
			if (num % 11 == 0) return true;
			if (num < 101) return false;
		}
		if (num < 1999) {
			int numCopy = num, lastDigit = num % 10;
			if ((numCopy -= lastDigit * 101) >= 0 && numCopy <= 180 && numCopy % 20 == 0) return true;
			if (lastDigit < 9 && (numCopy -= 1010) >= 0 && numCopy <= 180 && numCopy % 20 == 0) return true;
			if (num < 1001) return false;
		}
		if (num < 19_999) {
			int numCopy = num, lastDigit = num % 10;
			if ((numCopy -= lastDigit * 1001) >= 0 && numCopy <= 1980 && numCopy % 110 == 0) return true;
			if (lastDigit < 9 && (numCopy -= 10010) >= 0 && numCopy <= 1980 && numCopy % 110 == 0) return true;
			if (num < 10_001) return false;
		}
		int lastDigit = num % 10;
		if (lastDigit == 0 || (num -= lastDigit * 10001) < 0 || num % 10 != 0) return false;
		num /= 10;
		if ((num -= (lastDigit = num % 10) * 101) >= 0 && num <= 180 && num % 20 == 0) return true;
		return lastDigit < 9 && (num -= 1010) >= 0 && num <= 180 && num % 20 == 0;
	}
}
