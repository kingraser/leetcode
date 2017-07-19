package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DeleteOperationforTwoStrings {

  /*
  Given two words word1 and word2, 
  find the minimum number of steps required to make word1 and word2 the same, 
  where in each step you can delete one character in either string.
  
  Example 1:  
  Input: "sea", "eat"
  Output: 2
  Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
  
  Note:  
    The length of given words won't exceed 500.
    Characters in given words can only be lower-case letters.  
  */

  @Test
  public void test() {
    assertEquals(2, minDistance("sea", "eat"));
  }

  public int minDistance(String s1, String s2) {
    int[][] dp = new int[s1.length() + 1][s2.length() + 1];
    for (int i = 0; i <= s1.length(); i++)
      for (int j = 0; j <= s2.length(); j++)
        if (i > 0 && j > 0)
          dp[i][j] = s1.charAt(i - 1) == s2.charAt(j - 1) ? 1 + dp[i - 1][j - 1] : Math.max(dp[i - 1][j], dp[i][j - 1]);
    return s1.length() + s2.length() - (dp[s1.length()][s2.length()] << 1);
  }

}
