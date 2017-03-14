package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.Test;

public class MinimumTimeDifference {

  /*
  Given a list of 24-hour clock time points in "Hour:Minutes" format, 
  find the minimum minutes difference between any two time points in the list.
  
  Example 1:  
  Input: ["23:59","00:00"]
  Output: 1
  
  Note:  
    The number of time points in the given list is at least 2 and won't exceed 20000.
    The input time is legal and ranges from 00:00 to 23:59.  
  */

  @Test
  public void test() {
    assertEquals(1, findMinDifference(new String[] { "23:59", "00:00" }));
    assertEquals(1, findMinDifference(new String[] { "12:12", "12:13" }));
  }

  public int findMinDifference(String[] timePoints) {
    Arrays.sort(timePoints);
    return IntStream.range(0, timePoints.length)
        .map(i -> getDifference(timePoints[i], timePoints[(i + 1) % timePoints.length])).min().getAsInt();
  }

  private int getDifference(String early, String late) {
    return ((Integer.parseInt(late.substring(0, 2)) - Integer.parseInt(early.substring(0, 2))) * 60
        + Integer.parseInt(late.substring(3)) - Integer.parseInt(early.substring(3)) + 1440) % 1440;
  }
}
