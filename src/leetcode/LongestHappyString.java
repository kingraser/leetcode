package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class LongestHappyString {
	/*
	A string s is called happy if it satisfies the following conditions:

	s only contains the letters 'a', 'b', and 'c'.
	s does not contain any of "aaa", "bbb", or "ccc" as a substring.
	s contains at most a occurrences of the letter 'a'.
	s contains at most b occurrences of the letter 'b'.
	s contains at most c occurrences of the letter 'c'.
	Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy strings, return any of them. If there is no such string, return the empty string "".

	A substring is a contiguous sequence of characters within a string.

	Example 1:
	Input: a = 1, b = 1, c = 7
	Output: "ccaccbcc"
	Explanation: "ccbccacc" would also be a correct answer.

	Example 2:
	Input: a = 7, b = 1, c = 0
	Output: "aabaa"
	Explanation: It is the only correct answer in this case.

	Constraints:
	0 <= a, b, c <= 100
	a + b + c > 0
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{"ccaccbcc", 1, 1, 7},
				{"aabaa", 7, 1, 0},
		});
	}

	public String longestDiverseString(int a, int b, int c) {
		char[] result = new char[a + b + c], chars[] = new char[][]{{(char) a, 'a'}, {(char) b, 'b'}, {(char) c, 'c'}};
		for (int size = 0; ; ) {
			Arrays.sort(chars, (a1, a2) -> a2[0] - a1[0]);
			for (int i = Math.min(2, chars[0][0]); i-- > 0; chars[0][0]--) result[size++] = chars[0][1];
			if (chars[1][0] == 0) return new String(result, 0, size);
			if (chars[0][0] >= chars[1][0]) {
				result[size++] = chars[1][1];
				chars[1][0]--;
			}
		}
	}
}
