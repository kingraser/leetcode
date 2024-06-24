package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SumOfBeautyOfAllSubstrings {
	/*
	The beauty of a string is the difference in frequencies between the most frequent and least frequent characters.
		For example, the beauty of "abaacc" is 3 - 1 = 2.
	Given a string s, return the sum of beauty of all of its substrings.

	Example 1:
	Input: s = "aabcb"
	Output: 5
	Explanation: The substrings with non-zero beauty are ["aab","aabc","aabcb","abcb","bcb"], each with beauty equal to 1.

	Example 2:
	Input: s = "aabcbaa"
	Output: 17

	Constraints:
		1 <= s.length <= 500
		s consists of only lowercase English letters.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{5, "aabcb"},
				{17, "aabcbaa"},
		});
	}

	public int getBeauty(String s) {
		int result = 0, freq[] = new int[128];
		byte[] chars = s.getBytes();
		for (int left = 0, right, last = chars.length - 1, direction = 0; left < last; freq[chars[left++]]--, direction ^= 1)
			if (direction == 0) for (freq[chars[right = left]]++; right < last; result += getBeauty(freq)) freq[chars[++right]]++;
			else for (right = last; right > left; freq[chars[right--]]--) result += getBeauty(freq);
		return result;
	}

	int getBeauty(int[] charFreq) {
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		for (int i : charFreq)
			if (i != 0) {
				max = Math.max(max, i);
				min = Math.min(min, i);
			}
		return max - min;
	}
}
