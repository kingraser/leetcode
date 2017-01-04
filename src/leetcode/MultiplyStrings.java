/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月10日<p>
//-------------------------------------------------------
public class MultiplyStrings {

  /*
  Given two numbers represented as strings, return multiplication of the numbers as a string.    
  Note: The numbers can be arbitrarily large and are non-negative.
  */

  @Test
  public void test() {
    assertEquals("998001", multiply("999", "999"));
    assertEquals("56088", multiply("123", "456"));
    assertEquals("0", multiply("999", "0"));
  }

  public String multiply(String A1, String A2) {
    int[] r = new int[A1.length() + A2.length()];
    for (int l1 = A1.length(), l2 = A2.length(), i = l1 - 1; i >= 0; i--)
      for (int j = l2 - 1, c = 0; j >= 0 || c > 0; j--, c /= 10)
        r[i + j + 1] = (c += r[i + j + 1] + (A1.charAt(i) - '0') * (j < 0 ? 0 : A2.charAt(j) - '0')) % 10;
    return new String(Arrays.stream(r).map(i -> '0' + i).toArray(), get1stNot0Idx(r), r.length - get1stNot0Idx(r));
  }

  private int get1stNot0Idx(int[] A) {
    for (int i = 0; i < A.length - 1; i++)
      if (A[i] != 0) return i;
    return A.length - 1;
  }

}
