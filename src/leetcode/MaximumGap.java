package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Objects;

import org.junit.Test;

public class MaximumGap {

  /*
  Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
  Try to solve it in linear time/space.
  Return 0 if the array contains less than 2 elements.
  You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
  */

  @Test
  public void test() {
    assertEquals(499, maximumGap(new int[] { 1, 10, 5, 2, 1000, 509 }));
  }

  public int maximumGap(int[] nums) {
    if (Objects.isNull(nums) || nums.length < 2) return 0;
    int max = Arrays.stream(nums).max().getAsInt(), min = Arrays.stream(nums).min().getAsInt(),
        bucketLen = (int) Math.ceil(((double) max - min) / (nums.length - 1)),
        bucketCount = (max - min) / bucketLen + 1;
    int[] minBuckets = new int[bucketCount], maxBuckets = new int[bucketCount];
    Arrays.fill(minBuckets, Integer.MAX_VALUE);
    Arrays.fill(maxBuckets, Integer.MIN_VALUE);
    for (int i = 0, idx; i < nums.length; i++) {
      minBuckets[idx = (nums[i] - min) / bucketLen] = Math.min(minBuckets[idx], nums[i]);
      maxBuckets[idx] = Math.max(maxBuckets[idx], nums[i]);
    }
    int maxGap = 0;
    for (int i = 1, prev = maxBuckets[0]; i < bucketCount; i++) {
      if (minBuckets[i] == Integer.MAX_VALUE && maxBuckets[i] == Integer.MIN_VALUE) continue;
      maxGap = Math.max(maxGap, minBuckets[i] - prev);
      prev = maxBuckets[i];
    }
    return maxGap;
  }
}
