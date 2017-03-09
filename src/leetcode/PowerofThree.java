package leetcode;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class PowerofThree {

  /*
  Given an integer, write a function to determine if it is a power of three. 
  */
  static long maxPowerOfThree = 3;

  static {
    while (maxPowerOfThree < Integer.MAX_VALUE)
      maxPowerOfThree *= 3;
    maxPowerOfThree /= 3;
  }

  @Test
  public void test() {
    assertTrue(IsPowerOfThree(3));
    assertTrue(IsPowerOfThree(9));
  }

  public boolean IsPowerOfThree(int n) {
    return n > 0 && (maxPowerOfThree % n == 0);
  }
}
