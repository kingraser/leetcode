/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年4月18日;
//-------------------------------------------------------
public class PowerOfFour {

  /*
  Given an integer (signed 32 bits), write a function to check whether it is a power of 4.  
  Example:
  Given num = 16, return true. Given num = 5, return false.  
  Follow up: Could you solve it without loops/recursion? 
  */

  @Test
  public void test() {
    assertTrue(isPowerOfFour(16));
    assertFalse(isPowerOfFour(5));
  }

  //step1 num > 0 
  //step2 1 bit count is 1 
  //step3 the 1 bit occurs at odd bit
  public boolean isPowerOfFour(int num) {
    return num > 0 && (num & (num - 1)) == 0 && 0 != (num & 0x55555555);
  }

}
