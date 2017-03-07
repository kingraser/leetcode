package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DivideTwoIntegers {

  /*
  Divide two integers without using multiplication, division and mod operator.
  If it is overflow, return MAX_INT. 
  
  Divide is multiple sub operation. You can double the divisor to accelerate.
  */

  @Test
  public void test() {
    assertEquals(2, divide(5, 2));
    assertEquals(2, divide(4, 2));
    assertEquals(Integer.MAX_VALUE, divide(6, 0));
    assertEquals(Integer.MAX_VALUE, divide(Integer.MIN_VALUE, -1));
    assertEquals(Integer.MIN_VALUE, divide(Integer.MIN_VALUE, 1));
  }

  public int divide(int dividend, int divisor) {
    if (divisor == 0) return Integer.MAX_VALUE;
    long beichu = Math.abs((long) dividend), chushu = Math.abs((long) divisor), result = 0, i = 1;
    while (beichu >= chushu || i > 1)
      if (beichu >= chushu) {
        chushu <<= 1;
        i <<= 1;
      } else {
        result += (i >>= 1);
        beichu -= (chushu >>= 1);
        chushu = Math.abs((long) divisor);
        i = 1;
      }
    if ((dividend >= 0) ^ (divisor > 0)) return -result < Integer.MIN_VALUE ? Integer.MAX_VALUE : (int) -result;
    return result > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) result;
  }
}
