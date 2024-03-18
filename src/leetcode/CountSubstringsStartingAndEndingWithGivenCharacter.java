package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountSubstringsStartingAndEndingWithGivenCharacter {
	/*
	You are given a string s and a character c. Return the total number of substrings of s that start and end with c.

	Example 1:
	Input: s = "abada", c = "a"
	Output: 6
	Explanation: Substrings starting and ending with "a" are: "abada", "abada", "abada", "abada", "abada", "abada".

	Example 2:
	Input: s = "zzz", c = "z"
	Output: 6
	Explanation: There are a total of 6 substrings in s and all start and end with "z".

	Constraints:
		1 <= s.length <= 10^5
		s and c consist only of lowercase English letters.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{6L, "abada", 'a'},
				{6L, "zzz", 'z'},
		});
	}

	public long countSubstrings(String s, char c) {
		long count = 0;
		for (int i = 0, length = s.length(); i < length; ) if (s.charAt(i++) == c) count++;
		return (count * ++count) >> 1;
	}
}
