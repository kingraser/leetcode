package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class SmallestGoodBase {

  /*
  For an integer n, we call k>=2 a good base of n, if all digits of n base k are 1.
  Now given a string representing n, you should return the smallest good base of n in string format.
  
  Example 1:  
  Input: "13"
  Output: "3"
  Explanation: 13 base 3 is 111.
  
  Example 2:  
  Input: "4681"
  Output: "8"
  Explanation: 4681 base 8 is 11111.
  
  Example 3:  
  Input: "1000000000000000000"
  Output: "999999999999999999"
  Explanation: 1000000000000000000 base 999999999999999999 is 11.
  
  Note:  
    The range of n is [3, 10^18].
    The string representing n is always valid and will not have leading zeros.
  */

  @Test
  public void test() {
    assertEquals("496", smallestGoodBase("14919921443713777"));
    assertEquals("3", smallestGoodBase("13"));
    assertEquals("8", smallestGoodBase("4681"));
    assertEquals("999999999999999999", smallestGoodBase("1000000000000000000"));
  }

  /*
  n = k^m + k^(m-1) + ... + k + 1
  => n-1 = k^m + k^(m-1) + ... + k
  => n-1 = k (k^(m-1) + k^(m-2) + ... + k + 1) ...... [1]
  Thus n-1 % k == 0
  
  n = k^m + k^(m-1) + ... + k + 1
  => n > k^m 
  => m-th root of n > k ...... [2]
  
  From binomial thorem
  n = k^m + k^(m-1) + ... + k + 1 < (k+1)^m
  => m-th root of n < k + 1 ...... [3]
  
  From [2] and [3], we only need to check m-th root of n
  Cause the minium base is 2, so the range of m is 2->log2(n).
  */

  public String smallestGoodBase(String n) {
    long val = Long.parseLong(n), minusOne = val - 1;
    Set<Integer> roots = new HashSet<>();
    for (int m = 2, size = (int) (Math.log(val) / Math.log(2)), root; m <= size; m++)
      if ((root = (int) (Math.ceil(Math.pow(val, 1d / m))) - 1) > 1 && roots.add(root) && (minusOne) % root == 0
          && isBase(minusOne, root))
        return Integer.toString(root);
    return Long.toString(minusOne);
  }

  private boolean isBase(long val, int base) {
    for (long digit = base; val > 0; digit *= base)
      val -= digit;
    return val == 0;
  }
}
