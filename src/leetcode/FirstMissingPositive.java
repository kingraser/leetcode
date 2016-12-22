/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月10日<p>
//-------------------------------------------------------
public class FirstMissingPositive {

  /*
  Given an unsorted integer array, find the first missing positive integer.
  
  For example,
  Given [1,2,0] return 3,
  and [3,4,-1,1] return 2.
  
  Your algorithm should run in O(n) time and uses constant space. 
  */

  public int firstMissingPositive(int[] nums) {
    for (int i = 0; i < nums.length; i++)
      while (nums[i] > 0 && nums[i] - 1 < nums.length && nums[i] - 1 != i) {
        int temp = nums[nums[i] - 1];
        if (temp == nums[i]) break;
        nums[nums[i] - 1] = nums[i];
        nums[i] = temp;
      }
    for (int i = 0; i < nums.length; i++)
      if (i != nums[i] - 1) return i + 1;
    return nums.length + 1;
  }

  @Test
  public void test() {
    assertEquals(3, firstMissingPositive(new int[] { 1, 2, 0 }));
    assertEquals(2, firstMissingPositive(new int[] { 3, 4, -1, 1 }));
  }
}
