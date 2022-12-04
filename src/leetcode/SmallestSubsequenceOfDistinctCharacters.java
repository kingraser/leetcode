package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SmallestSubsequenceOfDistinctCharacters {
	/*
	Given a string s, return the lexicographically smallest subsequence of s that contains all the distinct characters of s exactly once.

	Example 1:
	Input: s = "bcabc"
	Output: "abc"

	Example 2:
	Input: s = "cbacdcbc"
	Output: "acdb"

	Constraints:
	1 <= s.length <= 1000
	s consists of lowercase English letters.

	Note: This question is the same as 316: https://leetcode.com/problems/remove-duplicate-letters/
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{"abc", "bcabc"},
				{"acdb", "cbacdcbc"}
		});
	}

	public String smallestSubsequence(String s) {
		char[] chars = s.toCharArray();
		int frequency[] = new int[128], idx = -1;
		boolean[] isSelected = new boolean[128];
		for (char c : chars) frequency[c]++;
		for (char c : chars) {
			frequency[c]--;
			if (isSelected[c]) continue;
			while (idx >= 0 && (chars[idx] > c && frequency[chars[idx]] > 0)) isSelected[chars[idx--]] = false;
			isSelected[chars[++idx] = c] = true;
		}
		return new String(chars, 0, ++idx);
	}

}
