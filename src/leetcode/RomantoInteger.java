package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RomantoInteger {

  /*
  Given a roman numeral, convert it to an integer.  
  Input is guaranteed to be within the range from 1 to 3999.
  */

  @Test
  public void test() {
    assertEquals(999, romanToInt("CMXCIX"));
  }

  int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
  String[] numerals = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

  public int romanToInt(String s) {
    int result = 0;
    for (int i = 0, start = 0; start < s.length(); i++)
      for (; s.startsWith(numerals[i], start); result += values[i], start += numerals[i].length());
    return result;
  }
}
