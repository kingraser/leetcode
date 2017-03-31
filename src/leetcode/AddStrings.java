package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddStrings {

  /*
  Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.
  
  Note:  
    The length of both num1 and num2 is < 5100.
    Both num1 and num2 contains only digits 0-9.
    Both num1 and num2 does not contain any leading zero.
    You must not use any built-in BigInteger library or convert the inputs to integer directly. 
  */

  @Test
  public void test() {
    assertEquals("10", addStrings("9", "1"));
  }

  public String addStrings(String a, String b) {
    return addStrings(a, b, 10);
  }

  public static String addStrings(String a, String b, int radix) {
    StringBuilder result = new StringBuilder();
    for (int aIdx = a.length() - 1, bIdx = b.length() - 1, carry = 0; aIdx >= 0 || bIdx >= 0
        || carry > 0; carry /= radix)
      result.append(digit((carry += getDigit(a, aIdx--) + getDigit(b, bIdx--)) % radix));
    return result.reverse().toString();
  }

  public static int getDigit(String s, int idx) {
    return idx < 0 ? 0 : s.charAt(idx) - '0';
  }

  public static char digit(int i) {
    return (char) (i + '0');
  }
}
