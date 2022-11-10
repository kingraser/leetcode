package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MinimumAdditionToMakeIntegerBeautiful {
	/*
	You are given two positive integers n and target.
	An integer is considered beautiful if the sum of its digits is less than or equal to target.
	Return the minimum non-negative integer x such that n + x is beautiful. The input will be generated such that it is always possible to make n beautiful.

	Example 1:
	Input: n = 16, target = 6
	Output: 4
	Explanation: Initially n is 16 and its digit sum is 1 + 6 = 7. After adding 4, n becomes 20 and digit sum becomes 2 + 0 = 2. It can be shown that we can not make n beautiful with adding non-negative integer less than 4.

	Example 2:
	Input: n = 467, target = 6
	Output: 33
	Explanation: Initially n is 467 and its digit sum is 4 + 6 + 7 = 17. After adding 33, n becomes 500 and digit sum becomes 5 + 0 + 0 = 5. It can be shown that we can not make n beautiful with adding non-negative integer less than 33.

	Example 3:
	Input: n = 1, target = 1
	Output: 0
	Explanation: Initially n is 1 and its digit sum is 1, which is already smaller than or equal to target.

	Constraints:
	1 <= n <= 10^12
	1 <= target <= 150
	The input will be generated such that it is always possible to make n beautiful.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{81L, 19L, 1},
				{4L, 16L, 6},
				{33L, 467L, 6},
				{0L, 1L, 1}
		});
	}

	long num, pows[] = new long[]{1, 10, 100, 1000, 10_000, 100_000, 1000_000, 10_000_000, 100_000_000, 1000_000_000, 10_000_000_000L, 100_000_000_000L, 1000_000_000_000L};

	public long makeIntegerBeautiful(long n, int target) {
		int digits[] = new int[14], left = digits.length, right = left, last = left - 1, sum = 0;
		for (num = 0; n > 0; n /= 10)
			for (sum += digits[--left] = (int) (n % 10); sum > target || (sum == target && right <= last); num += digits[right] * pows[last - right])
				sum -= digits[--right];
		return num == 0 ? 0L : pows[digits.length - right] - num;
	}
}
