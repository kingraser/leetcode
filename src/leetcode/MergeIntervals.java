package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.junit.Test;

import leetcode.common.Interval;

public class MergeIntervals {

  /*
  Given a collection of intervals, merge all overlapping intervals.
  
  For example,
  Given [1,3],[2,6],[8,10],[15,18],
  return [1,6],[8,10],[15,18]. 
  */

  public List<Interval> merge(List<Interval> intervals) {
    if (Objects.isNull(intervals) || intervals.isEmpty()) return intervals;
    LinkedList<Interval> result = new LinkedList<>();
    intervals.stream().sorted((i1, i2) -> i1.start - i2.start).forEach(i -> {
      if (result.isEmpty() || i.start > result.peekLast().end) result.add(i);
      else result.peekLast().end = Math.max(result.peekLast().end, i.end);
    });
    return result;
  }

  @Test
  public void test() {
    assertEquals(Arrays.asList(new Interval(1, 6), new Interval(8, 10), new Interval(15, 18)),
        merge(Arrays.asList(new Interval(1, 3), new Interval(2, 6), new Interval(8, 10), new Interval(15, 18))));
  }

}
