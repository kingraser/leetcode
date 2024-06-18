package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class NumberOfWonderfulSubstrings {
/*
A wonderful string is a string where at most one letter appears an odd number of times.
    For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
Given a string word that consists of the first ten lowercase English letters ('a' through 'j'), return the number of wonderful non-empty substrings in word. If the same substring appears multiple times in word, then count each occurrence separately.
A substring is a contiguous sequence of characters in a string.

Example 1:
Input: word = "aba"
Output: 4
Explanation: The four wonderful substrings are underlined below:
- "aba" -> "a"
- "aba" -> "b"
- "aba" -> "a"
- "aba" -> "aba"

Example 2:
Input: word = "aabb"
Output: 9
Explanation: The nine wonderful substrings are underlined below:
- "aabb" -> "a"
- "aabb" -> "aa"
- "aabb" -> "aab"
- "aabb" -> "aabb"
- "aabb" -> "a"
- "aabb" -> "abb"
- "aabb" -> "b"
- "aabb" -> "bb"
- "aabb" -> "b"

Example 3:
Input: word = "he"
Output: 2
Explanation: The two wonderful substrings are underlined below:
- "he" -> "h"
- "he" -> "e"

Constraints:
    1 <= word.length <= 10^5
    word consists of lowercase English letters from 'a' to 'j'.
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{4L, "aba"},
				{9L, "aabb"},
				{2L, "he"},
		});
	}

	public long wonderfulSubstrings(String word) {
		long result = 0, maskCount[] = new long[1024]; // cnt[state] stores how many times the state occurs
		maskCount[0] = 1;  //empty string gives case where all characters occur even number of times
		int mask = 0; // current state
		for (byte c : word.getBytes()) {
			// add count of same previous states and add 1 to count of times we've seen current state
			result += maskCount[mask ^= 1 << (c - 'a')]++;
			for (int i = 0; i < 10; i++) result += maskCount[mask ^ (1 << i)]; // try flick each switch
		}
		return result;
	}
}
