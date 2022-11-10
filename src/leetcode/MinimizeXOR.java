package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MinimizeXOR {
	/*
	Given two positive integers num1 and num2, find the integer x such that:
	x has the same number of set bits as num2, and
	The value x XOR num1 is minimal.
	Note that XOR is the bitwise XOR operation.
	Return the integer x. The test cases are generated such that x is uniquely determined.
	The number of set bits of an integer is the number of 1's in its binary representation.

	Example 1:
	Input: num1 = 3, num2 = 5
	Output: 3
	Explanation:
	The binary representations of num1 and num2 are 0011 and 0101, respectively.
	The integer 3 has the same number of set bits as num2, and the value 3 XOR 3 = 0 is minimal.

	Example 2:
	Input: num1 = 1, num2 = 12
	Output: 3
	Explanation:
	The binary representations of num1 and num2 are 0001 and 1100, respectively.
	The integer 3 has the same number of set bits as num2, and the value 3 XOR 1 = 2 is minimal.

	Constraints:
	1 <= num1, num2 <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, 3, 5},
				{3, 1, 12}
		});
	}

	public int minimizeXor(int num1, int num2) {
		int res = num1, bitCount1 = Integer.bitCount(num1), bitCount2 = Integer.bitCount(num2);
		for (int i = 0, diff = Math.abs(bitCount1 - bitCount2); diff > 0; i++)
			if ((bitCount1 > bitCount2) ^ ((1 << i) & num1) == 0) {
				res ^= 1 << i;
				diff--;
			}
		return res;
	}
}
