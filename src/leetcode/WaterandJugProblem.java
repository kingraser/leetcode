/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年6月24日;
//-------------------------------------------------------
public class WaterandJugProblem {

  /*
  You are given two jugs with capacities x and y litres. 
  There is an infinite amount of water supply available. 
  You need to determine whether it is possible to measure exactly z litres using these two jugs.
  
  Operations allowed:
  
  Fill any of the jugs completely.
  Empty any of the jugs.
  Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
  
  Example 1:
  
  Input: x = 2, y = 6, z = 4
  Output: True
  
  Example 2:
  
  Input: x = 2, y = 6, z = 5
  Output: False   
  */

  @Test
  public void test() {
    assertTrue(canMeasureWater(2, 6, 4));
    assertFalse(canMeasureWater(2, 6, 5));
  }

  public boolean canMeasureWater(int x, int y, int z) {
    return z < 1 ? true : z > x + y ? false : z % gcd(Math.min(x, y), Math.max(x, y)) == 0;
  }

  private int gcd(int x, int y) {
    return x == 0 ? y : y == 0 ? x : gcd(y % x, x);
  }
}
