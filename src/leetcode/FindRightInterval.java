package leetcode;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import leetcode.common.Interval;

public class FindRightInterval {

  /*
  Given a set of intervals, for each of the interval i, 
  check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, 
  which can be called that j is on the "right" of i.
  For any interval i, you need to store the minimum interval j's index, 
  which means that the interval j has the minimum start point to build the "right" relationship for interval i. 
  If the interval j doesn't exist, store -1 for the interval i. 
  Finally, you need output the stored value of each interval as an array.
  
  Note:  
    You may assume the interval's end point is always bigger than its start point.
    You may assume none of these intervals have the same start point.
  
  Example 1:  
  Input: [ [1,2] ]  
  Output: [-1]  
  Explanation: There is only one interval in the collection, so it outputs -1.
  
  Example 2:  
  Input: [ [3,4], [2,3], [1,2] ]  
  Output: [-1, 0, 1]  
  Explanation: There is no satisfied "right" interval for [3,4].
  For [2,3], the interval [3,4] has minimum-"right" start point;
  For [1,2], the interval [2,3] has minimum-"right" start point.
  
  Example 3:  
  Input: [ [1,4], [2,3], [3,4] ]  
  Output: [-1, 2, -1]  
  Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
  For [2,3], the interval [3,4] has minimum-"right" start point.
  */

  @Test
  public void test() {
    assertArrayEquals(findRightInterval(new Interval[] { new Interval(1, 2) }), new int[] { -1 });
    assertArrayEquals(findRightInterval(new Interval[] { new Interval(3, 4), new Interval(2, 3), new Interval(1, 2) }),
        new int[] { -1, 0, 1 });
    assertArrayEquals(findRightInterval(new Interval[] { new Interval(1, 4), new Interval(2, 3), new Interval(3, 4) }),
        new int[] { -1, 2, -1 });
  }

  public int[] findRightInterval(Interval[] intervals) {
    Map<Integer, Integer> map = new HashMap<>(intervals.length, 1f);
    int[] starts = new int[intervals.length], result = new int[intervals.length];
    for (int i = 0; i < intervals.length; map.put(intervals[i].start, i++))
      starts[i] = intervals[i].start;
    Arrays.sort(starts);
    for (int i = 0, idx; i < intervals.length; i++)
      result[i] = (idx = Arrays.binarySearch(starts, intervals[i].end)) >= 0 ? map.get(starts[idx])
          : (idx = ~idx) == intervals.length ? -1 : map.get(starts[idx]);
    return result;
  }
}
