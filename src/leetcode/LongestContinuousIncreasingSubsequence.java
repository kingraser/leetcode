package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongestContinuousIncreasingSubsequence {

  /*
  Given an unsorted array of integers, find the length of longest continuous increasing subsequence.
  
  Example 1:  
  Input: [1,3,5,4,7]
  Output: 3
  Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3. 
  Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4. 
  
  Example 2:  
  Input: [2,2,2,2,2]
  Output: 1
  Explanation: The longest continuous increasing subsequence is [2], its length is 1. 
  
  Note: Length of the array will not exceed 10,000. 
  */

  @Test
  public void test() {
    assertEquals(3, findLengthOfLCIS(new int[] { 1, 3, 5, 4, 7 }));
    assertEquals(1, findLengthOfLCIS(new int[] { 2, 2, 2, 2, 2 }));
  }

  public int findLengthOfLCIS(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    int max = 1, end = nums.length - 1;
    for (int i = 0, len = 1; i < end; max = Math.max(max, len = nums[i] < nums[++i] ? ++len : 1));
    return max;
  }

}
