package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class OptimalPartitionOfString {
	/*
	Given a string s, partition the string into one or more substrings such that the characters in each substring are unique. That is, no letter appears in a single substring more than once.
	Return the minimum number of substrings in such a partition.
	Note that each character should belong to exactly one substring in a partition.

	Example 1:
	Input: s = "abacaba"
	Output: 4
	Explanation:
	Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
	It can be shown that 4 is the minimum number of substrings needed.

	Example 2:
	Input: s = "ssssss"
	Output: 6
	Explanation:
	The only valid partition is ("s","s","s","s","s","s").

	Constraints:
	1 <= s.length <= 10^5
	s consists of only English lowercase letters.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{4, "abacaba" },
				{6, "ssssss" }
		});
	}

	public int partitionString(String s) {
		int result = 0;
		for (int i = 0, length = s.length(), flag, map = 0; i < length; map ^= flag)
			if ((map & (flag = 1 << (s.charAt(i++) - 'a'))) != 0) {
				result++;
				map = 0;
			}
		return result + 1;
	}
}
