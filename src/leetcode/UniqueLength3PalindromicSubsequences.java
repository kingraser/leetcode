package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class UniqueLength3PalindromicSubsequences {
	/*
	Given a string s, return the number of unique palindromes of length three that are a subsequence of s.
	Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.
	A palindrome is a string that reads the same forwards and backwards.
	A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
	    For example, "ace" is a subsequence of "abcde".

	Example 1:
	Input: s = "aabca"
	Output: 3
	Explanation: The 3 palindromic subsequences of length 3 are:
	- "aba" (subsequence of "aabca")
	- "aaa" (subsequence of "aabca")
	- "aca" (subsequence of "aabca")

	Example 2:
	Input: s = "adc"
	Output: 0
	Explanation: There are no palindromic subsequences of length 3 in "adc".

	Example 3:
	Input: s = "bbcbaba"
	Output: 4
	Explanation: The 4 palindromic subsequences of length 3 are:
	- "bbb" (subsequence of "bbcbaba")
	- "bcb" (subsequence of "bbcbaba")
	- "bab" (subsequence of "bbcbaba")
	- "aba" (subsequence of "bbcbaba")

	Constraints:
	    3 <= s.length <= 10^5
	    s consists of only lowercase English letters.
	*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, "aabca"},
				{0, "adc"},
				{4, "bbcbaba"},
		});
	}

	public int countPalindromicSubsequence(String s) {
		// 1 for get 2 letter; 2 for get 3 letter
		int result = 0, map[][] = new int[26][26], left[] = new int[26];
		Arrays.fill(left, Integer.MAX_VALUE);
		byte[] chars = s.getBytes();
		for (int i = 0; i < chars.length; i++) {
			int c = chars[i] - 'a';
			for (int first = 0; first < 26; first++) {
				if (map[c][first] == 1) {
					map[c][first] = 2;
					result++;
				}
				if (left[first] < i && map[first][c] == 0) map[first][c] = 1;
			}
			if (left[c] == Integer.MAX_VALUE) left[c] = i;
		}
		return result;
	}

	public int countPalindromicSubsequenceII(String s) {
		int result = 0, left[] = new int[26], right[] = new int[26];
		Arrays.fill(left, Integer.MAX_VALUE);
		byte[] chars = s.getBytes();
		for (int i = 0, c; i < chars.length; ) {
			right[c = chars[i] - 'a'] = i++;
			if (left[c] == Integer.MAX_VALUE) left[c] = i;
		}
		for (int c = 0, map[] = new int[128]; c < 26; Arrays.fill(map, 'a', 'z' + 1, 0), c++)
			while (left[c] < right[c]) if (map[chars[left[c]++]]++ == 0) result++;
		return result;
	}
}
