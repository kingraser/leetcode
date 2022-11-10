package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class OddStringDifference {
	/*
	You are given an array of equal-length strings words. Assume that the length of each string is n.
	Each string words[i] can be converted into a difference integer array difference[i] of length n - 1 where difference[i][j] = words[i][j+1] - words[i][j] where 0 <= j <= n - 2. Note that the difference between two letters is the difference between their positions in the alphabet i.e. the position of 'a' is 0, 'b' is 1, and 'z' is 25.
	For example, for the string "acb", the difference integer array is [2 - 0, 1 - 2] = [2, -1].
	All the strings in words have the same difference integer array, except one. You should find that string.
	Return the string in words that has different difference integer array.

	Example 1:
	Input: words = ["adc","wzy","abc"]
	Output: "abc"
	Explanation:
	- The difference integer array of "adc" is [3 - 0, 2 - 3] = [3, -1].
	- The difference integer array of "wzy" is [25 - 22, 24 - 25]= [3, -1].
	- The difference integer array of "abc" is [1 - 0, 2 - 1] = [1, 1].
	The odd array out is [1, 1], so we return the corresponding string, "abc".

	Example 2:
	Input: words = ["aaa","bob","ccc","ddd"]
	Output: "bob"
	Explanation: All the integer arrays are [0, 0] except for "bob", which corresponds to [13, -13].

	Constraints:
	3 <= words.length <= 100
	n == words[i].length
	2 <= n <= 20
	words[i] consists of lowercase English letters.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{"abc", new String[]{"adc", "wzy", "abc"}},
				{"bob", new String[]{"aaa", "bob", "ccc", "ddd"}},
				{"bob", new String[]{"bob", "aaa", "ccc", "ddd"}}
		});
	}

	public String oddString(String[] words) {
		if (!isSame(words[0], words[1])) return words[isSame(words[1], words[2]) ? 0 : 1];
		for (int i = 2; i < words.length; i++) if (!isSame(words[i], words[0])) return words[i];
		return null;
	}

	boolean isSame(String a, String b) {
		for (int i = 1, length = a.length(); i < length; i++)
			if (a.charAt(i) - a.charAt(i - 1) != b.charAt(i) - b.charAt(i - 1)) return false;
		return true;
	}

}
