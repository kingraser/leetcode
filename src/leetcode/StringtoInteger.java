/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月8日<p>
//-------------------------------------------------------
public class StringtoInteger {

  /*
  implement atoi to convert a string to an integer.
  
  Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.
  
  Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front. 
  */

  public int myAtoi(String str) {
    str = str.trim();
    for (int i = 0; i < str.length(); i++)
      if (!Character.isDigit(str.charAt(i)) && (i != 0 || (str.charAt(i) != '+' && str.charAt(i) != '-')))
        str = str.substring(0, i);
    if (str.length() == 0 || !Character.isDigit(str.charAt(str.length() - 1))) return 0;
    BigInteger bigInteger = new BigInteger(str);
    if (bigInteger.compareTo(new BigInteger(Integer.toString(Integer.MIN_VALUE))) == -1) return Integer.MIN_VALUE;
    if (bigInteger.compareTo(new BigInteger(Integer.toString(Integer.MAX_VALUE))) == 1) return Integer.MAX_VALUE;
    return bigInteger.intValue();
  }

  public int myAtoiII(String str) {
    str = str.trim();
    if (str.length() == 0) return 0;
    long result = 0;
    if (!Character.isDigit(str.charAt(0)) && '-' != str.charAt(0) && '+' != str.charAt(0)) return 0;
    for (int i = !Character.isDigit(str.charAt(0)) ? 1 : 0; i < str.length() && Character.isDigit(str.charAt(i));) {
      result = result * 10 + str.charAt(i++) - '0';
      if (str.charAt(0) == '-' && -result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
      else if (str.charAt(0) != '-' && result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
    }
    return (int) ((str.charAt(0) == '-') ? -result : result);
  }

  @Test
  public void test() {
    assertEquals(2147483647, myAtoiII("2147483648"));
    assertEquals(2147483647, myAtoi("2147483648"));
  }

}
