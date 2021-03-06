package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import leetcode.common.Interval;

public class NonoverlappingIntervals {

  /*
  Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
  
  Note:  
    You may assume the interval's end point is always bigger than its start point.
    Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
  
  Example 1:  
  Input: [ [1,2], [2,3], [3,4], [1,3] ]  
  Output: 1  
  Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
  
  Example 2:  
  Input: [ [1,2], [1,2], [1,2] ]  
  Output: 2  
  Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
  
  Example 3:  
  Input: [ [1,2], [2,3] ]  
  Output: 0  
  Explanation: You don't need to remove any of the intervals since they're already non-overlapping. 
  */

  @Test
  public void test() {
    assertEquals(1, eraseOverlapIntervals(
        new Interval[] { new Interval(1, 2), new Interval(2, 3), new Interval(3, 4), new Interval(1, 3) }));
    assertEquals(2,
        eraseOverlapIntervals(new Interval[] { new Interval(1, 2), new Interval(1, 2), new Interval(1, 2) }));
    assertEquals(0, eraseOverlapIntervals(new Interval[] { new Interval(1, 2), new Interval(2, 3) }));
  }

  public int eraseOverlapIntervals(Interval[] intervals) {
    int result = 0;
    Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);
    for (int last = 0, i = 1; i < intervals.length; i++)
      if (intervals[last].end > intervals[i].start) {
        result++;
        if (intervals[last].end > intervals[i].end) last = i;
      } else last = i;
    return result;
  }
}
