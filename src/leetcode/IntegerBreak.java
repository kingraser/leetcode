package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntegerBreak {

  /*
  Given a positive integer n, break it into the sum of at least two positive integers,
  and maximize the product of those integers. 
  Return the maximum product you can get.
  
  For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
  
  Note: you may assume that n is not less than 2. 
  */

  @Test
  public void test() {
    assertEquals(1, integerBreak(2));
    assertEquals(2, integerBreak(3));
    assertEquals(4, integerBreak(4));
    assertEquals(6, integerBreak(5));
    assertEquals(9, integerBreak(6));
    assertEquals(12, integerBreak(7));
    assertEquals(18, integerBreak(8));
    assertEquals(27, integerBreak(9));
    assertEquals(36, integerBreak(10));
  }

  public int integerBreak(int n) {
    if (n < 4) return --n;
    int x = (int) Math.pow(3, n / 3), y = n % 3;
    return y == 0 ? x : y == 1 ? x / 3 * 4 : x * y;
  }

}
