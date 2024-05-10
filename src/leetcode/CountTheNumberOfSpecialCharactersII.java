package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountTheNumberOfSpecialCharactersII {
/*
You are given a string word. A letter c is called special if it appears both in lowercase and uppercase in word, and every lowercase occurrence of c appears before the first uppercase occurrence of c.
Return the number of special letters in word.

Example 1:
Input: word = "aaAbcBC"
Output: 3
Explanation:
The special characters are 'a', 'b', and 'c'.

Example 2:
Input: word = "abc"
Output: 0
Explanation:
There are no special characters in word.

Example 3:
Input: word = "AbBCab"
Output: 0
Explanation:
There are no special characters in word.

Constraints:
    1 <= word.length <= 2 * 10^5
    word consists of only lowercase and uppercase English letters.
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, "aaAbcBC"},
				{0, "abc"},
				{0, "AbBCab"},
		});
	}

	public int numberOfSpecialChars(String word) {
		int result = 0, map[] = new int[128];
		for (int i = 0, c, length = word.length(); i < length; )
			if ((c = word.charAt(i++)) >= 'a' || map[c] == 0) map[c] = i;
		for (int i = 'a', j = 'A'; i <= 'z'; i++, j++) if (map[i] > 0 && map[j] > map[i]) result++;
		return result;
	}

}
