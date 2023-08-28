package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class WaysToExpressAnIntegerAsSumOfPowers {
	/*
	Given two positive integers n and x.
	Return the number of ways n can be expressed as the sum of the xth power of unique positive integers, in other words, the number of sets of unique integers [n1, n2, ..., nk] where n = n1x + n2x + ... + nkx.
	Since the result can be very large, return it modulo 109 + 7.
	For example, if n = 160 and x = 3, one way to express n is n = 23 + 33 + 53.

	Example 1:
	Input: n = 10, x = 2
	Output: 1
	Explanation: We can express n as the following: n = 32 + 12 = 10.
	It can be shown that it is the only way to express 10 as the sum of the 2nd power of unique integers.

	Example 2:
	Input: n = 4, x = 1
	Output: 2
	Explanation: We can express n in the following ways:
	- n = 41 = 4.
	- n = 31 + 11 = 4.

	Constraints:
	1 <= n <= 300
	1 <= x <= 5
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{1, 10, 2},
				{2, 4, 1}
		});
	}

	public int numberOfWays(int n, int x) {
		int dp[] = new int[301], mod = 1_000_000_007;
		dp[0] = 1;
		for (int num = 1, diff; (diff = (int) Math.pow(num, x)) <= n; num++)
			for (int i = n; i >= diff; i--)
				dp[i] = (dp[i] + dp[i - diff]) % mod;
		return dp[n];
	}
}
