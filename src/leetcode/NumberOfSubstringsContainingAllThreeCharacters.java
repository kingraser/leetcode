package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class NumberOfSubstringsContainingAllThreeCharacters {
	/*
	Given a string s consisting only of characters a, b and c.
	Return the number of substrings containing at least one occurrence of all these characters a, b and c.

	Example 1:
	Input: s = "abcabc"
	Output: 10
	Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).

	Example 2:
	Input: s = "aaacb"
	Output: 3
	Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".

	Example 3:
	Input: s = "abc"
	Output: 1

	Constraints:
	3 <= s.length <= 5 x 10^4
	s only consists of a, b or c characters.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{10, "abcabc"},
				{3, "aaacb"},
				{1, "abc"},
		});
	}

	public int numberOfSubstrings(String s) {
		int res = 0, last[] = {-1, -1, -1};
		for (int i = 0, length = s.length(); i < length; res += 1 + Math.min(last[0], Math.min(last[1], last[2])))
			last[s.charAt(i) - 'a'] = i++;
		return res;
	}
}
