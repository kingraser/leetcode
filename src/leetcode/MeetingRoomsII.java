package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import leetcode.common.Interval;

public class MeetingRoomsII {

  /*
  Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
  find the minimum number of conference rooms required.
  
  For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
  */

  @Test
  public void test() {
    assertEquals(2, minMeetingRooms(new Interval[] { new Interval(0, 30), new Interval(5, 10), new Interval(15, 20) }));
  }

  public int minMeetingRooms(Interval[] intervals) {
    int result = 0, end = Integer.MIN_VALUE;
    Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);
    for (int i = 0; i < intervals.length; end = Math.max(end, intervals[i++].end))
      if (intervals[i].start < end) result++;
    return result;
  }

}
