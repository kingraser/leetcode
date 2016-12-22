/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class FractiontoRecurringDecimal {

  /*
  Given two integers representing the numerator and denominator of a fraction, 
  return the fraction in string format.    
  If the fractional part is repeating, enclose the repeating part in parentheses.    
  For example,    
  Given numerator = 1, denominator = 2, return "0.5".
  Given numerator = 2, denominator = 1, return "2".
  Given numerator = 2, denominator = 3, return "0.(6)".
  */

  @Test
  public void test() {
    assertEquals("0.5", fractionToDecimal(1, 2));
    assertEquals("2", fractionToDecimal(2, 1));
    assertEquals("0.(6)", fractionToDecimal(2, 3));
    assertEquals("0", fractionToDecimal(0, 3));
    assertEquals("-0.(6)", fractionToDecimal(-2, 3));
    assertEquals("10.05", fractionToDecimal(1005, 100));
    assertEquals("10000.0(05)", fractionToDecimal(9900005, 990));
  }

  public String fractionToDecimal(int numerator, int denominator) {
    if (numerator == 0) return "0";
    long num = Math.abs((long) numerator), den = Math.abs((long) denominator);
    StringBuilder res = new StringBuilder(((numerator > 0) ^ (denominator > 0)) ? "-" : "").append(num / den);
    if ((num %= den) == 0) return res.toString();
    res.append(".");
    for (Map<Long, Integer> map = new HashMap<>(); num != 0; res.append((num *= 10) / den), num %= den)
      if (map.containsKey(num)) return res.insert(map.get(num), "(").append(")").toString();
      else map.put(num, res.length());
    return res.toString();
  }

}
