package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class LongestLineofConsecutiveOneinMatrix {

  /*
  Given a 01 matrix M, find the longest line of consecutive one in the matrix. 
  The line could be horizontal, vertical, diagonal or anti-diagonal.
  
  Example:
  Input:
  [
    [0,1,1,0],
    [0,1,1,0],
    [0,0,0,1]
  ]
  Output: 3
  
  Note: The number of elements in the given matrix will not exceed 10,000. 
  */

  @Test
  public void test() {
    assertEquals(3, longestLine(new int[][] { { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 1 } }));
  }

  public int longestLine(int[][] M) {
    int result = 0, dp[][][] = new int[M.length][M[0].length][4];
    for (int row = 0; row < M.length; row++)
      for (int col = 0; col < M[0].length; col++)
        if (M[row][col] == 1) {
          Arrays.fill(dp[row][col], 1);
          if (col > 0 && M[row][col - 1] == 1) dp[row][col][0] += dp[row][col - 1][0]; // horizontal line
          if (col > 0 && row > 0 && M[row - 1][col - 1] == 1) dp[row][col][1] += dp[row - 1][col - 1][1]; // diagonal line
          if (row > 0 && M[row - 1][col] == 1) dp[row][col][2] += dp[row - 1][col][2]; // vertical line
          if (col < M[0].length - 1 && row > 0 && M[row - 1][col + 1] == 1) dp[row][col][3] += dp[row - 1][col + 1][3]; // anti-diagonal line
          result = Math.max(result, Arrays.stream(dp[row][col]).max().getAsInt());
        }
    return result;
  }

}
