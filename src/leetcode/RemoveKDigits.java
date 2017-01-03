/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.math.BigInteger;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年9月20日;
//-------------------------------------------------------
public class RemoveKDigits {
  /*
  Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
  
  Note:
  
  The length of num is less than 10002 and will be ≥ k.
  The given num does not contain any leading zero.
  
  Example 1:
  
  Input: num = "1432219", k = 3
  Output: "1219"
  Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
  
  Example 2:
  
  Input: num = "10200", k = 1
  Output: "200"
  Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
  
  Example 3:
  
  Input: num = "10", k = 2
  Output: "0"
  Explanation: Remove all the digits from the number and it is left with nothing which is 0.    
  */

  @Test
  public void test() {
    assertEquals("1219", removeKdigits("1432219", 3));
    assertEquals("200", removeKdigits("10200", 1));
    assertEquals("0", removeKdigits("10", 2));
  }

  public String removeKdigits(String num, int k) {
    StringBuilder sb = new StringBuilder(num);
    for (int idx = 0; k-- > 0 && sb.length() > 0; sb.deleteCharAt(idx))
      if ((idx = Math.min(sb.length() - 1, idx)) > 0 && sb.charAt(idx - 1) > sb.charAt(idx)) --idx;
      else for (int end = sb.length() - 1; idx < end && sb.charAt(idx) <= sb.charAt(idx + 1); idx++);
    return sb.length() == 0 ? "0" : new BigInteger(sb.toString()).toString();
  }
}
