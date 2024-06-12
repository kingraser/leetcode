package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Wit
 */
public class LexicographicallyMinimumStringAfterRemovingStars {
/*
You are given a string s. It may contain any number of '*' characters. Your task is to remove all '*' characters.
While there is a '*', do the following operation:
    Delete the leftmost '*' and the smallest non-'*' character to its left. If there are several smallest characters, you can delete any of them.
Return the
lexicographically smallest
resulting string after removing all '*' characters.

Example 1:
Input: s = "aaba*"
Output: "aab"
Explanation:
We should delete one of the 'a' characters with '*'. If we choose s[3], s becomes the lexicographically smallest.

Example 2:
Input: s = "abc"
Output: "abc"
Explanation:
There is no '*' in the string.

Constraints:
    1 <= s.length <= 10^5
    s consists only of lowercase English letters and '*'.
    The input is generated such that it is possible to delete all '*' characters.
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{"aab", "aaba*"},
				{"abc", "abc"},
		});
	}

	@SuppressWarnings("unchecked")
	public String clearStars(String s) {
		Deque<Integer>[] map = new ArrayDeque[26];
		for (int i = 0; i < map.length; i++) map[i] = new ArrayDeque<>();
		byte[] bytes = s.getBytes();
		int length = bytes.length, resultSize = 0;
		for (int i = 0; i < length; i++)
			if (bytes[i] == '*')
				for (Deque<Integer> deque : map) {
					if (deque.isEmpty()) continue;
					bytes[deque.pop()] = bytes[i] = 0;
					break;
				}
			else map[bytes[i] - 'a'].push(i);
		return getString(bytes);
	}

	String getString(byte[] bytes) {
		int size = 0;
		for (byte b : bytes) if (b != 0) bytes[size++] = b;
		return new String(bytes, 0, size);
	}
}
