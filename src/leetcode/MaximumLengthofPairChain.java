package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class MaximumLengthofPairChain {

  /*
  You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.  
  Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. 
  Chain of pairs can be formed in this fashion.  
  Given a set of pairs, find the length longest chain which can be formed. 
  You needn't use up all the given pairs. You can select pairs in any order.
  
  Example 1:  
  Input: [[1,2], [2,3], [3,4]]
  Output: 2
  Explanation: The longest chain is [1,2] -> [3,4]
  
  Note:  
    The number of given pairs will be in the range [1, 1000
  */

  @Test
  public void test() {
    assertEquals(2, findLongestChain(new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 } }));
  }

  public int findLongestChain(int[][] pairs) {
    Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
    int count = 0, end = Integer.MIN_VALUE;
    for (int[] pair : pairs)
      if (pair[0] > end) {
        count++;
        end = pair[1];
      }
    return count;
  }

}
