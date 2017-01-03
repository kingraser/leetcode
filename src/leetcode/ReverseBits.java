/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class ReverseBits {
  /*
  Reverse bits of a given 32 bits unsigned integer.
  For example, 
  given input 43261596 (represented in binary as 00000010100101000001111010011100), 
  return 964176192 (represented in binary as 00111001011110000010100101000000).
  
  1作弊法,系统自带
  2模拟
  */

  // you need treat n as an unsigned value
  public int reverseBits(int n) {
    return Integer.reverse(n);
  }

  public int reverseBitsII(int n) {
    int result = 0;
    for (int i = 0; i < 32; i++) {
      result += n & 1;
      n >>>= 1;
      if (i < 31) result <<= 1;
    }
    return result;
  }

}
