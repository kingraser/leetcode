package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SumofTwoIntegers {

  /*
  Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
  
  Example:
  Given a = 1 and b = 2, return 3. 
  */

  @Test
  public void test() {
    assertEquals(3, getSum(2, 1));
  }

  public int getSum(int a, int b) {
    return b == 0 ? a : getSum(a ^ b, (a & b) << 1);
  }

}
