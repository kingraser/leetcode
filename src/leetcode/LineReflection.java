package leetcode;

import static leetcode.util.MathUtil.join;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class LineReflection {

  /*
  Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given set of points.
  
  Example 1:  
  Given points = [[1,1],[-1,1]], return true.
  
  Example 2:  
  Given points = [[1,1],[-1,-1]], return false.
  
  Follow up:
  Could you do better than O(n^2)?
  
  Hint:  
    Find the smallest and largest x-value for all points.
    If there is a line then it should be at y = (minX + maxX) / 2.
    For each point, make sure that it has a reflected point in the opposite side.  
  */

  @Test
  public void test() {
    assertTrue(isReflected(new int[][] { { 1, 1 }, { -1, 1 } }));
    assertFalse(isReflected(new int[][] { { 1, 1 }, { -1, -1 } }));
  }

  public boolean isReflected(int[][] points) {
    int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, sum;
    Set<Long> set = new HashSet<>();
    for (int[] p : points) {
      max = Math.max(max, p[0]);
      min = Math.min(min, p[0]);
      set.add(join(p[0], p[1]));
    }
    sum = max + min;
    return Arrays.stream(points).allMatch(p -> set.contains(join(sum - p[0], p[1])));
  }
}
