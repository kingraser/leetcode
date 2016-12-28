/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年10月11日;
//-------------------------------------------------------
public class LongestIncreasingSubsequence {

  /*
  Given an unsorted array of integers, find the length of longest increasing subsequence.
  
  For example,
  Given [10, 9, 2, 5, 3, 7, 101, 18],
  The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.
  
  Your algorithm should run in O(n^2) complexity.
  
  Follow up: Could you improve it to O(n log n) time complexity? 
  */
  public int lengthOfLIS(int[] nums) {
    int len = 0;
    int[] dp = new int[nums.length];
    for (int i : nums) {
      int idx = Arrays.binarySearch(dp, 0, len, i);
      if (idx < 0) idx = ~idx;
      dp[idx] = i;
      if (idx == len) len++;
    }
    return len;
  }

  @Test
  public void test() {
    assertEquals(4, lengthOfLIS(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 }));
  }
}
