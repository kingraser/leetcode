package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CheckIfWordIsValidAfterSubstitutions {
	/*
	Given a string s, determine if it is valid.
	A string s is valid if, starting with an empty string t = "", you can transform t into s after performing the following operation any number of times:
	Insert string "abc" into any position in t. More formally, t becomes tleft + "abc" + tright, where t == tleft + tright. Note that tleft and tright may be empty.
	Return true if s is a valid string, otherwise, return false.

	Example 1:
	Input: s = "aabcbc"
	Output: true
	Explanation:
	"" -> "abc" -> "aabcbc"
	Thus, "aabcbc" is valid.

	Example 2:
	Input: s = "abcabcababcc"
	Output: true
	Explanation:
	"" -> "abc" -> "abcabc" -> "abcabcabc" -> "abcabcababcc"
	Thus, "abcabcababcc" is valid.

	Example 3:
	Input: s = "abccba"
	Output: false
	Explanation: It is impossible to get "abccba" using the operation.

	Constraints:
	1 <= s.length <= 2 * 10^4
	s consists of letters 'a', 'b', and 'c'
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{false, "aabbcc"},
				{true, "aabcbc"},
				{true, "abcabcababcc"},
				{false, "abccba"}
		});
	}

	public boolean isValid(String s) {
		int length = s.length(), stackSize = 0, stack[] = new int[length];
		for (int i = 0, c; i < length; )
			if ((c = s.charAt(i++)) != 'c') stack[stackSize++] = c;
			else if (stackSize < 2 || stack[--stackSize] != 'b' || stack[--stackSize] != 'a') return false;
		return stackSize == 0;
	}
}
