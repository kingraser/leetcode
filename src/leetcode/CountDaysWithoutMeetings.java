package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Wit
 */
public class CountDaysWithoutMeetings {
/*
You are given a positive integer days representing the total number of days an employee is available for work (starting from day 1). You are also given a 2D array meetings of size n where, meetings[i] = [start_i, end_i] represents the starting and ending days of meeting i (inclusive).
Return the count of days when the employee is available for work but no meetings are scheduled.
Note: The meetings may overlap.

Example 1:
Input: days = 10, meetings = [[5,7],[1,3],[9,10]]
Output: 2
Explanation:
There is no meeting scheduled on the 4th and 8th days.

Example 2:
Input: days = 5, meetings = [[2,4],[1,3]]
Output: 1
Explanation:
There is no meeting scheduled on the 5th day.

Example 3:
Input: days = 6, meetings = [[1,6]]
Output: 0
Explanation:
Meetings are scheduled for all working days.

Constraints:
    1 <= days <= 10^9
    1 <= meetings.length <= 10^5
    meetings[i].length == 2
    1 <= meetings[i][0] <= meetings[i][1] <= days
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, 10, TestUtil.getArray("[[5,7],[1,3],[9,10]]")},
				{1, 5, TestUtil.getArray("[[2,4],[1,3]]")},
				{0, 6, TestUtil.getArray("[[1,6]]")},
		});
	}

	public int countDays(int days, int[][] meetings) {
		Arrays.sort(meetings, Comparator.comparingInt(i -> i[0]));
		int end = 0;
		for (int[] meeting : meetings)
			if (meeting[1] >= end) days -= -Math.max(end, meeting[0]) + (end = ++meeting[1]);
		return days;
	}
}
