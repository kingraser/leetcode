package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MinimumSwapsToMakeStringsEqual {
	/*
	You are given two strings s1 and s2 of equal length consisting of letters "x" and "y" only. Your task is to make these two strings equal to each other. You can swap any two characters that belong to different strings, which means: swap s1[i] and s2[j].
	Return the minimum number of swaps required to make s1 and s2 equal, or return -1 if it is impossible to do so.

	Example 1:
	Input: s1 = "xx", s2 = "yy"
	Output: 1
	Explanation: Swap s1[0] and s2[1], s1 = "yx", s2 = "yx".

	Example 2:
	Input: s1 = "xy", s2 = "yx"
	Output: 2
	Explanation: Swap s1[0] and s2[0], s1 = "yy", s2 = "xx".
	Swap s1[0] and s2[1], s1 = "xy", s2 = "xy".
	Note that you cannot swap s1[0] and s1[1] to make s1 equal to "yx", cause we can only swap chars in different strings.

	Example 3:
	Input: s1 = "xx", s2 = "xy"
	Output: -1

	Constraints:
	1 <= s1.length, s2.length <= 1000
	s1.length == s2.length
	s1, s2 only contain 'x' or 'y'.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{1, "xx", "yy"},
				{2, "xy", "yx"},
				{-1, "xx", "xy"}
		});
	}

	public int minimumSwap(String s1, String s2) {
		int xy = 0, yx = 0;
		for (int i = 0, length = s1.length(), c; i < length; i++)
			if ((c = s1.charAt(i)) == s2.charAt(i)) continue;
			else if (c == 'x') xy++;
			else yx++;
		return (xy + yx & 1) == 1 ? -1 : (xy >> 1) + (yx >> 1) + ((xy & 1) == 1 ? 2 : 0);
	}
}
