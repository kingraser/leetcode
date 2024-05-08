package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

import static leetcode.util.MathUtil.gcd;

/**
 * @author Wit
 */
public class MinimumLengthOfAnagramConcatenation {
/*
You are given a string s, which is known to be a concatenation of anagrams of some string t.
Return the minimum possible length of the string t.
An anagram is formed by rearranging the letters of a string. For example, "aab", "aba", and, "baa" are anagrams of "aab".

Example 1:
Input: s = "abba"
Output: 2
Explanation:
One possible string t could be "ba".

Example 2:
Input: s = "cdef"
Output: 4
Explanation:
One possible string t could be "cdef", notice that t can be equal to s.

Constraints:
    1 <= s.length <= 10^5
    s consist only of lowercase English letters.
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{4, "aabbabab"},
				{2, "abba"},
				{4, "cdef"},
		});
	}

	static final int ALPHABET_SIZE = 26;

	public int minAnagramLength(String s) {
		int length = s.length(), greatestAnagramCount = 0, letterFreq[] = new int[ALPHABET_SIZE];
		for (int i = 0; i < length; ) letterFreq[s.charAt(i++) - 'a']++;
		for (int frequency : letterFreq) greatestAnagramCount = gcd(greatestAnagramCount, frequency);
		for (int divisor = 1, half = greatestAnagramCount >> 1, anagramCount, anagramLength; divisor <= half; divisor++)
			if (greatestAnagramCount % divisor == 0 && isValid(s, letterFreq, (anagramCount = greatestAnagramCount / divisor), anagramLength = length / anagramCount))
				return anagramLength;
		return length;
	}


	boolean isValid(String s, int[] totalFreq, int anagramCount, int anagramLength) {
		for (int i = 0; i < s.length(); ) {
			int[] anagramFreq = new int[totalFreq.length];
			for (int j = anagramLength; j-- > 0; ) anagramFreq[s.charAt(i++) - 'a'] += anagramCount;
			if (!Arrays.equals(totalFreq, anagramFreq)) return false;
		}
		return true;
	}
}
