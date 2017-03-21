package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import leetcode.common.Interval;

public class DataStreamasDisjointIntervals {

  /*
  Given a data stream input of non-negative integers a1, a2, ..., an, ..., 
  summarize the numbers seen so far as a list of disjoint intervals.
  
  For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
  
  [1, 1]
  [1, 1], [3, 3]
  [1, 1], [3, 3], [7, 7]
  [1, 3], [7, 7]
  [1, 3], [6, 7]
  
  Follow up:
  What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size? 
  */

  @Test
  public void test() {
    SummaryRanges summaryRanges = new SummaryRanges();
    summaryRanges.addNum(1);
    assertEquals(Arrays.asList(new Interval(1, 1)), summaryRanges.getIntervals());
    summaryRanges.addNum(3);
    assertEquals(Arrays.asList(new Interval(1, 1), new Interval(3, 3)), summaryRanges.getIntervals());
    summaryRanges.addNum(7);
    assertEquals(Arrays.asList(new Interval(1, 1), new Interval(3, 3), new Interval(7, 7)),
        summaryRanges.getIntervals());
    summaryRanges.addNum(2);
    assertEquals(Arrays.asList(new Interval(1, 3), new Interval(7, 7)), summaryRanges.getIntervals());
    summaryRanges.addNum(6);
    assertEquals(Arrays.asList(new Interval(1, 3), new Interval(6, 7)), summaryRanges.getIntervals());
  }

  public class SummaryRanges {
    TreeMap<Integer, Interval> map = new TreeMap<>();

    public void addNum(int val) {
      if (map.containsKey(val)) return;
      Entry<Integer, Interval> l = map.lowerEntry(val), h = map.higherEntry(val);
      if (Objects.nonNull(l) && Objects.nonNull(h) && l.getValue().end + 1 == val && h.getKey() == val + 1) {
        l.getValue().end = h.getValue().end;
        map.remove(h.getKey());
      } else if (Objects.nonNull(l) && l.getValue().end + 1 >= val) l.getValue().end = Math.max(l.getValue().end, val);
      else if (Objects.nonNull(h) && h.getKey() == val + 1) {
        h.getValue().start = val;
        map.put(val, h.getValue());
        map.remove(h.getKey());
      } else map.put(val, new Interval(val, val));
    }

    public List<Interval> getIntervals() {
      return new ArrayList<>(map.values());
    }
  }
}
