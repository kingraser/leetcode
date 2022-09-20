package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class LengthOfTheLongestAlphabeticalContinuousSubstring {
	/*
	An alphabetical continuous string is a string consisting of consecutive letters in the alphabet. In other words, it is any substring of the string "abcdefghijklmnopqrstuvwxyz".
	For example, "abc" is an alphabetical continuous string, while "acb" and "za" are not.
	Given a string s consisting of lowercase letters only, return the length of the longest alphabetical continuous substring.

	Example 1:
	Input: s = "abacaba"
	Output: 2
	Explanation: There are 4 distinct continuous substrings: "a", "b", "c" and "ab".
	"ab" is the longest continuous substring.

	Example 2:
	Input: s = "abcde"
	Output: 5
	Explanation: "abcde" is the longest continuous substring.

	Constraints:
	1 <= s.length <= 10^5
	s consists of only English lowercase letters.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, "abacaba"},
				{5, "abcde"}
		});
	}

	public int longestContinuousSubstring(String s) {
		int result = 1, length = s.length();
		for (int left = 0, right = 1; left < length; result = Math.max(result, right - left), left = right++)
			while (right < length && s.charAt(right) - s.charAt(right - 1) == 1) right++;
		return result;
	}
}
