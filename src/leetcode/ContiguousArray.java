package leetcode;

import static leetcode.MaximumSizeSubarraySumEqualsk.maxSubArrayLen;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ContiguousArray {

  /*
  Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.  
  Example 1:  
  Input: [0,1]
  Output: 2
  Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
  
  Example 2:  
  Input: [0,1,0]
  Output: 2
  Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
  */

  @Test
  public void test() {
    assertEquals(2, findMaxLength(new int[] { 0, 1 }));
    assertEquals(2, findMaxLength(new int[] { 0, 1, 0 }));
  }

  public int findMaxLength(int[] nums) {
    for (int i = 0; i < nums.length; i++)
      if (nums[i] == 0) nums[i] = -1;
    return maxSubArrayLen(nums, 0);
  }

}
