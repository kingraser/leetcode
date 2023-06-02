package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SumMultiples {
	/*
	Given a positive integer n, find the sum of all integers in the range [1, n] inclusive that are divisible by 3, 5, or 7.
	Return an integer denoting the sum of all numbers in the given range satisfying the constraint.

	Example 1:
	Input: n = 7
	Output: 21
	Explanation: Numbers in the range [1, 7] that are divisible by 3, 5, or 7 are 3, 5, 6, 7. The sum of these numbers is 21.

	Example 2:
	Input: n = 10
	Output: 40
	Explanation: Numbers in the range [1, 10] that are divisible by 3, 5, or 7 are 3, 5, 6, 7, 9, 10. The sum of these numbers is 40.

	Example 3:
	Input: n = 9
	Output: 30
	Explanation: Numbers in the range [1, 9] that are divisible by 3, 5, or 7 are 3, 5, 6, 7, 9. The sum of these numbers is 30.

	Constraints:
	1 <= n <= 10^3
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{81, 15},
				{30, 9},
				{21, 7},
				{40, 10}
		});
	}

	public int sumOfMultiples(int n) {
		return sum(3, n / 3) + sum(5, n / 5) + sum(7, n / 7) - sum(15, n / 15) - sum(21, n / 21) - sum(35, n / 35) + sum(105, n / 105);
	}

	int sum(int q, int n) {
		return n < 1 ? 0 : n * ++n * q >> 1;
	}
}