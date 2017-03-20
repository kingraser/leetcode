package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BurstBalloons {

  /*
  Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. 
  You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. 
  Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.  
  Find the maximum coins you can collect by bursting the balloons wisely.
  
  Note:
  (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
  (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
  
  Example:    
  Given [3, 1, 5, 8]    
  Return 167    
  nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
  coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
  */

  @Test
  public void test() {
    assertEquals(167, maxCoins(new int[] { 3, 1, 5, 8 }));
  }

  /*
  Let dp[left][right] is the max coins of range[left, right]
  dp[l][r] = max(dp[l][r], nums[l - 1] * nums[m] * nums[r + 1] + dp[l][m-1] + dp[m+1][r]) (l <= m <= r)
  */

  public int maxCoins(int[] nums) {
    int dp[][] = new int[nums.length][nums.length];
    for (int length = 1; length <= nums.length; length++)
      for (int start = 0; start <= nums.length - length; start++)
        for (int end = start + length - 1, idx = start; idx <= end; idx++)
          dp[start][end] = Math.max(dp[start][end], nums[idx] * getValue(nums, start - 1) * getValue(nums, end + 1)
              + getValue(dp, start, idx - 1) + getValue(dp, idx + 1, end));
    return dp[0][nums.length - 1];
  }

  private int getValue(int[][] nums, int row, int col) {
    return row < 0 || row == nums.length || col < 0 || col == nums[0].length ? 0 : nums[row][col];
  }

  private int getValue(int[] nums, int idx) {
    return idx < 0 || idx == nums.length ? 1 : nums[idx];
  }
}
