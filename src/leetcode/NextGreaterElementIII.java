package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NextGreaterElementIII {

  /*
  Given a positive 32-bit integer n, 
  you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. 
  If no such positive 32-bit integer exists, you need to return -1.
  
  Example 1:  
  Input: 12
  Output: 21
  
  Example 2:  
  Input: 21
  Output: -1  
  */

  @Test
  public void test() {
    assertEquals(12223233, nextGreaterElement(12222333));
    assertEquals(21, nextGreaterElement(12));
    assertEquals(-1, nextGreaterElement(21));
  }

  public int nextGreaterElement(int n) {
    int[] s = Integer.toString(n).chars().toArray();
    NextPermutation.nextPermutation(s);
    long next = Long.parseLong(new String(s, 0, s.length));
    return next > Integer.MAX_VALUE || next <= n ? -1 : (int) next;
  }

}
