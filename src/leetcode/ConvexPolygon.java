package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ConvexPolygon {
  /*
  Given a list of points that form a polygon when joined sequentially, find if this polygon is convex.  
  Note:  
      There are at least 3 and at most 10,000 points.
      Coordinates are in the range -10,000 to 10,000.
      You may assume the polygon formed by given points is always a simple polygon (Simple polygon definition). 
      In other words, we ensure that exactly two edges intersect at each vertex, and that edges otherwise don't intersect each other.
  
  Example 1:  
  [[0,0],[0,1],[1,1],[1,0]]  
  Answer: True  
  Explanation: 
   __
  |__|
   
  Example 2:  
  [[0,0],[0,10],[10,10],[10,0],[5,5]]  
  Answer: False  
  Explanation:
   __
  |  |
  |/\|
  */

  @Test
  public void test() {
    assertTrue(isConvex(new int[][] { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 0 } }));
    assertFalse(isConvex(new int[][] { { 0, 0 }, { 0, 10 }, { 10, 10 }, { 10, 0 }, { 5, 5 } }));
  }

  public boolean isConvex(int[][] points) {
    for (int i = 0, len = points.length, previous = 0, current = 0; i < len; i++)
      if ((current = (points[(i + 1) % len][0] - points[i][0]) * (points[(i + 2) % len][1] - points[i][1])
          - (points[(i + 2) % len][0] - points[i][0]) * (points[(i + 1) % len][1] - points[i][1])) != 0)
        if (current * previous < 0) return false;
        else previous = current;
    return true;
  }

}
