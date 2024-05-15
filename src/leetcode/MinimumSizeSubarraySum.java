package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinimumSizeSubarraySum {

  /*
  Given an array of n positive integers and a positive integer s, 
  find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.    
  For example, given the array [2,3,1,2,4,3] and s = 7,
  the subarray [4,3] has the minimal length under the problem constraint. 
  */

  @Test
  public void test() {
    assertEquals(2, minSubArrayLen(7, new int[] { 2, 3, 1, 2, 4, 3 }));
  }

  public int minSubArrayLen(int s, int[] nums) {
    int result = 0;
    for (int i = 0, start = 0, sum = 0; i < nums.length;) {
      if ((sum += nums[i++]) < s) continue;
      while (sum >= s) {sum -= nums[start++];}
      result = result == 0 ? i - start + 1 : Math.min(result, i - start + 1);
    }
    return result;
  }
}
