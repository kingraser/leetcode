package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class LongestIdealSubsequence {
	/*
	You are given a string s consisting of lowercase letters and an integer k. We call a string t ideal if the following conditions are satisfied:
	t is a subsequence of the string s.
	The absolute difference in the alphabet order of every two adjacent letters in t is less than or equal to k.
	Return the length of the longest ideal string.
	A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
	Note that the alphabet order is not cyclic. For example, the absolute difference in the alphabet order of 'a' and 'z' is 25, not 1.

	Example 1:
	Input: s = "acfgbd", k = 2
	Output: 4
	Explanation: The longest ideal string is "acbd". The length of this string is 4, so 4 is returned.
	Note that "acfgbd" is not ideal because 'c' and 'f' have a difference of 3 in alphabet order.

	Example 2:
	Input: s = "abcd", k = 3
	Output: 4
	Explanation: The longest ideal string is "abcd". The length of this string is 4, so 4 is returned.

	Constraints:
	1 <= s.length <= 10^5
	0 <= k <= 25
	s consists of lowercase English letters.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{7, "lkpkxcigcs", 6},
				{4, "acfgbd", 2},
				{4, "abcd", 3}
		});
	}

	//dp[c] means the length of the longest ideal subsequence ending with character c.
	//Iterate the character i in string s,
	//c can be the next character for string ending from i - k to i + k.
	//So that dp[i] = max(dp[i-k], dp[i-k+1] ... , dp[i+k]) + 1.
	//return the max(dp) as result.
	public int longestIdealString(String s, int k) {
		int result = 0, length = s.length(), dp[] = new int[128];
		for (int i = 0, c, j, max; i < length; result = Math.max(result, ++dp[c]))
			for (c = s.charAt(i++), j = Math.max('a', c - k), max = Math.min('z', c + k); j <= max; )
				dp[c] = Math.max(dp[c], dp[j++]);
		return result;
	}
}
