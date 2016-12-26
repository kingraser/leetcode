/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月17日<p>
//-------------------------------------------------------
public class PowXN {

  @Test
  public void test() {
    assertEquals(8, pow(2, 3), 0);
    assertEquals(27, pow(3, 3), 0);
  }

  public double pow(double x, int n) {
    if (n < 0) return pow(1 / x, -n);
    double result = 1;
    for (; n > 0; n = n >> 1, x *= x)
      if ((n & 1) == 1) result *= x;
    return result;
  }
}
