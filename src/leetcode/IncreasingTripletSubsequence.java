/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年2月16日;
//-------------------------------------------------------
public class IncreasingTripletSubsequence {

  /*
  Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.    
  Formally the function should:
  
  Return true if there exists i, j, k
  such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false. 
  
  Your algorithm should run in O(n) time complexity and O(1) space complexity.
  
  Examples:
  Given [1, 2, 3, 4, 5],
  return true.
  
  Given [5, 4, 3, 2, 1],
  return false. 
  */

  @Test
  public void test() {
    assertTrue(increasingTriplet(new int[] { 2, 1, 5, 0, 4, 6 }));
    assertTrue(increasingTriplet(new int[] { 1, 2, 3, 4, 5 }));
    assertFalse(increasingTriplet(new int[] { 5, 4, 3, 2, 1 }));
  }

  public boolean increasingTriplet(int[] nums) {
    return increasing(nums, 3);
  }

  private boolean increasing(int[] nums, int k) {
    int[] min = new int[--k];
    Arrays.fill(min, Integer.MAX_VALUE);
    A: for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < min.length; j++)
        if (nums[i] < min[j]) {
          min[j] = nums[i];
          continue A;
        }
      return true;
    }
    return false;
  }
}
