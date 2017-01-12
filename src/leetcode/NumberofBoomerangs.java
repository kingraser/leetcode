/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年11月7日;
//-------------------------------------------------------
public class NumberofBoomerangs {

  /*
  Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters). 
  Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).
  
  Example:
  
  Input:
  [[0,0],[1,0],[2,0]]
  
  Output:
  2
  
  Explanation:
  The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]] 
  */

  @Test
  public void test() {
    assertEquals(2, numberOfBoomerangs(new int[][] { { 0, 0 }, { 1, 0 }, { 2, 0 } }));
  }

  public int numberOfBoomerangs(int[][] points) {
    int sum = 0;
    for (int[] i : points) {
      Map<Integer, Integer> map = new HashMap<>(points.length << 1);
      for (int[] j : points) {
        if (i == j) continue;
        map.compute(calDistance(i, j), (k, v) -> Objects.isNull(v) ? 1 : ++v);
      }
      for (Integer v : map.values())
        if (v > 1) sum += v * --v;
    }
    return sum;
  }

  private int calDistance(int[] x, int[] y) {
    return pow2(x[0] - y[0]) + pow2(x[1] - y[1]);
  }

  private int pow2(int i) {
    return i * i;
  }
}
