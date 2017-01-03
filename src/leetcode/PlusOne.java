/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class PlusOne {

  public int[] plusOne(int[] digits) {
    for (int l = digits.length - 1; l > -1;)
      if (digits[l] == 9) digits[l--] = 0;
      else {
        digits[l]++;
        return digits;
      }
    int[] res = new int[digits.length + 1];
    res[0] = 1;
    return res;
  }

  @Test
  public void test() {
    assertArrayEquals(new int[] { 1, 0, 0, 0 }, plusOne(new int[] { 9, 9, 9 }));
  }
}
