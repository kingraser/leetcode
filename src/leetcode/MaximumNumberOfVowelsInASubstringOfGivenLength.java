package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MaximumNumberOfVowelsInASubstringOfGivenLength {
	/*
	Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
	Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

	Example 1:
	Input: s = "abciiidef", k = 3
	Output: 3
	Explanation: The substring "iii" contains 3 vowel letters.

	Example 2:
	Input: s = "aeiou", k = 2
	Output: 2
	Explanation: Any substring of length 2 contains 2 vowels.

	Example 3:
	Input: s = "leetcode", k = 3
	Output: 2
	Explanation: "lee", "eet" and "ode" contain 2 vowels.

	Constraints:
	1 <= s.length <= 10^5
	s consists of lowercase English letters.
	1 <= k <= s.length
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{1, "tryhard", 4},
				{3, "abciiidef", 3},
				{2, "aeiou", 2},
				{2, "leetcode", 3},
		});
	}

	public int maxVowels(String s, int k) {
		int result = 0, left = 0, right = 0, count = 0, counts[] = new int[128];
		"aeiou".chars().forEach(c -> counts[c]++);
		for (char[] chars = s.toCharArray(); right < chars.length; result = Math.max(result, count)) {
			count += counts[chars[right++]];
			if (right - left > k) count -= counts[chars[left++]];
		}
		return result;
	}

}
