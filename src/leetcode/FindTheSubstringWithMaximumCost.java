package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class FindTheSubstringWithMaximumCost {
	/*
	You are given a string s, a string chars of distinct characters and an integer array vals of the same length as chars.
	The cost of the substring is the sum of the values of each character in the substring. The cost of an empty string is considered 0.
	The value of the character is defined in the following way:
	If the character is not in the string chars, then its value is its corresponding position (1-indexed) in the alphabet.
	For example, the value of 'a' is 1, the value of 'b' is 2, and so on. The value of 'z' is 26.
	Otherwise, assuming i is the index where the character occurs in the string chars, then its value is vals[i].
	Return the maximum cost among all substrings of the string s.

	Example 1:
	Input: s = "adaa", chars = "d", vals = [-1000]
	Output: 2
	Explanation: The value of the characters "a" and "d" is 1 and -1000 respectively.
	The substring with the maximum cost is "aa" and its cost is 1 + 1 = 2.
	It can be proven that 2 is the maximum cost.

	Example 2:
	Input: s = "abc", chars = "abc", vals = [-1,-1,-1]
	Output: 0
	Explanation: The value of the characters "a", "b" and "c" is -1, -1, and -1 respectively.
	The substring with the maximum cost is the empty substring "" and its cost is 0.
	It can be proven that 0 is the maximum cost.

	Constraints:
	1 <= s.length <= 10^5
	s consist of lowercase English letters.
	1 <= chars.length <= 26
	chars consist of distinct lowercase English letters.
	vals.length == chars.length
	-1000 <= vals[i] <= 1000
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, "adaa", "d", new int[]{-1000}},
				{0, "abc", "abc", new int[]{-1, -1, -1}},
		});
	}

	public int maximumCostSubstring(String s, String chars, int[] vals) {
		int result = 0, values[] = new int[128];
		for (int i = 'a', value = 0; i <= 'z'; ) values[i++] = ++value;
		for (int i = 0, length = chars.length(); i < length; ) values[chars.charAt(i)] = vals[i++];
		for (int i = 0, length = s.length(), c, temp = 0; i < length; result = Math.max(result, temp))
			temp = Math.max(0, temp + values[s.charAt(i++)]);
		return result;
	}
}
