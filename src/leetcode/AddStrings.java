/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
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
    Assert.assertEquals("10", addStrings("9", "1"));
  }

  public String addStrings(String num1, String num2) {
    int l1 = num1.length(), l2 = num2.length(), idx = Math.max(l1, l2);
    char[] result = new char[idx + 1];
    for (int i1 = l1 - 1, i2 = l2 - 1, c = 0, v; i1 >= 0 || i2 >= 0 || c > 0; c = v > 9 ? 1 : 0, i1--, i2--)
      result[idx--] = (char) ((v = (i1 >= 0 ? num1.charAt(i1) : '0') + (i2 >= 0 ? num2.charAt(i2) : '0') - 2 * '0' + c)
          % 10 - '0');
    return new String(result, ++idx, result.length - idx);
  }

}
