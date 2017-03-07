package leetcode;

import static leetcode.util.ArrayUtil.swap;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FirstMissingPositive {

  /*
  Given an unsorted integer array, find the first missing positive integer.
  
  For example,
  Given [1,2,0] return 3,
  and [3,4,-1,1] return 2.
  
  Your algorithm should run in O(n) time and uses constant space. 
  */

  @Test
  public void test() {
    assertEquals(3, firstMissingPositive(new int[] { 1, 2, 0 }));
    assertEquals(2, firstMissingPositive(new int[] { 3, 4, -1, 1 }));
  }

  public int firstMissingPositive(int A[]) {
    for (int i = 0; i < A.length; i++)
      while (A[i] > 0 && A[i] <= A.length && A[A[i] - 1] != A[i])
        swap(A, i, A[i] - 1);
    for (int i = 0; i < A.length; i++)
      if (A[i] != i + 1) return i + 1;
    return A.length + 1;
  }

}
