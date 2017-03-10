package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class ReversePairs {

  /*
  Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2 * nums[j].  
  You need to return the number of important reverse pairs in the given array.
  
  Example1:  
  Input: [1,3,2,3,1]
  Output: 2
  
  Example2:  
  Input: [2,4,3,5,1]
  Output: 3
  
  Note:
    The length of the given array will not exceed 50,000.
    All the numbers in the input array are in the range of 32-bit integer.
  */

  @Test
  public void test() {
    assertEquals(2, reversePairs(new int[] { 1, 3, 2, 3, 1 }));
    assertEquals(3, reversePairs(new int[] { 2, 4, 3, 5, 1 }));
    assertEquals(9, reversePairs(new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE,
        Integer.MIN_VALUE, Integer.MAX_VALUE }));
  }

  public int reversePairs(int[] nums) {
    return mergeSort(nums, 0, nums.length - 1);
  }

  private int mergeSort(int[] nums, int start, int end) {
    if (start >= end) return 0;
    int middle = (start + end) >> 1, count = mergeSort(nums, start, middle) + mergeSort(nums, middle + 1, end);
    for (int i = start, j = middle + 1; i <= middle; count += j - (middle + 1), i++)
      for (; j <= end && nums[i] > nums[j] << 1; j++);
    Arrays.sort(nums, start, end + 1);
    return count;
  }

}
