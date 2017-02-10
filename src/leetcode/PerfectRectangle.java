package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.Test;

public class PerfectRectangle {

  /*
  Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.  
  Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).
  
  Example 1:  
  rectangles = [
    [1,1,3,3],
    [3,1,4,2],
    [3,2,4,4],
    [1,3,2,4],
    [2,3,3,4]
  ]  
  Return true. All 5 rectangles together form an exact cover of a rectangular region.
   __ __ __
  |__|__|  |
  |     |__|
  |_____|__| 
  
  Example 2:  
  rectangles = [
    [1,1,2,3],
    [1,3,2,4],
    [3,1,4,2],
    [3,2,4,4]
  ]  
  Return false. Because there is a gap between the two rectangular regions.
   __    __
  |__|  |  |
  |  |  |__|
  |__|  |__| 
  
  Example 3:  
  rectangles = [
    [1,1,3,3],
    [3,1,4,2],
    [1,3,2,4],
    [3,2,4,4]
  ]  
  Return false. Because there is a gap in the top center.
   __    __
  |__|__|  |
  |     |__|
  |_____|__| 
  
  Example 4:  
  rectangles = [
    [1,1,3,3],
    [3,1,4,2],
    [1,3,2,4],
    [2,2,4,4]
  ]  
  Return false. Because two of the rectangles overlap with each other.
   __ __ __
  |__|__   |
  |  |@@|__|
  |_____|__|   
  */

  @Test
  public void test() {
    assertTrue(isRectangleCover(
        new int[][] { { 1, 1, 3, 3 }, { 3, 1, 4, 2 }, { 3, 2, 4, 4 }, { 1, 3, 2, 4 }, { 2, 3, 3, 4 } }));
    assertFalse(isRectangleCover(new int[][] { { 1, 1, 2, 3 }, { 1, 3, 2, 4 }, { 3, 1, 4, 2 }, { 3, 2, 4, 4 } }));
    assertFalse(isRectangleCover(new int[][] { { 1, 1, 3, 3 }, { 3, 1, 4, 2 }, { 1, 3, 2, 4 }, { 3, 2, 4, 4 } }));
    assertFalse(isRectangleCover(new int[][] { { 1, 1, 3, 3 }, { 3, 1, 4, 2 }, { 1, 3, 2, 4 }, { 2, 2, 4, 4 } }));
  }

  public boolean isRectangleCover(int[][] rectangles) {
    if (rectangles.length == 0 || rectangles[0].length == 0) return false;
    int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE, bottom = left, top = right, sum = 0;
    Set<Long> set = new HashSet<>();
    for (int[] rect : rectangles) {
      left = Math.min(rect[0], left);
      bottom = Math.min(rect[1], bottom);
      right = Math.max(rect[2], right);
      top = Math.max(rect[3], top);
      sum += (rect[2] - rect[0]) * (rect[3] - rect[1]);
      Stream.of(join(rect[0], rect[1]), join(rect[0], rect[3]), join(rect[2], rect[3]), join(rect[2], rect[1]))
          .forEach(a -> toggle(set, a));
    }
    return set.size() == 4 && sum == (right - left) * (top - bottom) && set.contains(join(left, bottom))
        && set.contains(join(left, top)) && set.contains(join(right, bottom)) && set.contains(join(right, top));
  }

  private void toggle(Set<Long> set, long a) {
    if (!set.add(a)) set.remove(a);
  }

  private long join(long a, long b) {
    return (a << 32) | (b & 0xffffffffL);
  }

}
