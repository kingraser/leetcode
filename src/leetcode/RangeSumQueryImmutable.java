package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RangeSumQueryImmutable {
  /*
  Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
  
  Example:  
  Given nums = [-2, 0, 3, -5, 2, -1]
  
  sumRange(0, 2) -> 1
  sumRange(2, 5) -> -1
  sumRange(0, 5) -> -3
  
  Note:  
    You may assume that the array does not change.
    There are many calls to sumRange function.
  */

  @Test
  public void test() {
    NumArray array = new NumArray(new int[] { -2, 0, 3, -5, 2, -1 });
    assertEquals(1, array.sumRange(0, 2));
    assertEquals(-1, array.sumRange(2, 5));
    assertEquals(-3, array.sumRange(0, 5));
  }

  public class NumArray {
    int[] sums;

    public NumArray(int[] nums) {
      sums = nums;
      for (int i = 1; i < nums.length; i++)
        sums[i] += sums[i - 1];
    }

    public int sumRange(int i, int j) {
      return sums[j] - (i == 0 ? 0 : sums[i - 1]);
    }
  }
}
