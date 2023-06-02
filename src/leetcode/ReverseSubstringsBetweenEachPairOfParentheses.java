package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class ReverseSubstringsBetweenEachPairOfParentheses {
	/*
	You are given a string s that consists of lower case English letters and brackets.
	Reverse the strings in each pair of matching parentheses, starting from the innermost one.
	Your result should not contain any brackets.

	Example 1:
	Input: s = "(abcd)"
	Output: "dcba"

	Example 2:
	Input: s = "(u(love)i)"
	Output: "iloveu"
	Explanation: The substring "love" is reversed first, then the whole string is reversed.

	Example 3:
	Input: s = "(ed(et(oc))el)"
	Output: "leetcode"
	Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.

	Constraints:
	1 <= s.length <= 2000
	s only contains lower case English characters and parentheses.
	It is guaranteed that all parentheses are balanced.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{"dcba", "(abcd)"},
				{"iloveu", "(u(love)i)"},
				{"leetcode", "(ed(et(oc))el)"},
		});
	}

	public String reverseParentheses(String s) {
		int length = s.length(), stack[] = new int[length], stackSize = 0, pair[] = new int[length], resultSize = 0;
		char[] chars = s.toCharArray(), result = new char[length];
		for (int i = 0; i < length; i++)
			if (chars[i] == '(') stack[stackSize++] = i;
			else if (chars[i] == ')') pair[pair[i] = stack[--stackSize]] = i;
		for (int i = 0, d = 1; i < length; i += d)
			if (chars[i] == '(' || chars[i] == ')') {
				i = pair[i];
				d = -d;
			} else result[resultSize++] = chars[i];
		return new String(result, 0, resultSize);
	}
}
