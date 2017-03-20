package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddBinary {

  /*
  Given two binary strings, return their sum (also a binary string). 
  For example,
  a = "11"
  b = "1"
  Return "100".
  */

  @Test
  public void test() {
    assertEquals("100", addBinary("11", "1"));
    assertEquals("110", addBinary("11", "11"));
  }

  public String addBinary(String a, String b) {
    StringBuilder result = new StringBuilder();
    for (int aIdx = a.length() - 1, bIdx = b.length() - 1, carry = 0; aIdx >= 0 || bIdx >= 0 || carry > 0; carry >>= 1)
      result.append(digit((carry += getDigit(a, aIdx--) + getDigit(b, bIdx--)) & 1));
    return result.reverse().toString();
  }

  public static int getDigit(String s, int idx) {
    return idx < 0 ? 0 : s.charAt(idx) - '0';
  }

  public static char digit(int i) {
    return (char) (i + '0');
  }
}
