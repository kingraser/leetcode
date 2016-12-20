/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class AddBinary {
  /*
  For example,
  a = "11"
  b = "1"
  Return "100".
  */

  @Test
  public void test() {
    assertEquals("100", addBinary("11", "1"));
  }

  public String addBinary(String a, String b) {
    int al = a.length(), bl = b.length(), k = Math.max(al, bl);
    char[] A = new char[k + 1];
    for (int i = --al, j = --bl, v, c = 0; i >= 0 || j >= 0 || c > 0; c = v > 1 ? 1 : 0)
      A[k--] = (char) ((v = (i < 0 ? 0 : a.charAt(i--) - '0') + (j < 0 ? 0 : b.charAt(j--) - '0') + c) % 2 + '0');
    return new String(A, ++k, A.length - k);
  }
}
