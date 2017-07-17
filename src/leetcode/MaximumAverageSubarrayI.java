package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MaximumAverageSubarrayI {

  /*
  Given an array consisting of n integers, 
  find the contiguous subarray of given length k that has the maximum average value. 
  And you need to output the maximum average value.
  
  Example 1:  
  Input: [1,12,-5,-6,50,3], k = 4
  Output: 12.75
  Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
  
  Note:  
    1 <= k <= n <= 30,000.
    Elements of the given array will be in the range [-10,000, 10,000].  
  */

  @Test
  public void test() {
    assertEquals(12.75d, findMaxAverage(new int[] { 1, 12, -5, -6, 50, 3 }, 4), 0);
  }

  public double findMaxAverage(int[] nums, int k) {
    int sum = 0, max, i;
    for (i = 0; i < k; sum += nums[i++]);
    for (max = sum; i < nums.length; max = Math.max(max, sum += nums[i] - nums[i++ - k]));
    return (double) max / k;
  }

}
