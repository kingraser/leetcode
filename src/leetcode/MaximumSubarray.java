package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MaximumSubarray {
  /*
  Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
  
  For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
  the contiguous subarray [4,-1,2,1] has the largest sum = 6. 
  */

  @Test
  public void test() {
    assertEquals(6, maxSubArray(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
  }

  public int maxSubArray(int[] A) {
    int max = Integer.MIN_VALUE;
    for (int i = 0, sum = 0; i < A.length; max = Math.max(max, sum += A[i++]), sum = Math.max(0, sum));
    return max;
  }

}
