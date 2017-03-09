package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongestIncreasingPathinaMatrix {

  /*
  Given an integer matrix, find the length of the longest increasing path.    
  From each cell, you can either move to four directions: left, right, up or down. 
  You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
  
  Example 1:    
  nums = [
      [9,9,4],
      [6,6,8],
      [2,1,1]
  ]    
  Return 4
  The longest increasing path is [1, 2, 6, 9].
  
  Example 2:    
  nums = [
      [3,4,5],
      [3,2,6],
      [2,2,1]
  ]    
  Return 4
  The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
  */

  @Test
  public void test() {
    assertEquals(4, longestIncreasingPath(new int[][] { { 3, 4, 5 }, { 3, 2, 6 }, { 2, 2, 1 } }));
    assertEquals(4, longestIncreasingPath(new int[][] { { 9, 9, 4 }, { 6, 6, 8 }, { 2, 1, 1 } }));
  }

  int[] dRow = { 1, -1, 0, 0 }, dCol = { 0, 0, 1, -1 };

  public int longestIncreasingPath(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
    int[][] distance = new int[matrix.length][matrix[0].length];
    int ans = 0;
    for (int row = 0; row < matrix.length; row++)
      for (int col = 0; col < matrix[0].length; col++)
        ans = Math.max(ans, dfs(row, col, matrix, distance));
    return ans;
  }

  private int dfs(int row, int col, int[][] matrix, int[][] distance) {
    if (distance[row][col] != 0) return distance[row][col];
    for (int i = 0, newRow, newCol; i < 4; i++)
      if ((newRow = row + dRow[i]) >= 0 && (newCol = col + dCol[i]) >= 0 && newRow < matrix.length
          && newCol < matrix[0].length && matrix[newRow][newCol] > matrix[row][col])
        distance[row][col] = Math.max(distance[row][col], dfs(newRow, newCol, matrix, distance));
    return ++distance[row][col];
  }

}
