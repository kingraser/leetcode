package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class DetermineIfTwoStringsAreClose {
/*
Two strings are considered close if you can attain one from the other using the following operations:
    Operation 1: Swap any two existing characters.
        For example, abcde -> aecdb
    Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
        For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
You can use the operations on either string as many times as necessary.
Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.

Example 1:
Input: word1 = "abc", word2 = "bca"
Output: true
Explanation: You can attain word2 from word1 in 2 operations.
Apply Operation 1: "abc" -> "acb"
Apply Operation 1: "acb" -> "bca"

Example 2:
Input: word1 = "a", word2 = "aa"
Output: false
Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.

Example 3:
Input: word1 = "cabbba", word2 = "abbccc"
Output: true
Explanation: You can attain word2 from word1 in 3 operations.
Apply Operation 1: "cabbba" -> "caabbb"
Apply Operation 2: "caabbb" -> "baaccc"
Apply Operation 2: "baaccc" -> "abbccc"

Constraints:
    1 <= word1.length, word2.length <= 10^5
    word1 and word2 contain only lowercase English letters.
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{true, "abc", "bca"},
				{false, "a", "aa"},
				{true, "cabbba", "abbccc"},
				{false, "uau", "ssx"}
		});
	}

	public boolean closeStrings(String word1, String word2) {
		int length = word1.length(), count1[] = new int['z' + 1], count2[] = new int[count1.length], maxFreq = 0, freq[], count = 0;
		if (length != word2.length()) return false;
		for (byte b : word1.getBytes()) count1[b]++;
		for (byte b : word2.getBytes()) count2[b]++;
		for (int i = 'a'; i <= 'z'; i++) maxFreq = Math.max(maxFreq, Math.max(count1[i], count2[i]));
		freq = new int[maxFreq + 1];
		for (int i = 'a'; i <= 'z'; i++) {
			if (count1[i] == 0 ^ count2[i] == 0) return false;
			if (count1[i] == 0) continue;
			if (freq[count1[i]]++ == 0) count++;
			else if (freq[count1[i]] == 0) count--;
			if (freq[count2[i]]-- == 0) count++;
			else if (freq[count2[i]] == 0) count--;
		}
		return count == 0;
	}
}
