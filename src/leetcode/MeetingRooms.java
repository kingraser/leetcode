package leetcode;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;

import org.junit.Test;

import leetcode.common.Interval;

public class MeetingRooms {

  /*
  Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
  determine if a person could attend all meetings.
  
  For example, Given [[0, 30],[5, 10],[15, 20]], return false.
  */

  @Test
  public void test() {
    assertFalse(canAttendMeetings(new Interval[] { new Interval(0, 30), new Interval(5, 10), new Interval(15, 20) }));
  }

  public boolean canAttendMeetings(Interval[] intervals) {
    Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);
    for (int i = 1; i < intervals.length; i++)
      if (intervals[i].start < intervals[i - 1].end) return false;
    return true;
  }

}
