package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class RemoveLetterToEqualizeFrequency {
	/*
	You are given a 0-indexed string word, consisting of lowercase English letters. You need to select one index and remove the letter at that index from word so that the frequency of every letter present in word is equal.
	Return true if it is possible to remove one letter so that the frequency of all letters in word are equal, and false otherwise.
	Note:
	The frequency of a letter x is the number of times it occurs in the string.
	You must remove exactly one letter and cannot choose to do nothing.

	Example 1:
	Input: word = "abcc"
	Output: true
	Explanation: Select index 3 and delete it: word becomes "abc" and each character has a frequency of 1.

	Example 2:
	Input: word = "aazz"
	Output: false
	Explanation: We must delete a character, so either the frequency of "a" is 1 and the frequency of "z" is 2, or vice versa. It is impossible to make all present letters have equal frequency.

	Constraints:
	2 <= word.length <= 100
	word consists of lowercase English letters only.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{true, "bbbaccc"},
				{true, "zz"},
				{false, "cbccca"},
				{true, "abc"},
				{true, "abcc"},
				{false, "aazz"}
		});
	}

	public boolean equalFrequency(String word) {
		// map[0][0] for one frequency, map[0][1] for the count of letters with the frequency
		// map[1][0] for the other frequency, map[1][1] for the count of letters with the frequency
		// It is obvious that the result will be false if there are more than 2 distinct frequencies
		int[] frequencies = new int[128], map[] = new int[2][2];
		for (char c : word.toCharArray()) frequencies[c]++;
		for (int c = 'a', frequency; c <= 'z'; )
			if ((frequency = frequencies[c++]) == 0) continue;
			else if (frequency == (map[0][0] == 0 ? map[0][0] = frequency : map[0][0])) map[0][1]++;
			else if (frequency == (map[1][0] == 0 ? map[1][0] = frequency : map[1][0])) map[1][1]++;
			else return false;
		// Make sure map[0][0] is smaller than map[1][0], just for convenience.
		if (map[0][0] > map[1][0]) {
			int[] temp = map[0];
			map[0] = map[1];
			map[1] = temp;
		}
		// It is obvious that the result will be false if both of the counts are greater than 1
		if (map[0][1] > 1 && map[1][1] > 1) return false;
		// If there is only one frequency, the count of the letters with the frequency or the frequency has to be 1
		return (map[1][0] == 1 || (map[0][1] == 0 && map[1][1] == 1)) ||
				// In the 2 frequencies case, now we have 2 options
				// 1. one is to operate the letters with the min_frequency
				// 2. the other is to operate the letters with the max_frequency
				// In the first option, both of the count of letters with the min_frequency and the min_frequency have to be 1
				(map[0][1] == 1 && map[0][0] == 1)
				// In the second option, the difference of two frequencies has to be 1 and the count of letters with max_frequency has to be 1
				|| (map[1][1] == 1 && map[1][0] - map[0][0] == 1);
	}
}
