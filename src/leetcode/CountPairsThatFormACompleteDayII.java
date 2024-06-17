package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountPairsThatFormACompleteDayII {
/*
Given an integer array hours representing times in hours, return an integer denoting the number of pairs i, j where i < j and hours[i] + hours[j] forms a complete day.
A complete day is defined as a time duration that is an exact multiple of 24 hours.
For example, 1 day is 24 hours, 2 days is 48 hours, 3 days is 72 hours, and so on.

Example 1:
Input: hours = [12,12,30,24,24]
Output: 2
Explanation: The pairs of indices that form a complete day are (0, 1) and (3, 4).

Example 2:
Input: hours = [72,48,24,3]
Output: 3
Explanation: The pairs of indices that form a complete day are (0, 1), (0, 2), and (1, 2).

Constraints:
    1 <= hours.length <= 5 * 10^5
    1 <= hours[i] <= 10^9
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2L, new int[]{12, 12, 30, 24, 24}},
				{3L, new int[]{72, 48, 24, 3}},
				{1L, new int[]{11, 11, 24}},
				{1L, new int[]{13, 11}}
		});
	}

	public static long countCompleteDayPairs(int[] hours) {
		long result = 0, map[] = new long[24];
		for (int hour : hours) map[hour % 24]++;
		for (int left = 1, right = 23; left < right; left++, right--)
			if (map[left] > 0 && map[right] > 0) result += map[left] * map[right];
		if (map[0] > 0) result += (map[0] * (map[0] - 1)) >> 1;
		if (map[12] > 0) result += (map[12] * (map[12] - 1)) >> 1;
		return result;
	}
}
