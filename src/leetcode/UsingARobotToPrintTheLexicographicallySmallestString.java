package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class UsingARobotToPrintTheLexicographicallySmallestString {
	/*
	You are given a string s and a robot that currently holds an empty string t. Apply one of the following operations until s and t are both empty:
	Remove the first character of a string s and give it to the robot. The robot will append this character to the string t.
	Remove the last character of a string t and give it to the robot. The robot will write this character on paper.
	Return the lexicographically smallest string that can be written on the paper.

	Example 1:
	Input: s = "zza"
	Output: "azz"
	Explanation: Let p denote the written string.
	Initially p="", s="zza", t="".
	Perform first operation three times p="", s="", t="zza".
	Perform second operation three times p="azz", s="", t="".

	Example 2:
	Input: s = "bac"
	Output: "abc"
	Explanation: Let p denote the written string.
	Perform first operation twice p="", s="c", t="ba".
	Perform second operation twice p="ab", s="c", t="".
	Perform first operation p="ab", s="", t="c".
	Perform second operation p="abc", s="", t="".

	Example 3:
	Input: s = "bdda"
	Output: "addb"
	Explanation: Let p denote the written string.
	Initially p="", s="bdda", t="".
	Perform first operation four times p="", s="", t="bdda".
	Perform second operation four times p="addb", s="", t="".

	Constraints:
	1 <= s.length <= 10^5
	s consists of only English lowercase letters.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{"azz", "zza"},
				{"abc", "bac"},
				{"addb", "bdda"},
		});
	}


	public String robotWithString(String s) {
		char low = 'a', chars[] = s.toCharArray(), result[] = new char[chars.length], stack[] = new char[chars.length];
		int top = -1, freq[] = new int[128], resIdx = 0;
		for (char c : chars) freq[c]++;
		for (char c : chars) {
			freq[stack[++top] = c]--;
			while (low <= 'z' && freq[low] == 0) low++;
			while (top != -1 && stack[top] <= low) result[resIdx++] = stack[top--];
		}
		return new String(result);
	}
}
