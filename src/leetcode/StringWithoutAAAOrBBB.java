package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class StringWithoutAAAOrBBB {
	/*
	Given two integers a and b, return any string s such that:
	s has length a + b and contains exactly a 'a' letters, and exactly b 'b' letters,
	The substring 'aaa' does not occur in s, and
	The substring 'bbb' does not occur in s.

	Example 1:
	Input: a = 1, b = 2
	Output: "abb"
	Explanation: "abb", "bab" and "bba" are all correct answers.

	Example 2:
	Input: a = 4, b = 1
	Output: "aabaa"

	Constraints:
	0 <= a, b <= 100
	It is guaranteed such an s exists for the given a and b.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{"bbaba", 2, 3},
				{"bba", 1, 2},
				{"aabaa", 4, 1},
		});
	}

	public String strWithout3a3b(int a, int b) {return strWithout3a3b(a, b, 'a', 'b');}

	String strWithout3a3b(int a, int b, char aChar, char bChar) {
		if (a < b) return strWithout3a3b(b, a, bChar, aChar);
		char result[] = new char[a + b];
		int idx = 0, times = Math.min(a - b, b);
		b -= times;
		while (times-- > 0) {
			result[idx++] = aChar;
			result[idx++] = aChar;
			result[idx++] = bChar;
		}
		while (b-- > 0) {
			result[idx++] = aChar;
			result[idx++] = bChar;
		}
		while (idx < result.length) result[idx++] = aChar;
		return new String(result);
	}
}
