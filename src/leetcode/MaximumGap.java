/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class MaximumGap {

  /*
  Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
  Try to solve it in linear time/space.
  Return 0 if the array contains less than 2 elements.
  You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
  */
  public int maximumGap(int[] nums) {
    if (nums == null || nums.length < 2) return 0;
    int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length; i++) {//get max and min
      max = Math.max(max, nums[i]);
      min = Math.min(min, nums[i]);
    }
    int bucketLen = (int) Math.ceil(((double) max - min) / (nums.length - 1)),
        bucketCount = (int) ((max - min) / bucketLen + 1);
    int[] minBuckets = new int[bucketCount], maxBuckets = new int[bucketCount];
    Arrays.fill(minBuckets, Integer.MAX_VALUE);
    Arrays.fill(maxBuckets, Integer.MIN_VALUE);
    for (int i = 0; i < nums.length; i++) {
      int b = (int) ((nums[i] - min) / bucketLen);
      minBuckets[b] = Math.min(minBuckets[b], nums[i]);
      maxBuckets[b] = Math.max(maxBuckets[b], nums[i]);
    }
    int maxGap = 0, prev = maxBuckets[0];
    for (int i = 1; i < bucketCount; i++) {
      if (minBuckets[i] == Integer.MAX_VALUE && maxBuckets[i] == Integer.MIN_VALUE) continue;
      maxGap = Math.max(maxGap, minBuckets[i] - prev);
      prev = maxBuckets[i];
    }
    return maxGap;
  }
}
