/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年10月10日;
//-------------------------------------------------------
public class AddStrings {
  /*
  Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.
  
  Note:
  
    The length of both num1 and num2 is < 5100.
    Both num1 and num2 contains only digits 0-9.
    Both num1 and num2 does not contain any leading zero.
    You must not use any built-in BigInteger library or convert the inputs to integer directly. 
  */

  @Test
  public void test() {
    assertEquals("10", addStrings("9", "1"));
  }

  public String addStrings(String n1, String n2) {
    int l1 = n1.length(), l2 = n2.length(), k = Math.max(l1, l2);
    char[] A = new char[k + 1];
    for (int i = --l1, j = --l2, c = 0, v; i >= 0 || j >= 0 || c > 0; c = v > 9 ? 1 : 0)
      A[k--] = (char) ((v = (i < 0 ? 0 : n1.charAt(i--) - '0') + (j < 0 ? 0 : n2.charAt(j--) - '0') + c) % 10 + '0');
    return new String(A, ++k, A.length - k);
  }

}
