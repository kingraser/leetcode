/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月9日<p>
//-------------------------------------------------------
public class RomantoInteger {

  public int romanToInt(String s) {
    int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
    String[] numerals = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
    int result = 0;
    for (int i = 0, index = 0; index < s.length() || i < values.length; i++)
      for (; index + numerals[i].length() <= s.length() && numerals[i].equals(
          s.substring(index, index + numerals[i].length())); result += values[i], index += numerals[i].length());
    return result;
  }
}
