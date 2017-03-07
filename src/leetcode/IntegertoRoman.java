package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntegertoRoman {

  /*
  Given an integer, convert it to a roman numeral.
  Input is guaranteed to be within the range from 1 to 3999.
  */

  @Test
  public void test() {
    assertEquals("CMXCIX", intToRoman(999));
  }

  public String intToRoman(int num) {
    int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
    String[] numerals = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < values.length; i++)
      for (; num >= values[i]; num -= values[i], result.append(numerals[i]));
    return result.toString();
  }

}
