package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class ExistenceOfASubstringInAStringAndItsReverse {
	/*
	Given a string s, find any substring of length 2 which is also present in the reverse of s.
	Return true if such a substring exists, and false otherwise.

	Example 1:
	Input: s = "leetcode"
	Output: true
	Explanation: Substring "ee" is of length 2 which is also present in reverse(s) == "edocteel".

	Example 2:
	Input: s = "abcba"
	Output: true
	Explanation: All substrings of length 2 "ab", "bc", "cb", "ba" are also present in reverse(s) == "abcba".

	Example 3:
	Input: s = "abcd"
	Output: false
	Explanation: There is no substring of length 2 in s, which is also present in the reverse of s.

	Constraints:
		1 <= s.length <= 100
		s consists only of lowercase English letters.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{true, "leetcode"},
				{true, "abcba"},
				{false, "abcd"},
		});
	}

	public boolean isSubstringPresent(String s) {
		char[] chars = s.toCharArray();
		for (int i = 0, last = chars.length - 1; i < last; i++)
			for (int j = last; j > 0; j--)
				if (chars[i] == chars[j] && chars[i + 1] == chars[j - 1]) return true;
		return false;
	}
}
