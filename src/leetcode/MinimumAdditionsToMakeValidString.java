package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MinimumAdditionsToMakeValidString {
	/*
	Given a string word to which you can insert letters "a", "b" or "c" anywhere and any number of times, return the minimum number of letters that must be inserted so that word becomes valid.
	A string is called valid if it can be formed by concatenating the string "abc" several times.

	Example 1:
	Input: word = "b"
	Output: 2
	Explanation: Insert the letter "a" right before "b", and the letter "c" right next to "a" to obtain the valid string "abc".

	Example 2:
	Input: word = "aaa"
	Output: 6
	Explanation: Insert letters "b" and "c" next to each "a" to obtain the valid string "abcabcabc".

	Example 3:
	Input: word = "abc"
	Output: 0
	Explanation: word is already valid. No modifications are needed.

	Constraints:
	1 <= word.length <= 50
	word consists of letters "a", "b" and "c" only.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, "b"},
				{6, "aaa"},
				{0, "abc"}
		});
	}

	public int addMinimum(String word) {
		int abcCount = 0, length = word.length();
		for (int i = 0, prev = 'c'; i < length; ) if (prev >= (prev = word.charAt(i++))) abcCount++;
		return abcCount * 3 - length;
	}
}
