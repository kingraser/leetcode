package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class NumberOfWaysToReachAPositionAfterExactlyKSteps {
	/*
	You are given two positive integers startPos and endPos. Initially, you are standing at position startPos on an infinite number line. With one step, you can move either one position to the left, or one position to the right.
	Given a positive integer k, return the number of different ways to reach the position endPos starting from startPos, such that you perform exactly k steps. Since the answer may be very large, return it modulo 109 + 7.
	Two ways are considered different if the order of the steps made is not exactly the same.
	Note that the number line includes negative integers.

	Example 1:
	Input: startPos = 1, endPos = 2, k = 3
	Output: 3
	Explanation: We can reach position 2 from 1 in exactly 3 steps in three ways:
	- 1 -> 2 -> 3 -> 2.
	- 1 -> 2 -> 1 -> 2.
	- 1 -> 0 -> 1 -> 2.
	It can be proven that no other way is possible, so we return 3.

	Example 2:
	Input: startPos = 2, endPos = 5, k = 10
	Output: 0
	Explanation: It is impossible to reach position 5 from position 2 in exactly 10 steps.

	Constraints:
	1 <= startPos, endPos, k <= 1000
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{934081896, 989, 1000, 99},
				{1, 1, 1000, 999},
				{3, 1, 2, 3},
				{0, 2, 5, 10}
		});
	}

	static final int MODULE = 1_000_000_007;

	//Two sufficient and necessary conditions from a to b with k steps:
	//1.    abs(a - b) <= k
	//2.    (k - abs(a - b)) % 2 == 0
	//Now let left represents the left steps, right represents the right steps
	//We will get 2 equations
	//1.    left + right = k
	//2.    right - left = b - a
	//So right = (b - a + k) / 2
	//The combinations is to pick right steps from k steps to go right.
	//return result combination(k, right)
	public int numberOfWays(int startPos, int endPos, int k) {
		if (k < Math.abs(endPos - startPos) || ((k - Math.abs(endPos - startPos)) & 1) == 1) return 0;
		long result = 1L;
		int rightCount = (endPos - startPos + k) >> 1;
		rightCount = Math.min(rightCount, k - rightCount);
		for (int i = 0; i < rightCount; ) {
			result = result * (k - i) % MODULE;
			result = result * modularInverse(++i) % MODULE;
		}
		return (int) result;
	}

	long modularInverse(long num) {
		return num == 1 ? 1 : (MODULE - MODULE / num) * modularInverse(MODULE % num) % MODULE;
	}
}
