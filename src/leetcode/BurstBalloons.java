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
  Let the last burst balloon m to be a separate point, divide balloons into 2 parts, left and right.
  dp[l][r] = max(dp[l][r], nums[l] * nums[m] * nums[r] + dp[l][m] + dp[m][r])
  */

  public int maxCoins(int[] nums) {
    int len = nums.length, len2 = len + 2;
    int[] newnums = new int[len2];
    for (int i = 0; i < len; i++)
      newnums[i + 1] = nums[i];
    newnums[0] = newnums[len + 1] = 1;
    int[][] DP = new int[len2][len2];
    for (int k = 2; k < len2; k++)
      for (int l = 0; l + k < len2; l++)
        for (int m = l + 1, h = l + k; m < h; m++)
          DP[l][h] = Math.max(DP[l][h], newnums[l] * newnums[m] * newnums[h] + DP[l][m] + DP[m][h]);
    return DP[0][nums.length + 1];
  }
}
