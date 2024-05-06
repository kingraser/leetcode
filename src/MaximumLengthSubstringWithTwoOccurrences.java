import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MaximumLengthSubstringWithTwoOccurrences {
	/*
	Given a string s, return the maximum length of a substring such that it contains at most two occurrences of each character.

	Example 1:
	Input: s = "bcbbbcba"
	Output: 4
	Explanation:
	The following substring has a length of 4 and contains at most two occurrences of each character: "bcbbbcba".

	Example 2:
	Input: s = "aaaa"
	Output: 2
	Explanation:
	The following substring has a length of 2 and contains at most two occurrences of each character: "aaaa".

	Constraints:
		2 <= s.length <= 100
		s consists only of lowercase English letters.
	*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[]{4, 2}, new Object[][]{{"bcbbbcba"}, {"aaaa"}});
	}

	public int maximumLengthSubstring(String s) {
		int result = 0, left = 0, right = left;
		char[] count = new char[128], chars = s.toCharArray();
		for (; right < chars.length; right++) {
			if (count[chars[right]]++ < 2) continue;
			result = Integer.max(result, right - left);
			while (count[chars[right]] > 2) count[chars[left++]]--;
		}
		return Integer.max(result, right - left);
	}
}
