package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountSymmetricIntegers {
	/*
	You are given two positive integers low and high.
	An integer x consisting of 2 * n digits is symmetric if the sum of the first n digits of x is equal to the sum of the last n digits of x. Numbers with an odd number of digits are never symmetric.
	Return the number of symmetric integers in the range [low, high].

	Example 1:
	Input: low = 1, high = 100
	Output: 9
	Explanation: There are 9 symmetric integers between 1 and 100: 11, 22, 33, 44, 55, 66, 77, 88, and 99.

	Example 2:
	Input: low = 1200, high = 1230
	Output: 4
	Explanation: There are 4 symmetric integers between 1200 and 1230: 1203, 1212, 1221, and 1230.

	Constraints:
	1 <= low <= high <= 10^4
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{9, 1, 100},
				{4, 1200, 1230}
		});
	}

	public int countSymmetricIntegers(int low, int high) {return symmetric(high) - symmetric(low - 1);}

	private int symmetric(int num) {
		if (num < 100) return num / 11;
		if (num <= 1000) return 9;
		int result = 9, firstHalf = num / 100;
		for (int i = 10; i < firstHalf; i++) result += 10 - Math.abs(9 - (i / 10 + i % 10));
		for (int digitSum = firstHalf / 10 + firstHalf % 10, limit = Math.min(num % 100, digitSum > 9 ? 90 + digitSum - 9 : (digitSum * 10)), i = digitSum > 9 ? 10 * (digitSum - 9) + 9 : digitSum; i <= limit; i += 9)
			result++;
		return result;
	}
}
