/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月1日<p>
//-------------------------------------------------------
public class UglyNumberII {

  /*
          找到第n个ugly number
  */

  private static final int[] primes = new int[] { 2, 3, 5 };

  public int nthUglyNumber(int n) {
    return SuperUglyNumber.nthSuperUglyNumber(n, primes);
  }

  @Test
  public void test() {
    assertEquals(1, nthUglyNumber(1));
    assertEquals(2, nthUglyNumber(2));
    assertEquals(3, nthUglyNumber(3));
    assertEquals(4, nthUglyNumber(4));
    assertEquals(5, nthUglyNumber(5));
    assertEquals(6, nthUglyNumber(6));
    assertEquals(8, nthUglyNumber(7));
    assertEquals(9, nthUglyNumber(8));
    assertEquals(10, nthUglyNumber(9));
  }
}
