/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月21日<p>
//-------------------------------------------------------
public class MoveZeroes {

  /*
  Given an array nums, 
  write a function to move all 0's to the end of it 
  while maintaining the relative order of the non-zero elements.
  
  For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
  
  Note:    
      You must do this in-place without making a copy of the array.
      Minimize the total number of operations.    
  */

  public void moveZeroes(int[] nums) {
    int i = 0;
    for (int num : nums)
      if (num != 0) nums[i++] = num;
    Arrays.fill(nums, i, nums.length, 0);
  }

  @Test
  public void test() {
    int[] input = new int[] { 0, 1, 0, 3, 12 }, expected = new int[] { 1, 3, 12, 0, 0 }, actuals;
    moveZeroes(actuals = Arrays.copyOf(input, input.length));
    assertArrayEquals(expected, actuals);
  }

}
