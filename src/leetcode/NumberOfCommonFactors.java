package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class NumberOfCommonFactors {
	/*
	Given two positive integers a and b, return the number of common factors of a and b.
	An integer x is a common factor of a and b if x divides both a and b.

	Example 1:
	Input: a = 12, b = 6
	Output: 4
	Explanation: The common factors of 12 and 6 are 1, 2, 3, 6.

	Example 2:
	Input: a = 25, b = 30
	Output: 2
	Explanation: The common factors of 25 and 30 are 1, 5.

	Constraints:
	1 <= a, b <= 1000
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, 452, 392},
				{1, 43, 945},
				{4, 12, 6},
				{2, 25, 30}
		});
	}

	public int commonFactors(int a, int b) {return a > b ? commonFactors(b, a) : getFactors(getGcd(a, b));}

	int getFactors(int gcd) {
		if (gcd == 1) return 1;
		int result = 2, factor = 2;
		for (int sqrt = (int) Math.sqrt(gcd); factor <= sqrt; factor++) if (gcd % factor == 0) result += 2;
		return --factor * factor == gcd ? --result : result;
	}

	int getGcd(int a, int b) {return (b = b % a) == 0 ? a : getGcd(b, a);}
}
