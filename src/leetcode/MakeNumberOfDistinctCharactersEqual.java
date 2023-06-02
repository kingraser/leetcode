package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MakeNumberOfDistinctCharactersEqual {
	/*
	You are given two 0-indexed strings word1 and word2.
	A move consists of choosing two indices i and j such that 0 <= i < word1.length and 0 <= j < word2.length and swapping word1[i] with word2[j].
	Return true if it is possible to get the number of distinct characters in word1 and word2 to be equal with exactly one move. Return false otherwise.

	Example 1:
	Input: word1 = "ac", word2 = "b"
	Output: false
	Explanation: Any pair of swaps would yield two distinct characters in the first string, and one in the second string.

	Example 2:
	Input: word1 = "abcc", word2 = "aab"
	Output: true
	Explanation: We swap index 2 of the first string with index 0 of the second string. The resulting strings are word1 = "abac" and word2 = "cab", which both have 3 distinct characters.

	Example 3:
	Input: word1 = "abcde", word2 = "fghij"
	Output: true
	Explanation: Both resulting strings will have 5 distinct characters, regardless of which indices we swap.

	Constraints:
	1 <= word1.length, word2.length <= 10^5
	word1 and word2 consist of only lowercase English letters.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{false, "aa", "ab"},
				{false, "ac", "b"},
				{true, "abcc", "aab"},
				{true, "abcde", "fghij"},
		});
	}

	public boolean isItPossible(String word1, String word2) {
		int count1 = 0, freq1[] = new int[128], count2 = 0, freq2[] = new int[128];
		for (char c : word1.toCharArray()) freq1[c]++;
		for (char c : word2.toCharArray()) freq2[c]++;
		for (int c = 'a'; c <= 'z'; c++) {
			if (freq1[c] > 0) count1++;
			if (freq2[c] > 0) count2++;
		}
		if (Math.abs(count1 - count2) > 2) return false;
		for (int c1 = 'a'; c1 <= 'z'; c1++)
			if (freq1[c1] > 0)
				for (int c2 = 'a', count11 = count1 + (freq1[c1] == 1 ? -1 : 0), count22 = count2 + (freq2[c1] == 0 ? 1 : 0); c2 <= 'z'; c2++)
					if (freq2[c2] == 0) continue;
					else if (c1 == c2) {
						if (count1 == count2) return true;
						continue;
					} else if (count11 + (freq1[c2] == 0 ? 1 : 0) == count22 + (freq2[c2] == 1 ? -1 : 0))
						return true;
		return false;
	}
}
