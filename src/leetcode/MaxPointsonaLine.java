/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import leetcode.common.Point;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月16日<p>
//-------------------------------------------------------
public class MaxPointsonaLine {

  /*
  Given n points on a 2D plane, 
  find the maximum number of points that lie on the same straight line.
  */

  public int maxPoints(Point[] p) {
    int result = 0;
    for (int i = 0, same = 1; i < p.length; i++, same = 1) {
      Map<Double, Integer> map = new HashMap<>();
      for (int j = i + 1; j < p.length; j++)
        if (p[i].equals(p[j])) same++;
        else map.compute(getGradient(p[i], p[j]), (k, v) -> v == null ? 1 : v + 1);
      result = Math.max(result, same + map.values().stream().max((i1, i2) -> i1 - i2).orElse(0));
    }
    return result;
  }

  private Double getGradient(Point p1, Point p2) {
    return p1.x == p2.x ? Double.NaN : p1.y == p2.y ? 0 : ((double) (p1.y - p2.y)) / (p1.x - p2.x);
  }

  @Test
  public void test() {
    assertEquals(2, maxPoints(new Point[] { new Point(), new Point() }));
    assertEquals(2, maxPoints(new Point[] { new Point(), new Point(0, 1) }));
    assertEquals(3, maxPoints(new Point[] { new Point(-4, 1), new Point(-7, 7), new Point(-1, 5), new Point(9, -25) }));
    assertEquals(1, maxPoints(new Point[] { new Point() }));
  }

}
