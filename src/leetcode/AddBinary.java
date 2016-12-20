/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
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
    Assert.assertEquals("100", addBinary("11", "1"));
  }

  public String addBinary(String a, String b) {
    int al = a.length(), bl = b.length(), k = Math.max(al, bl);
    char[] A = new char[k + 1];
    for (int i = --al, j = --bl, r, c = 0; i >= 0 || j >= 0 || c > 0; c = (r & 2) >> 1)
      A[k--] = (char) (((r = (i < 0 ? 0 : a.charAt(i--) - '0') + (j < 0 ? 0 : b.charAt(j--) - '0') + c) & 1) + '0');
    return new String(A, ++k, A.length - k);
  }
}
