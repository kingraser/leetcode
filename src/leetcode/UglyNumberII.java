package leetcode;

import static leetcode.SuperUglyNumber.nthSuperUglyNumber;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UglyNumberII {

  /*
  Find the n-th ugly number.
  */

  private static final int[] primes = new int[] { 2, 3, 5 };

  public int nthUglyNumber(int n) {
    return nthSuperUglyNumber(n, primes);
  }

  @Test
  public void test() {
    assertEquals(1, nthUglyNumber(1));
    assertEquals(2, nthUglyNumber(2));
    assertEquals(3, nthUglyNumber(3));
    assertEquals(4, nthUglyNumber(4));
    assertEquals(5, nthUglyNumber(5));
    assertEquals(6, nthUglyNumber(6));
    assertEquals(8, nthUglyNumber(7));
    assertEquals(9, nthUglyNumber(8));
    assertEquals(10, nthUglyNumber(9));
  }
}
