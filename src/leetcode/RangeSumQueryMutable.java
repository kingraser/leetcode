package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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

  /*
  binary indexed tree
  
  C1 = A1
  C2 = A1 + A2
  C3 = A3
  C4 = A1 + A2 + A3 + A4
  C5 = A5
  C6 = A5 + A6
  C7 = A7
  C8 = A1 + A2 + A3 + A4 + A5 + A6 + A7 + A8
  
  1 0001 1  the value of the lowest bit, we can get from x & -x
  2 0010 2  
  3 0011 1  
  4 0100 4  
  5 0101 1  
  6 0110 2  
  7 0111 1  
  8 1000 8
  */

  public class NumArray {
    int[] nums, sums;

    public NumArray(int[] nums) {
      this.nums = new int[nums.length];
      sums = new int[nums.length + 1];
      for (int i = 0; i < nums.length; i++)
        update(i, nums[i]);
    }

    void update(int i, int val) {
      for (int j = i + 1, diff = val - nums[i]; j < sums.length; j += (j & -j))
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
