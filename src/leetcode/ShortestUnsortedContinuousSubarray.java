package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ShortestUnsortedContinuousSubarray {

  /*
  Given an integer array, 
  you need to find one continuous subarray that if you only sort this subarray in ascending order, 
  then the whole array will be sorted in ascending order, too.  
  You need to find the shortest such subarray and output its length.
  
  Example 1:  
  Input: [2, 6, 4, 8, 10, 9, 15]
  Output: 5
  Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
  
  Note:  
    Then length of the input array is in range [1, 10,000].
    The input array may contain duplicates, so ascending order here means <=.  
  */

  @Test
  public void test() {
    assertEquals(5, findUnsortedSubarray(new int[] { 2, 6, 4, 8, 10, 9, 15 }));
  }

  public int findUnsortedSubarray(int[] nums) {
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, l, r;
    for (int i = 1; i < nums.length; i++)
      if (nums[i] < nums[i - 1]) min = Math.min(min, nums[i]);
    for (int i = nums.length - 2; i >= 0; i--)
      if (nums[i] > nums[i + 1]) max = Math.max(max, nums[i]);
    for (l = 0; l < nums.length && min >= nums[l]; l++);
    for (r = nums.length - 1; r >= 0 && max <= nums[r]; r--);
    return r - l < 0 ? 0 : r - l + 1;
  }

}
