package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;

import org.junit.Test;

import leetcode.util.MathUtil;

public class EditDistance {

  /*
  Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. 
  (each operation is counted as 1 step.)
  
  You have the following 3 operations permitted on a word:
  
  a) Insert a character
  b) Delete a character
  c) Replace a character
  */

  /*
  Let dp[i][j] represents the minimum edit distance between A[0,i] and B[0,j]
  Let A[0,i] is str1c, B[0,j] is str2d,
  1. if c == d, then dp[i][j] = dp[i-1][j-1];
  2. else c != d,
  (a) replace c with d, then dp[i][j] = dp[i - 1][j - 1] + 1;
  (b) append c with d, then dp[i][j] = dp[i][j - 1] + 1;
  (c) delete c,then dp[i][j] = dp[i - 1][j] + 1;
  */

  @Test
  public void test() {
    assertEquals(10, minDistance("test", "hello world"));
  }

  public int minDistance(String word1, String word2) {
    int[][] dp = new int[word1.length() + 1][word2.length() + 1];
    IntStream.range(0, word1.length() + 1).forEach(i -> dp[i][0] = i);
    IntStream.range(0, word2.length() + 1).forEach(i -> dp[0][i] = i);
    for (int idx1 = 1; idx1 < word1.length() + 1; idx1++)
      for (int idx2 = 1; idx2 < word2.length() + 1; idx2++)
        if (word1.charAt(idx1 - 1) == word2.charAt(idx2 - 1)) dp[idx1][idx2] = dp[idx1 - 1][idx2 - 1];
        else dp[idx1][idx2] = MathUtil.min(dp[idx1 - 1][idx2 - 1], dp[idx1][idx2 - 1], dp[idx1 - 1][idx2]) + 1;
    return dp[word1.length()][word2.length()];
  }
}
