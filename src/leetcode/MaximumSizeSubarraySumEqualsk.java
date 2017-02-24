package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class MaximumSizeSubarraySumEqualsk {

  /*
  Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
  Example 1:
  Given nums = [1, -1, 5, -2, 3], k = 3,
  return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
  Example 2:
  Given nums = [-2, -1, 2, 1], k = 1,
  return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
  Follow Up:Can you do it in O(n) time?
  */

  @Test
  public void test() {
    assertEquals(4, maxSubArrayLen(new int[] { 1, -1, 5, -2, 3 }, 3));
    assertEquals(2, maxSubArrayLen(new int[] { -2, -1, 2, 1 }, 1));
  }

  public int maxSubArrayLen(int[] nums, int k) {
    int max = 0;
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0, sum = 0; i < nums.length; map.putIfAbsent(sum, i++))
      if ((sum += nums[i]) == k) max = i + 1;
      else max = Math.max(max, i - map.getOrDefault(sum - k, i));
    return max;
  }
}
