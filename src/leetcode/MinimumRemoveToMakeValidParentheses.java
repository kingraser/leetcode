package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MinimumRemoveToMakeValidParentheses {
	/*
	Given a string s of '(' , ')' and lowercase English characters.
	Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
	Formally, a parentheses string is valid if and only if:
	It is the empty string, contains only lowercase characters, or
	It can be written as AB (A concatenated with B), where A and B are valid strings, or
	It can be written as (A), where A is a valid string.

	Example 1:
	Input: s = "lee(t(c)o)de)"
	Output: "lee(t(c)o)de"
	Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

	Example 2:
	Input: s = "a)b(c)d"
	Output: "ab(c)d"

	Example 3:
	Input: s = "))(("
	Output: ""
	Explanation: An empty string is also valid.

	Constraints:
	1 <= s.length <= 10^5
	s[i] is either'(' , ')', or lowercase English letter.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{"lee(t(c)o)de", "lee(t(c)o)de)"},
				{"ab(c)d", "a)b(c)d"},
				{"", "))(("},
		});
	}

	public String minRemoveToMakeValid(String s) {
		int length = s.length(), stackSize = 0, stack[] = new int[length], resultIndex = 0;
		for (int i = 0, c; i < length; )
			if ((c = s.charAt(i++)) == ')') {
				if (stackSize > 0 && stack[stackSize - 1] > 0) stackSize--;
				else stack[stackSize++] = -i;
			} else if (c == '(') stack[stackSize++] = i;
		char[] result = new char[length - stackSize];
		for (int i = 0, stackIndex = 0; i < length; i++)
			if (stackIndex >= stackSize || Math.abs(stack[stackIndex]) != i + 1) result[resultIndex++] = s.charAt(i);
			else stackIndex++;
		return new String(result);
	}
}
