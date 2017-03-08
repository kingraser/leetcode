package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Sqrtx {

  /*
  Implement int sqrt(int x).  
  Compute and return the square root of x.
  */

  @Test
  public void test() {
    assertEquals(0, mySqrt(0));
    assertEquals(1, mySqrt(1));
    assertEquals(1, mySqrt(2));
    assertEquals(1, mySqrt(3));
    assertEquals(2, mySqrt(4));
    assertEquals(2, mySqrt(5));
  }

  public int mySqrt(int x) {
    for (long start = 0, end = x, mid, square;;)
      if ((square = (mid = (start + end) >> 1) * mid) == x) return (int) mid;
      else if (square > x) end = mid - 1;
      else if ((mid + 1) * (mid + 1) > x) return (int) mid;
      else start = mid + 1;
  }

}
