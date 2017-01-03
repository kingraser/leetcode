/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年8月2日;
//-------------------------------------------------------
public class SumofTwoIntegers {

  /*
  Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
  
  Example:
  Given a = 1 and b = 2, return 3. 
  */

  @Test
  public void test() {
    assertEquals(3, getSum(2, 1));
  }

  public int getSum(int a, int b) {
    return b == 0 ? a : getSum(a ^ b, (a & b) << 1);
  }

}
