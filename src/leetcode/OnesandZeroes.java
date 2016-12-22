package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OnesandZeroes {

  /*
  In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.
  
  For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.
  
  Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.
  
  Note:
  
    The given numbers of 0s and 1s will both not exceed 100
    The size of given string array won't exceed 600.
  
  Example 1:
  
  Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
  Output: 4
  
  Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
  
  Example 2:
  
  Input: Array = {"10", "0", "1"}, m = 1, n = 1
  Output: 2
  
  Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".  
  */

  @Test
  public void test() {
    assertEquals(4, findMaxForm(new String[] { "10", "0001", "111001", "1", "0" }, 5, 3));
    assertEquals(2, findMaxForm(new String[] { "10", "0", "1" }, 1, 1));
  }

  public int findMaxForm(String[] strs, int m, int n) {
    int[][] dp = new int[m + 1][n + 1];
    for (String s : strs) {
      int zeros = 0, ones = 0;
      for (char c : s.toCharArray())
        if (c == '0') zeros++;
        else ones++;
      for (int i = m; i >= zeros; i--)
        for (int j = n; j >= ones; j--)
          dp[i][j] = Math.max(1 + dp[i - zeros][j - ones], dp[i][j]);
    }
    return dp[m][n];
  }

}
