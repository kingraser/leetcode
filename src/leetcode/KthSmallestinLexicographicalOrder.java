package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class KthSmallestinLexicographicalOrder {

  /*
  Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.  
  Note: 1 ≤ k ≤ n ≤ 10^9.
  Example:  
  Input:
  n: 13   k: 2 
  Output:
  10
  
  Explanation:
  The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10. 
  */

  @Test
  public void test() {
    assertEquals(10, findKthNumber(13, 2));
  }

  public int findKthNumber(int n, int k) {
    int val = 1, steps;
    for (k--; k > 0;)
      if ((steps = calSteps(n, val, val + 1)) <= k) {
        val++;
        k -= steps;
      } else {
        val *= 10;
        k--;
      }
    return val;
  }

  //use long in case of overflow
  public int calSteps(int n, long n1, long n2) {
    int steps = 0;
    while (n1 <= n) {
      steps += Math.min(n + 1, n2) - n1;
      n1 *= 10;
      n2 *= 10;
    }
    return steps;
  }

}
