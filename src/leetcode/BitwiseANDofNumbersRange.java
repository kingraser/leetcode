package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BitwiseANDofNumbersRange {

  /*
  Given a range [m, n] where 0 <= m <= n <= 2147483647, 
  return the bitwise AND of all numbers in this range, inclusive.    
  For example, given the range [5, 7], you should return 4.
  
  get m, n common 1 prefix
  */

  @Test
  public void test() {
    assertEquals(4, rangeBitwiseAnd(5, 7));
  }

  public int rangeBitwiseAnd(int m, int n) {
    int bit = 0;
    for (; m != n; m >>= 1, n >>= 1, bit++);
    return m << bit;
  }
}
