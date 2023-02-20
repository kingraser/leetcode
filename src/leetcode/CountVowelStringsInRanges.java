package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountVowelStringsInRanges {
	/*
	You are given a 0-indexed array of strings words and a 2D array of integers queries.
	Each query queries[i] = [li, ri] asks us to find the number of strings present in the range li to ri (both inclusive) of words that start and end with a vowel.
	Return an array ans of size queries.length, where ans[i] is the answer to the ith query.
	Note that the vowel letters are 'a', 'e', 'i', 'o', and 'u'.

	Example 1:
	Input: words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
	Output: [2,3,0]
	Explanation: The strings starting and ending with a vowel are "aba", "ece", "aa" and "e".
	The answer to the query [0,2] is 2 (strings "aba" and "ece").
	to query [1,4] is 3 (strings "ece", "aa", "e").
	to query [1,1] is 0.
	We return [2,3,0].

	Example 2:
	Input: words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
	Output: [3,2,1]
	Explanation: Every string satisfies the conditions, so we return [3,2,1].

	Constraints:
	1 <= words.length <= 10^5
	1 <= words[i].length <= 40
	words[i] consists only of lowercase English letters.
	sum(words[i].length) <= 3 * 10^5
	1 <= queries.length <= 10^5
	0 <= li <= ri < words.length
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{2, 3, 0}, new String[]{"aba", "bcb", "ece", "aa", "e"}, TestUtil.getArray("[[0,2],[1,4],[1,1]]")},
				{new int[]{3, 2, 1}, new String[]{"a", "e", "i"}, TestUtil.getArray("[[0,2],[0,1],[2,2]]")},
		});
	}

	public int[] vowelStrings(String[] words, int[][] queries) {
		int[] count = new int[words.length + 1], result = new int[queries.length];
		for (int i = 0; i < words.length; i++) count[i + 1] = isValid(words[i]) ? count[i] + 1 : count[i];
		for (int i = 0; i < queries.length; i++) result[i] = count[queries[i][1] + 1] - count[queries[i][0]];
		return result;
	}

	boolean isValid(String s) {
		return isVowel(s.charAt(0)) && (s.length() == 1 || isVowel(s.charAt(s.length() - 1)));
	}

	boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
}
