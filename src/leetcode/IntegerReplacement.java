package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntegerReplacement {

  /*
  Given a positive integer n and you can do operations as follow:
    If n is even, replace n with n/2.
    If n is odd, you can replace n with either n + 1 or n - 1.
  What is the minimum number of replacements needed for n to become 1?
  
  Example 1:  
  Input:
  8  
  Output:
  3  
  Explanation:
  8 -> 4 -> 2 -> 1
  
  Example 2:  
  Input:
  7  
  Output:
  4  
  Explanation:
  7 -> 8 -> 4 -> 2 -> 1
  or
  7 -> 6 -> 3 -> 2 -> 1
  */

  @Test
  public void test() {
    assertEquals(3, integerReplacement(8));
    assertEquals(4, integerReplacement(7));
    assertEquals(32, integerReplacement(2147483647));
  }

  public int integerReplacement(int n) {
    int count = 0;
    for (long m = n; m != 1; count++)
      if (0 == (m & 1)) m >>= 1;
      else if (m == 3 || (m & 2) == 0) m--;
      else m++;
    return count;
  }

}
