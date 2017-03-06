package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReverseInteger {

  /*
  Reverse digits of an integer.
  
  Example1: x = 123, return 321
  Example2: x = -123, return -321 
  */

  @Test
  public void test() {
    assertEquals(321, reverse(123));
    assertEquals(-321, reverse(-123));
  }

  public static int reverse(int x) {
    long result = 0;
    for (; x != 0; x /= 10)
      result = result * 10 + x % 10;
    return result < Integer.MIN_VALUE || result > Integer.MAX_VALUE ? 0 : (int) result;
  }
}
