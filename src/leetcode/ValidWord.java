package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class ValidWord {
/*
A word is considered valid if:
    It contains a minimum of 3 characters.
    It consists of the digits 0-9, and the uppercase and lowercase English letters. (Not necessary to have all of them.)
    It includes at least one vowel.
    It includes at least one consonant.
You are given a string word.
Return true if word is valid, otherwise, return false.
Notes:
    'a', 'e', 'i', 'o', 'u', and their uppercases are vowels.
    A consonant is an English letter that is not a vowel.

Example 1:
Input: word = "234Adas"
Output: true
Explanation:
This word satisfies the conditions.

Example 2:
Input: word = "b3"
Output: false
Explanation:
The length of this word is fewer than 3, and does not have a vowel.

Example 3:
Input: word = "a3$e"
Output: false
Explanation:
This word contains a '$' character and does not have a consonant.

Constraints:
    1 <= word.length <= 20
    word consists of English uppercase and lowercase letters, digits, '@', '#', and '$'.
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{true, "234Adas"},
				{false, "b3"},
				{false, "a3$e"},
		});
	}

	public boolean isValid(String word) {
		if (word.length() < 3) return false;
		boolean hasVowel = false, hasConsonant = false;
		for (int i = 0, c; i < word.length(); )
			if (((c = word.charAt(i++)) >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				if (!hasVowel) hasConsonant |= !(hasVowel = isVowel(c));
				else if (!hasConsonant) hasConsonant = !isVowel(c);
			} else if (c < '0' || c > '9') return false;
		return hasVowel && hasConsonant;
	}

	boolean isVowel(int c) {
		if (c < 'a') c += 'a' - 'A'; // upper case -> lower case
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

}
