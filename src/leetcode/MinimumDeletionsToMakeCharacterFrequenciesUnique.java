package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class MinimumDeletionsToMakeCharacterFrequenciesUnique {
/*
A string s is called good if there are no two different characters in s that have the same frequency.
Given a string s, return the minimum number of characters you need to delete to make s good.
The frequency of a character in a string is the number of times it appears in the string. For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.

Example 1:
Input: s = "aab"
Output: 0
Explanation: s is already good.

Example 2:
Input: s = "aaabbbcc"
Output: 2
Explanation: You can delete two 'b's resulting in the good string "aaabcc".
Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".

Example 3:
Input: s = "ceabaacb"
Output: 2
Explanation: You can delete both 'c's resulting in the good string "eabaab".
Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).

Constraints:
    1 <= s.length <= 10^5
    s contains only lowercase English letters.
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{5, "beaddedbacdcd"},
				{276, "abcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwz"},
				{2, "bbcebab"},
				{3, "abcabc"},
				{0, "aab"},
				{2, "aaabbbcc"},
				{2, "ceabaacb"},
		});
	}

	public int minDeletions(String s) {
		//map 0 for freq; 1 for count
		int result = 0, freq[] = new int[128], map[][] = new int[27][2], mapSize = 0;
		for (byte c : s.getBytes()) freq[c]++;
		Arrays.sort(freq, 'a', 'z' + 1);
		for (int i = 'z', prev = s.length() + 1; i >= 'a' && freq[i] > 0; i--)
			if (freq[i] != prev) {
				map[mapSize][0] = prev = freq[i];
				map[mapSize++][1]++;
			} else map[mapSize - 1][1]++;
		for (int i = 0, gap; map[i][0] > 0; i++)
			if (--map[i][1] == 0) continue;
			else if ((gap = map[i][0] - map[i + 1][0] - 1) < map[i][1]) {
				result += getSum(gap) + (map[i][1] -= gap) * (gap + 1);
				map[i + 1][1] += map[i][1];
			} else result += getSum(map[i][1]);
		return result;
	}

	int getSum(int n) {return (n * ++n) >> 1;}
}
