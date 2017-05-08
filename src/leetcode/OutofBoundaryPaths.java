package leetcode;

import static leetcode.BattleshipsinaBoard.DIRS;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OutofBoundaryPaths {

  /*
  There is an m by n grid with a ball. 
  Given the start coordinate (i,j) of the ball, 
  you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). 
  However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. 
  The answer may be very large, return it after mod 10^9 + 7.
  
  Example 1:  
  Input:m = 2, n = 2, N = 2, i = 0, j = 0
  Output: 6
  
  Example 2:  
  Input:m = 1, n = 3, N = 3, i = 0, j = 1
  Output: 12
  
  Note:  
    Once you move the ball out of boundary, you cannot move it back.
    The length and height of the grid is in range [1,50].
    N is in range [0,50].  
  */

  @Test
  public void test() {
    assertEquals(6, findPaths(2, 2, 2, 0, 0));
    assertEquals(12, findPaths(1, 3, 3, 0, 1));
  }

  public int findPaths(int m, int n, int N, int i, int j) {
    long[][][] dp = new long[m][n][N + 1];
    for (int move = 1; move <= N; move++)
      for (int row = 0; row < m; row++)
        for (int col = 0; col < n; dp[row][col++][move] %= 10_0000_0007)
          for (int[] dir : DIRS)
            dp[row][col][move] += get(row + dir[0], col + dir[1], move - 1, dp);
    return (int) dp[i][j][N];
  }

  private long get(int row, int col, int move, long[][][] dp) {
    return row < 0 || row == dp.length || col < 0 || col == dp[0].length ? 1 : dp[row][col][move];
  }

}
