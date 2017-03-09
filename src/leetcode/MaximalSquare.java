package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.util.MathUtil;

public class MaximalSquare {

  /*
  Given a 2D binary matrix filled with 0's and 1's, 
  find the largest square containing all 1's and return its area.    
  For example, given the following matrix:
  
  1 0 1 0 0
  1 0 1 1 1
  1 1 1 1 1
  1 0 0 1 0
  
  Return 4. 
  */

  /*
  Let A[x][y] represents the length of edges of the largest square with right bottom(x,y).
  A[x][y] = 0 if matrix[x][y] == 0
  A[x][y] = min(A[x - 1][y - 1], A[x][y - 1], A[x - 1][y]) + 1 if matrix[x][y] == 1
  */

  @Test
  public void test() {
    assertEquals(4, maximalSquare(
        new char[][] { "10100".toCharArray(), "10111".toCharArray(), "11111".toCharArray(), "10010".toCharArray() }));
  }

  public int maximalSquare(char[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
    int[][] A = new int[matrix.length][matrix[0].length];
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < matrix.length; i++)
      if (matrix[i][0] == '1') {
        A[i][0] = 1;
        max = 1;
      }
    for (int i = 1; i < matrix[0].length; i++)
      if (matrix[0][i] == '1') {
        A[0][i] = 1;
        max = 1;
      }
    for (int i = 1; i < matrix.length; i++)
      for (int j = 1; j < matrix[0].length; max = Math.max(max, A[i][j++]))
        if (matrix[i][j] == '0') A[i][j] = 0;
        else A[i][j] = 1 + MathUtil.min(A[i - 1][j], A[i - 1][j - 1], A[i][j - 1]);
    return max * max;
  }
}
