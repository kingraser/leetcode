package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MinimumSubstringPartitionOfEqualCharacterFrequency {
/*
Given a string s, you need to partition it into one or more balanced
substrings
. For example, if s == "ababcc" then ("abab", "c", "c"), ("ab", "abc", "c"), and ("ababcc") are all valid partitions, but ("a", "bab", "cc"), ("aba", "bc", "c"), and ("ab", "abcc") are not. The unbalanced substrings are bolded.
Return the minimum number of substrings that you can partition s into.
Note: A balanced string is a string where each character in the string occurs the same number of times.

Example 1:
Input: s = "fabccddg"
Output: 3
Explanation:
We can partition the string s into 3 substrings in one of the following ways: ("fab, "ccdd", "g"), or ("fabc", "cd", "dg").

Example 2:
Input: s = "abababaccddb"
Output: 2
Explanation:
We can partition the string s into 2 substrings like so: ("abab", "abaccddb").

Constraints:
    1 <= s.length <= 1000
    s consists only of English lowercase letters.
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, "fabccddg"},
				{2, "abababaccddb"},
		});
	}

	public int minimumSubstringsInPartition(String s) {
		char[] chars = s.toCharArray();
		int length = chars.length, dp[] = new int[length + 1], count[] = new int[128];
		for (int i = 1; i <= length; ) dp[i] = i++;
		for (int right = 1; right <= length; right++, clear(count))
			for (int left = right - 1, distinct = 0, maxCount = 0; left >= 0; left--) {
				if (count[chars[left]]++ == 0) distinct++;
				if ((maxCount = Math.max(maxCount, count[chars[left]])) * distinct != right - left) continue;
				dp[right] = Math.min(dp[right], dp[left] + 1);
			}
		return dp[length];
	}

	void clear(int[] count) {for (int i = 'a'; i <= 'z'; ) count[i++] = 0;}
}
