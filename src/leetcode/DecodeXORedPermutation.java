package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class DecodeXORedPermutation {
	/*
	There is an integer array perm that is a permutation of the first n positive integers, where n is always odd.
	It was encoded into another integer array encoded of length n - 1, such that encoded[i] = perm[i] XOR perm[i + 1]. For example, if perm = [1,3,2], then encoded = [2,1].
	Given the encoded array, return the original array perm. It is guaranteed that the answer exists and is unique.

	Example 1:
	Input: encoded = [3,1]
	Output: [1,2,3]
	Explanation: If perm = [1,2,3], then encoded = [1 XOR 2,2 XOR 3] = [3,1]

	Example 2:
	Input: encoded = [6,5,4,6]
	Output: [2,4,1,5,3]

	Constraints:
	3 <= n < 10^5
	n is odd.
	encoded.length == n - 1
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{1, 2, 3}, new int[]{3, 1}},
				{new int[]{2, 4, 1, 5, 3}, new int[]{6, 5, 4, 6}},
		});
	}

	public int[] decode(int[] encoded) {
		int result[] = new int[encoded.length + 1], xorAll = ((result.length + 1) >> 1) & 1, xorWithoutFirst = encoded[1];
		for (int i = 3; i < encoded.length; i += 2) xorWithoutFirst ^= encoded[i];
		result[0] = xorAll ^ xorWithoutFirst;
		for (int i = 1; i < result.length; i++) result[i] = encoded[i - 1] ^ result[i - 1];
		return result;
	}
}
