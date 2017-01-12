/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月8日<p>
//-------------------------------------------------------
public class PalindromeNumber {

  public boolean isPalindrome(int x) {
    if (x < 0) return false;
    if (x < 10) return true;
    for (int a = getMax10PowSmallOrEqual(x), b = 1; a > b; a /= 10, b *= 10)
      if ((x / a) % 10 != (x / b) % 10) return false;
    return true;
  }

  public int getMax10PowSmallOrEqual(int n) {
    int result = 1;
    while (result < n)
      result *= 10;
    return result == n ? result : result / 10;
  }

  @Test
  public void test() {
    assertTrue(isPalindrome(123321));
  }
}
