package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class LexicographicallySmallestStringAfterASwap {
	/*
	Given a string s containing only digits, return the
	lexicographically smallest string
	that can be obtained after swapping adjacent digits in s with the same parity at most once.
	Digits have the same parity if both are odd or both are even. For example, 5 and 9, as well as 2 and 4, have the same parity, while 6 and 9 do not.

	Example 1:
	Input: s = "45320"
	Output: "43520"
	Explanation:
	s[1] == '5' and s[2] == '3' both have the same parity, and swapping them results in the lexicographically smallest string.

	Example 2:
	Input: s = "001"
	Output: "001"
	Explanation:
	There is no need to perform a swap because s is already the lexicographically smallest.

	Constraints:
		2 <= s.length <= 100
		s consists only of digits.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{"43520", "45320"},
				{"001", "001"},
				{"13", "13"},
		});
	}

	public String getSmallestString(String s) {
		byte[] result = s.getBytes();
		for (int i = 0, last = result.length - 1; i < last; )
			if (((result[i] ^ result[++i]) & 1) == 0 && result[i] < result[i - 1]) {
				//swap
				result[i - 1] ^= result[i];
				result[i] ^= result[i - 1];
				result[i - 1] ^= result[i];
				break;
			}
		return new String(result);
	}
}
