/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class RotateArray {

  /*    
  Rotate an array of n elements to the right by k steps.
  
  For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]. 
  */

  public void rotate(int[] nums, int k) {
    k %= nums.length;
    reverse(nums, 0, nums.length - 1 - k);
    reverse(nums, nums.length - k, nums.length - 1);
    reverse(nums, 0, nums.length - 1);
  }

  private void reverse(int[] nums, int i, int j) {
    for (; i < j; swap(nums, i++, j--));
  }

  private void swap(int[] nums, int i, int j) {
    int swap = nums[i];
    nums[i] = nums[j];
    nums[j] = swap;
  }

  @Test
  public void test() {
    int[] expecteds = new int[] { 5, 6, 7, 1, 2, 3, 4 }, actuals = new int[] { 1, 2, 3, 4, 5, 6, 7 };
    rotate(actuals, 3);
    assertArrayEquals(expecteds, actuals);
  }

}
