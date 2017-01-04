/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月2日<p>
//-------------------------------------------------------
public class MissingNumber {

  /*
  Give an array 0, 1, 2, ..., n, remove a number, find it. 
  For example
  Given [0, 1, 3] return 2. 
  */

  public int missingNumber(int[] nums) {
    Arrays.sort(nums);
    int left = 0, right = nums.length, mid;
    while (left < right)
      if (nums[mid = (left + right) >> 1] > mid) right = mid;
      else left = mid + 1;
    return left;
  }

  @Test
  public void test() {
    assertEquals(2, missingNumber(new int[] { 0, 1, 3 }));
  }
}
