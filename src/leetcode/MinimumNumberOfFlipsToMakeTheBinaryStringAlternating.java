package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MinimumNumberOfFlipsToMakeTheBinaryStringAlternating {
	/*
	You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:
	Type-1: Remove the character at the start of the string s and append it to the end of the string.
	Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
	Return the minimum number of type-2 operations you need to perform such that s becomes alternating.
	The string is called alternating if no two adjacent characters are equal.
	For example, the strings "010" and "1010" are alternating, while the string "0100" is not.

	Example 1:
	Input: s = "111000"
	Output: 2
	Explanation: Use the first operation two times to make s = "100011".
	Then, use the second operation on the third and sixth elements to make s = "101010".

	Example 2:
	Input: s = "010"
	Output: 0
	Explanation: The string is already alternating.

	Example 3:
	Input: s = "1110"
	Output: 1
	Explanation: Use the second operation on the second element to make s = "1010".

	Constraints:
	1 <= s.length <= 10^5
	s[i] is either '0' or '1'.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, "111000"},
				{0, "010"},
				{1, "1110"},
		});
	}

	public int minFlips(String s) {
		char[] chars = s.toCharArray();
		int length = chars.length, count1[] = new int[2], oddLength = length >> 1, evenLength = oddLength + 1, result = length;
		for (int i = 0; i < length; i++) if (chars[i] == '1') count1[i & 1]++;
		if ((length & 1) == 0) return Math.min(oddLength - (count1[0] -= count1[1]), oddLength + count1[0]);
		for (int i = 0, diff = count1[0] - count1[1]; i < length; i++) result = Math.min(result, Math.min(evenLength - (diff = chars[i] == '1' ? -diff + 2 : -diff), oddLength + diff));
		return result;
	}
}
