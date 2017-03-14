package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NumberofDigitOne {

  /*
  Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
  For example:
  Given n = 13,
  Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.  
  Hint: Beware of overflow.
  */

  @Test
  public void test() {
    assertEquals(6, countDigitOne(13));
  }

  public int countDigitOne(int n) {
    int ones = 0;
    for (long unit = 1, a, b; unit <= n; unit *= 10) {
      a = n / unit;
      b = n % unit;
      ones += (a + 8) / 10 * unit + (a % 10 == 1 ? b + 1 : 0);
    }
    return ones;
  }
}
