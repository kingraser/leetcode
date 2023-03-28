package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class NumberOfEvenAndOddBits {
	/*
	You are given a positive integer n.
	Let even denote the number of even indices in the binary representation of n (0-indexed) with value 1.
	Let odd denote the number of odd indices in the binary representation of n (0-indexed) with value 1.
	Return an integer array answer where answer = [even, odd].

	Example 1:
	Input: n = 17
	Output: [2,0]
	Explanation: The binary representation of 17 is 10001.
	It contains 1 on the 0th and 4th indices.
	There are 2 even and 0 odd indices.

	Example 2:
	Input: n = 2
	Output: [0,1]
	Explanation: The binary representation of 2 is 10.
	It contains 1 on the 1st index.
	There are 0 even and 1 odd indices.

	Constraints:
	1 <= n <= 1000
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{2, 0}, 17},
				{new int[]{0, 1}, 2}
		});
	}

	int even1 = 0x55555555, odd1 = even1 << 1;

	public int[] evenOddBit(int n) {
		return new int[]{Integer.bitCount(even1 & n), Integer.bitCount(odd1 & n)};
	}
}
