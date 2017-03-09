package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UglyNumber {

  /*
  Ugly number is a number only has 2,3,5 prime factor. 1 is also ugly number.
  Check a number is ugly number or not.
  */

  public boolean isUgly(int num) {
    if (num <= 0) return false;
    while ((num & 1) == 0)
      num >>= 1;
    while (num % 5 == 0)
      num /= 5;
    while (num % 3 == 0)
      num /= 3;
    return num == 1;
  }

  @Test
  public void test() {
    assertTrue(isUgly(8));
    assertTrue(isUgly(6));
    assertFalse(isUgly(14));
  }
}
