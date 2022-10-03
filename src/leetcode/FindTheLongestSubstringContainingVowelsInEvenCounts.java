package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class FindTheLongestSubstringContainingVowelsInEvenCounts {
	/*
	Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.

	Example 1:
	Input: s = "eleetminicoworoep"
	Output: 13
	Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.

	Example 2:
	Input: s = "leetcodeisgreat"
	Output: 5
	Explanation: The longest substring is "leetc" which contains two e's.

	Example 3:
	Input: s = "bcbcbc"
	Output: 6
	Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.

	Constraints:
	1 <= s.length <= 5 x 10^5
	s contains only lowercase English letters.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{13, "eleetminicoworoep"},
				{5, "leetcodeisgreat"},
				{6, "bcbcbc"},
		});
	}

	//flag & 1 = (the count of a) % 2
	//flag & 2 = (the count of e) % 2
	//flag & 4 = (the count of i) % 2
	//flag & 8 = (the count of o) % 2
	//flag & 16 = (the count of u) % 2
	public int findTheLongestSubstring(String s) {
		int result = 0, length = s.length(), firstSeen[] = new int[32];
		Arrays.fill(firstSeen, -1);
		for (int i = 0, flag = 0; i < length; result = Math.max(result, i++ - firstSeen[flag]))
			if ((flag ^= getMask(s.charAt(i))) != 0 && firstSeen[flag] == -1) firstSeen[flag] = i;
		return result;
	}

	int getMask(char c) {return c == 'a' ? 1 : c == 'e' ? 2 : c == 'i' ? 4 : c == 'o' ? 8 : c == 'u' ? 16 : 0;}
}
