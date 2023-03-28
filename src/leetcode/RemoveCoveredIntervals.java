package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class RemoveCoveredIntervals {
	/*
	Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri), remove all intervals that are covered by another interval in the list.
	The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.
	Return the number of remaining intervals.

	Example 1:
	Input: intervals = [[1,4],[3,6],[2,8]]
	Output: 2
	Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.

	Example 2:
	Input: intervals = [[1,4],[2,3]]
	Output: 1

	Constraints:
	1 <= intervals.length <= 1000
	intervals[i].length == 2
	0 <= li < ri <= 10^5
	All the given intervals are unique.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{1, TestUtil.getArray("[[1,2],[1,4],[3,4]]")},
				{2, TestUtil.getArray("[[1,4],[3,6],[2,8]]")},
				{1, TestUtil.getArray("[[1,4],[2,3]]")},
		});
	}

	public int removeCoveredIntervals(int[][] intervals) {
		int result = intervals.length;
		Arrays.sort(intervals, (i1, i2) -> i1[0] == i2[0] ? i2[1] - i1[1] : i1[0] - i2[0]);
		for (int i = 1, rightMost = intervals[0][1]; i < intervals.length; i++)
			if (intervals[i][1] <= rightMost) result--;
			else rightMost = intervals[i][1];
		return result;
	}
}
