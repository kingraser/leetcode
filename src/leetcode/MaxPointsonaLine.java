package leetcode;

import leetcode.common.Point;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MaxPointsonaLine {

  /*
  Given n points on a 2D plane, 
  find the maximum number of points that lie on the same straight line.
  */

  public int maxPoints(Point[] p) {
    int result = 0;
    for (int i = 0, j, same; i < p.length; i++) {
      Map<Double, Integer> map = new HashMap<>();
      for (same = 1, j = i + 1; j < p.length; j++)
        if (p[i].equals(p[j])) same++;
        else map.merge(getGradient(p[i], p[j]), 1, Integer::sum);
      result = Math.max(result, same + map.values().stream().max(Integer::compare).orElse(0));
    }
    return result;
  }

  private Double getGradient(Point p1, Point p2) {
    return p1.x == p2.x ? Double.NaN : p1.y == p2.y ? 0 : ((double) (p1.y - p2.y)) / (p1.x - p2.x);
  }

  @Test
  public void test() {
    assertEquals(2, maxPoints(new Point[] { new Point(0, 0), new Point(0, 0) }));
    assertEquals(2, maxPoints(new Point[] { new Point(0, 0), new Point(0, 1) }));
    assertEquals(3, maxPoints(new Point[] { new Point(-4, 1), new Point(-7, 7), new Point(-1, 5), new Point(9, -25) }));
    assertEquals(1, maxPoints(new Point[] { new Point(0, 0) }));
  }

}
