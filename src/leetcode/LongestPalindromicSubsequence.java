package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongestPalindromicSubsequence {

  /*
  Given a string s, find the longest palindromic subsequence's length in s. 
  You may assume that the maximum length of s is 1000.
  
  Example 1:
  Input: "bbbab"
  Output: 4  
  One possible longest palindromic subsequence is "bbbb".
  
  Example 2:
  Input: "cbbd"
  Output: 2
  One possible longest palindromic subsequence is "bb". 
  */

  @Test
  public void test() {
    assertEquals(4, longestPalindromeSubseq("bbbab"));
    assertEquals(2, longestPalindromeSubseq("cbbd"));
  }

  public int longestPalindromeSubseq(String s) {
    int length = s.length(), dp[][] = new int[s.length()][s.length()]; // dp[i][j]: the longest palindromic subsequence's length of substring(i, j)
    for (int i = length - 1, j; i >= 0; i--)
      for (dp[i][i] = 1, j = i + 1; j < length; j++)
        if (s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i + 1][j - 1] + 2;
        else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
    return dp[0][s.length() - 1];
  }
}
