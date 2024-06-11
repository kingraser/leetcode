package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class FindTheChildWhoHasTheBallAfterKSeconds {
/*
You are given two positive integers n and k. There are n children numbered from 0 to n - 1 standing in a queue in order from left to right.
Initially, child 0 holds a ball and the direction of passing the ball is towards the right direction. After each second, the child holding the ball passes it to the child next to them. Once the ball reaches either end of the line, i.e. child 0 or child n - 1, the direction of passing is reversed.
Return the number of the child who receives the ball after k seconds.

Example 1:
Input: n = 3, k = 5
Output: 1

Input: n = 5, k = 6
Output: 2

Example 3:
Input: n = 4, k = 2
Output: 2

Constraints:
    2 <= n <= 50
    1 <= k <= 50
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{1, 3, 5},
				{2, 5, 6},
				{2, 4, 2},
		});
	}

	public int numberOfChild(int n, int k) {
		int step = k % (n - 1);
		return ((k / (n - 1)) & 1) == 1 ? (n - 1) - k % (n - 1) : (k % (n - 1));
	}

	public int numberOfChildII(int n, int k) {
		return ((k / --n) & 1) == 1 ? n - k % n : (k % n);
	}
}
