package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MinimumFlipsToMakeAORBEqualToC {
	/*
	Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
	Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.

	Example 1:
	Input: a = 2, b = 6, c = 5
	Output: 3
	Explanation: After flips a = 1 , b = 4 , c = 5 such that (a OR b == c)

	Example 2:
	Input: a = 4, b = 2, c = 7
	Output: 1

	Example 3:
	Input: a = 1, b = 2, c = 3
	Output: 0

	Constraints:
	1 <= a <= 10^9
	1 <= b <= 10^9
	1 <= c <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{0, 7, 7, 7},
				{3, 2, 6, 5},
				{1, 4, 2, 7},
				{0, 1, 2, 3}
		});
	}

	public int minFlips(int a, int b, int c) {
		/*
		(a or b) xor c get different bits
		we have to consider a corner case that at a bit, both a and b is 1, c is 0
		to get both a and b is 1, we use a&b
		use ~c to make 0 -> 1
		then use & operation to get the bits
		*/
		return Integer.bitCount((a | b) ^ c) + Integer.bitCount((a & b) & (~c));
	}
}
