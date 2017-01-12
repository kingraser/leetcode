/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年9月1日;
//-------------------------------------------------------
public class RangeSumQueryMutable {
  /*
  Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
  The update(i, val) function modifies nums by updating the element at index i to val.
  
  Example:
  
  Given nums = [1, 3, 5]
  
  sumRange(0, 2) -> 9
  update(1, 2)
  sumRange(0, 2) -> 8
  */

  @Test
  public void test() {
    NumArray numArray = new NumArray(new int[] { 1, 3, 5 });
    assertEquals(9, numArray.sumRange(0, 2));
    numArray.update(1, 2);
    assertEquals(8, numArray.sumRange(0, 2));
  }

  public class NumArray {

    int[] nums, sums;

    public NumArray(int[] nums) {
      this.nums = new int[nums.length];
      sums = new int[nums.length + 1];
      for (int i = 0; i < nums.length; i++)
        update(i, nums[i]);
    }

    void update(int i, int val) {
      for (int j = i + 1, size = nums.length + 1, diff = val - nums[i]; j < size; j += (j & -j))
        sums[j] += diff;
      nums[i] = val;
    }

    public int sumRange(int i, int j) {
      return sum(++j) - sum(i);
    }

    private int sum(int i) {
      int sum = 0;
      for (int j = i; j > 0; j -= (j & -j))
        sum += sums[j];
      return sum;
    }
  }
}
