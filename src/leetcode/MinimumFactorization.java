package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MinimumFactorization {

  /*
  Given a positive integer a, find the smallest positive integer b whose multiplication of each digit equals to a.  
  If there is no answer or the answer is not fit in 32-bit sidgned integer, then return 0.
  
  Example 1
  Input:48  
  Output:68
  
  Example 2
  Input:15
  Output:35
  */

  @Test
  public void test() {
    assertEquals(68, smallestFactorization(48));
    assertEquals(35, smallestFactorization(15));
  }

  public int smallestFactorization(int a) {
    if (a < 10) return a;
    long result = 0;
    for (long i = 9, base = 1; i > 1; i--)
      for (; a % i == 0; a /= i, base *= 10)
        if ((result += i * base) > Integer.MAX_VALUE) return 0;
    return a == 1 ? (int) result : 0;
  }

}
