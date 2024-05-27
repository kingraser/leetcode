package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class StringCompressionIII {
/*
Given a string word, compress it using the following algorithm:
    Begin with an empty string comp. While word is not empty, use the following operation:
        Remove a maximum length prefix of word made of a single character c repeating at most 9 times.
        Append the length of the prefix followed by c to comp.
Return the string comp.

Example 1:
Input: word = "abcde"
Output: "1a1b1c1d1e"
Explanation:
Initially, comp = "". Apply the operation 5 times, choosing "a", "b", "c", "d", and "e" as the prefix in each operation.
For each prefix, append "1" followed by the character to comp.

Example 2:
Input: word = "aaaaaaaaaaaaaabb"
Output: "9a5a2b"
Explanation:
Initially, comp = "". Apply the operation 3 times, choosing "aaaaaaaaa", "aaaaa", and "bb" as the prefix in each operation.
    For prefix "aaaaaaaaa", append "9" followed by "a" to comp.
    For prefix "aaaaa", append "5" followed by "a" to comp.
    For prefix "bb", append "2" followed by "b" to comp.

Constraints:
    1 <= word.length <= 2 * 10^5
    word consists only of lowercase English letters.
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{"1a1b1c1d1e", "abcde"},
				{"9a5a2b", "aaaaaaaaaaaaaabb"},
		});
	}


	public String compressedString(String word) {
		StringBuilder result = new StringBuilder();
		char prev = word.charAt(0), current;
		int count = 1;
		for (int i = 1, length = word.length(); i < length; )
			if ((current = word.charAt(i++)) != prev) {
				result.append(count).append(prev);
				prev = current;
				count = 1;
			} else if (++count > 9) {
				result.append(9).append(prev);
				count = 1;
			}
		return result.append(count).append(prev).toString();
	}
}
