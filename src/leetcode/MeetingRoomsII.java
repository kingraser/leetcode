package leetcode;

import leetcode.common.Interval;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class MeetingRoomsII {

  /*
  Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
  find the minimum number of conference rooms required.  
  For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
  */

	@Test
	public void test() {
		assertEquals(2, minMeetingRooms(new Interval[]{new Interval(0, 30), new Interval(5, 10), new Interval(15, 20)}));
	}

	public int minMeetingRooms(Interval[] intervals) {
		int result = 0, end = Integer.MIN_VALUE;
		Arrays.sort(intervals, Comparator.comparingInt(i -> i.start));
		for (int i = 0; i < intervals.length; i++) {
			if (intervals[i].start < end) result++;
			end = Math.max(end, intervals[i].end);
		}
		return result;
	}

}
