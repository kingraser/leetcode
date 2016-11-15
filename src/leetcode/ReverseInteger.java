/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月8日<p>
//-------------------------------------------------------
public class ReverseInteger {

  /*
  Reverse digits of an integer.
  
  Example1: x = 123, return 321
  Example2: x = -123, return -321 
  */

  public int reverse(int x) {
    Long reverse = Long.valueOf(new StringBuilder().append(Math.abs((long) x)).reverse().toString());
    return x < 0 ? reverse + Integer.MIN_VALUE > 0 ? 0 : -reverse.intValue()
        : reverse > Integer.MAX_VALUE ? 0 : reverse.intValue();
  }

  public int reverseII(int x) {
    if (Math.abs((long) x) < 10) return x;
    long y = 0, n = 1 + (int) Math.log10(Math.abs((long) x));
    for (int i = 0; i < n; y = y * 10 + (int) (x / Math.pow(10, i) % 10), i++);
    return y < Integer.MIN_VALUE || y > Integer.MAX_VALUE ? 0 : (int) y;
  }

  @Test
  public void test() {
    Assert.assertEquals(321, reverse(123));
    Assert.assertEquals(-321, reverse(-123));
    Assert.assertEquals(321, reverseII(123));
    Assert.assertEquals(-321, reverseII(-123));
  }

}
