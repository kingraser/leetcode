package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class CombinationSumIV {

  /*
  Given an integer array with all positive numbers and no duplicates, 
  find the number of possible combinations that add up to a positive integer target.
  
  Example:  
  nums = [1, 2, 3]
  target = 4
  
  The possible combination ways are:
  (1, 1, 1, 1)
  (1, 1, 2)
  (1, 2, 1)
  (1, 3)
  (2, 1, 1)
  (2, 2)
  (3, 1)
  
  Note that different sequences are counted as different combinations.  
  Therefore the output is 7.
  
  Follow up:
  What if negative numbers are allowed in the given array?
  How does it change the problem?
  What limitation we need to add to the question to allow negative numbers?   
  */

  @Test
  public void test() {
    assertEquals(7, combinationSum4(new int[] { 1, 2, 3 }, 4));
  }

  public int combinationSum4(int[] nums, int target) {
    Arrays.sort(nums);
    int[] dp = new int[target + 1]; // dp[i] is the combination sum of i
    dp[0] = 1;
    for (int sum = 1; sum <= target; sum++)
      for (int num : nums)
        if (sum < num) break;
        else dp[sum] += dp[sum - num];
    return dp[target];
  }
}
