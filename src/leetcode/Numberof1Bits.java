/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class Numberof1Bits {
  /*
  Write a function that takes an unsigned integer 
  and returns the number of '1' bits it has (also known as the Hamming weight).  
  For example, 
  the 32-bit integer '11' has binary representation 00000000000000000000000000001011, 
  so the function should return 3.
  */

  @Test
  public void test() {
    assertEquals(3, hammingWeight(11));
    assertEquals(3, hammingWeightII(11));
  }

  //cheat
  public int hammingWeight(int n) {
    return Integer.bitCount(n);
  }

  public int hammingWeightII(int n) {
    int result = 0;
    for (; n != 0; n >>= 1)
      if ((n & 1) == 1) result++;
    return result;
  }

}
