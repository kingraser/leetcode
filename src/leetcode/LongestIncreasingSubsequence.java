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
