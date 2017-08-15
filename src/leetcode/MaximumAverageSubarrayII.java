package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MaximumAverageSubarrayII {

  /*
  Given an array consisting of n integers, 
  find the contiguous subarray whose length is greater than or equal to k that has the maximum average value. 
  And you need to output the maximum average value.
  
  Example 1:
  Input: [1,12,-5,-6,50,3], k = 4
  Output: 12.75
  Explanation:
  when length is 5, maximum average value is 10.8,
  when length is 6, maximum average value is 9.16667.
  Thus return 12.75.
  
  Note:
    1 <= k <= n <= 10,000.
    Elements of the given array will be in range [-10,000, 10,000].
    The answer with the calculation error less than 10^-5 will be accepted. 
  */

  @Test
  public void test() {
    assertEquals(12.75d, findMaxAverage(new int[] { 1, 12, -5, -6, 50, 3 }, 4), 0.00001d);
  }

  public double findMaxAverage(int[] nums, int k) {
    double max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, prevMid, mid, diff = Integer.MAX_VALUE;
    for (int n : nums) {
      max = Math.max(max, n);
      min = Math.min(min, n);
    }
    for (prevMid = max; diff > 0.00001d; diff = Math.abs(prevMid - mid), prevMid = mid)
      if (check(nums, mid = (max + min) * 0.5, k)) min = mid;
      else max = mid;
    return min;
  }

  public boolean check(int[] nums, double mid, int k) {
    double sum = 0, prev = 0, min_sum = 0;
    for (int i = 0; i < k; sum += nums[i++] - mid);
    if (sum >= 0) return true;
    for (int i = k; i < nums.length; i++)
      if ((sum += nums[i] - mid) >= (min_sum = Math.min((prev += nums[i - k] - mid), min_sum))) return true;
    return false;
  }

}
