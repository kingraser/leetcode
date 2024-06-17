package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountPairsThatFormACompleteDayI {
/*
Given an integer array hours representing times in hours, return an integer denoting the number of pairs i, j where i < j and hours[i] + hours[j] forms a complete day.
A complete day is defined as a time duration that is an exact multiple of 24 hours.
For example, 1 day is 24 hours, 2 days is 48 hours, 3 days is 72 hours, and so on.

Example 1:
Input: hours = [12,12,30,24,24]
Output: 2
Explanation:
The pairs of indices that form a complete day are (0, 1) and (3, 4).

Example 2:
Input: hours = [72,48,24,3]
Output: 3
Explanation:
The pairs of indices that form a complete day are (0, 1), (0, 2), and (1, 2).

Constraints:
    1 <= hours.length <= 100
    1 <= hours[i] <= 10^9
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, new int[]{12, 12, 30, 24, 24}},
				{3, new int[]{72, 48, 24, 3}},
				{0, new int[]{11, 11, 24}},
				{2, new int[]{11, 22, 2, 25, 19, 2}}
		});
	}

	public int countCompleteDayPairs(int[] hours) {
		return (int) CountPairsThatFormACompleteDayII.countCompleteDayPairs(hours);
	}
}
