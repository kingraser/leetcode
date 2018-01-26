package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NondecreasingArray {

  /*
  Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.
  
  We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).
  
  Example 1:  
  Input: [4,2,3]
  Output: True
  Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
  
  Example 2:  
  Input: [4,2,1]
  Output: False
  Explanation: You can't get a non-decreasing array by modify at most one element.
  
  Note: The n belongs to [1, 10,000]. 
  */

  @Test
  public void test() {
    assertTrue(checkPossibility(new int[] { 4, 2, 3 }));
    assertFalse(checkPossibility(new int[] { 4, 2, 1 }));
    assertTrue(checkPossibility(new int[] { 3, 4, 2, 4 }));
  }

  public boolean checkPossibility(int[] nums) {
    for (int idx = 1, count = 1; idx < nums.length; idx++)
      if (nums[idx] < nums[idx - 1]) if (count-- == 0) return false;
      else if (idx == 1 || nums[idx] >= nums[idx - 2]) nums[idx - 1] = nums[idx];
      else nums[idx] = nums[idx - 1];
    return true;
  }

}
