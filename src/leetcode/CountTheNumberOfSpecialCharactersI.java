package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountTheNumberOfSpecialCharactersI {
/*
You are given a string word. A letter is called special if it appears both in lowercase and uppercase in word.
Return the number of special letters in word.

Example 1:
Input: word = "aaAbcBC"
Output: 3
Explanation:
The special characters in word are 'a', 'b', and 'c'.

Example 2:
Input: word = "abc"
Output: 0
Explanation:
No character in word appears in uppercase.

Example 3:
Input: word = "abBCab"
Output: 1
Explanation:
The only special character in word is 'b'.

Constraints:
    1 <= word.length <= 50
    word consists of only lowercase and uppercase English letters.
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, "aaAbcBC"},
				{0, "abc"},
				{1, "abBCab"},});
	}

	public int numberOfSpecialChars(String word) {
		int result = 0, count[] = new int[26];
		for (int i = 0, length = word.length(), c; i < length; )
			if ((c = word.charAt(i++)) >= 'a') count[c - 'a'] |= 1;
			else count[c - 'A'] |= 2;
		for (int i : count) if (i > 2) result++;
		return result;
	}
}
