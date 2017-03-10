package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountNumberswithUniqueDigits {

  /*
  Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10^n.
  
  Example:
  Given n = 2, return 91. 
  The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99] 
  */

  /*
  Let f(k) = count of numbers with unique digits with length equals k.
  f(1) = 10, ..., f(k) = 9 * 9 * 8 * ... (9 - k + 2) 
  The first factor is 9 because a number cannot start with 0.
  O(k)
  */

  @Test
  public void test() {
    assertEquals(1, countNumbersWithUniqueDigits(0));
    assertEquals(10, countNumbersWithUniqueDigits(1));
    assertEquals(91, countNumbersWithUniqueDigits(2));
  }

  public int countNumbersWithUniqueDigits(int n) {
    int result = n == 0 ? 1 : 10, fk = 9;
    for (int k = 9; n-- > 1 && k > 0; result += fk *= k--);
    return result;
  }
}
