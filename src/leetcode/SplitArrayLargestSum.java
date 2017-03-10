package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class SplitArrayLargestSum {

  /*
  Given an array which consists of non-negative integers and an integer m, 
  you can split the array into m non-empty continuous subarrays. 
  Write an algorithm to minimize the largest sum among these m subarrays.
  Note: Given m satisfies the following constraint: 1 ≤ m ≤ length(nums) ≤ 14,000.
  
  Examples:  
  Input: nums = [7,2,5,10,8]
  m = 2  
  Output:
  18
  
  Explanation:
  There are four ways to split nums into two subarrays.
  The best way is to split it into [7,2,5] and [10,8],
  where the largest sum among the two subarrays is only 18.
  */

  @Test
  public void test() {
    assertEquals(5, splitArray(new int[] { 1, 2, 3, 4, 5 }, 5));
    assertEquals(15, splitArray(new int[] { 1, 2, 3, 4, 5 }, 1));
    assertEquals(4, splitArray(new int[] { 2, 3, 1, 2, 4, 3 }, 5));
    assertEquals(18, splitArray(new int[] { 7, 2, 5, 10, 8 }, 2));
  }

  public int splitArray(int[] nums, int m) {
    int max = Arrays.stream(nums).max().getAsInt(), sum = Arrays.stream(nums).sum(), left, right, mid;
    for (left = max, right = sum; left <= right;)
      if (canSplit(nums, m, mid = left + ((right - left) >> 1))) right = mid - 1;
      else left = mid + 1;
    return left;
  }

  private boolean canSplit(int[] A, int m, int limit) {
    int count = 0, sum = 0, i = 0;
    while (i < A.length && count < m)
      if ((sum += A[i]) > limit) {
        count++;
        sum = 0;
      } else i++;
    return (i != A.length || sum > 0 ? ++count : count) <= m;
  }

}
