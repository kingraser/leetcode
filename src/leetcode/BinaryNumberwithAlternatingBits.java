package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BinaryNumberwithAlternatingBits {

  /*
  Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.
  
  Example 1:  
  Input: 5
  Output: True
  Explanation:
  The binary representation of 5 is: 101
  
  Example 2:  
  Input: 7
  Output: False
  Explanation:
  The binary representation of 7 is: 111.
  
  Example 3:  
  Input: 11
  Output: False
  Explanation:
  The binary representation of 11 is: 1011.
  
  Example 4:  
  Input: 10
  Output: True
  Explanation:
  The binary representation of 10 is: 1010.  
  */

  @Test
  public void test() {
    assertTrue(hasAlternatingBits(5));
    assertFalse(hasAlternatingBits(7));
    assertFalse(hasAlternatingBits(11));
    assertTrue(hasAlternatingBits(10));
  }

  public boolean hasAlternatingBits(int n) {
    return ((n ^= (n >> 2)) & (n - 1)) == 0;
  }
}
