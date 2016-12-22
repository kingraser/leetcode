/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年10月10日;
//-------------------------------------------------------
public class PacificAtlanticWaterFlow {
  /*
  Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
  
  Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
  
  Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
  
  Note:
  
    The order of returned grid coordinates does not matter.
    Both m and n are less than 150.
  
  Example:
  
  Given the following 5x5 matrix:
  
  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic
  
  Return:
  
  [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix). 
  */

  int[] dx = new int[] { -1, 1, 0, 0 }, dy = new int[] { 0, 0, 1, -1 };

  public List<int[]> pacificAtlantic(int[][] matrix) {
    List<int[]> result = new ArrayList<>();
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return result;
    boolean[][] pacific = new boolean[matrix.length][matrix[0].length],
        atlantic = new boolean[matrix.length][matrix[0].length];
    for (int i = 0; i < matrix[0].length; dfs(matrix, pacific, 0, i++, Integer.MIN_VALUE));//top
    for (int i = 1; i < matrix.length; dfs(matrix, pacific, i++, 0, Integer.MIN_VALUE));//left
    for (int i = 0; i < matrix.length; dfs(matrix, atlantic, i++, matrix[0].length - 1, Integer.MIN_VALUE));//right
    for (int i = 0; i < matrix[0].length - 1; dfs(matrix, atlantic, matrix.length - 1, i++, Integer.MIN_VALUE));//bottom
    for (int i = 0; i < matrix.length; i++)
      for (int j = 0; j < matrix[0].length; j++)
        if (pacific[i][j] && atlantic[i][j]) result.add(new int[] { i, j });
    return result;
  }

  private void dfs(int[][] A, boolean[][] reach, int i, int j, int height) {
    if (i < 0 || i == A.length || j < 0 || j == A[0].length || reach[i][j] || A[i][j] < height) return;
    reach[i][j] = true;
    for (int k = 0; k < 4; dfs(A, reach, i + dx[k], j + dy[k++], A[i][j]));
  }

  @Test
  public void test() {
    int[][] matrix = new int[][] { new int[] { 1, 2, 2, 3, 5 }, new int[] { 3, 2, 3, 4, 4 },
        new int[] { 2, 4, 5, 3, 1 }, new int[] { 6, 7, 1, 4, 5 }, new int[] { 5, 1, 1, 2, 4 } };
    assertEquals(
        new HashSet<>(Arrays.asList(Arrays.asList(0, 4), Arrays.asList(1, 3), Arrays.asList(1, 4), Arrays.asList(2, 2),
            Arrays.asList(3, 0), Arrays.asList(3, 1), Arrays.asList(4, 0))),
        pacificAtlantic(matrix).stream().map(a -> Arrays.stream(a).boxed().collect(Collectors.toList()))
            .collect(Collectors.toSet()));
  }

}
