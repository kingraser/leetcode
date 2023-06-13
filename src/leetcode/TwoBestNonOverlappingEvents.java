package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class TwoBestNonOverlappingEvents {
	/*
	You are given a 0-indexed 2D integer array of events where events[i] = [startTime_i, endTime_i, value_i]. The ith event starts at startTime_i and ends at endTime_i, and if you attend this event, you will receive a value of value_i. You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.
	Return this maximum sum.
	Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts and the other ends at the same time. More specifically, if you attend an event with end time t, the next event must start at or after t + 1.

	Example 1:
	Input: events = [[1,3,2],[4,5,2],[2,4,3]]
	Output: 4
	Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.

	Example 2:
	Example 1 Diagram
	Input: events = [[1,3,2],[4,5,2],[1,5,5]]
	Output: 5
	Explanation: Choose event 2 for a sum of 5.

	Example 3:
	Input: events = [[1,5,3],[1,5,1],[6,6,5]]
	Output: 8
	Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.

	Constraints:
	2 <= events.length <= 10^5
	events[i].length == 3
	1 <= startTime_i <= endTime_i <= 10^9
	1 <= value_i <= 10^6
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2000000, TestUtil.getArray("[[2,1000000000,1000000],[1,1,1000000]]")},
				{4, TestUtil.getArray("[[1,3,2],[4,5,2],[2,4,3]]")},
				{5, TestUtil.getArray("[[1,3,2],[4,5,2],[1,5,5]]")},
				{8, TestUtil.getArray("[[1,5,3],[1,5,1],[6,6,5]]")},
		});
	}

	public int maxTwoEvents(int[][] events) {
		int array[][] = new int[events.length << 1][3], size = 0, ans = 0, max = 0;
		for (int[] event : events) {
			array[size][0] = event[0];
			array[size][1] = 1; // 1 for start
			array[size][2] = array[++size][2] = event[2];
			array[size++][0] = event[1] + 1;
		}
		Arrays.sort(array, (a1, a2) -> a1[0] == a2[0] ? a1[1] - a2[1] : a1[0] - a2[0]);
		for (int[] tuple : array)
			if (tuple[1] == 1) ans = Math.max(ans, max + tuple[2]);
			else max = Math.max(max, tuple[2]);
		return ans;
	}
}
