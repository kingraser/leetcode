/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class NumberofIslands {
  /*
  Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
  An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
  Example 1:    
  11110
  11010
  11000
  00000    
  Answer: 1    
  Example 2:    
  11000
  11000
  00100
  00011    
  Answer: 3
  */

  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) return 0;
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    int islands = 0;
    for (int i = 0; i < grid.length; i++)
      for (int j = 0; j < grid[0].length; j++)
        if (grid[i][j] == '1' && !visited[i][j]) {
          islands++;
          dfs(grid, i, j, visited);
        }
    return islands;
  }

  private int[] dx = new int[] { 1, -1, 0, 0 }, dy = new int[] { 0, 0, 1, -1 };

  private void dfs(char[][] grid, int i, int j, boolean[][] visited) {
    if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || visited[i][j] || grid[i][j] == '0') return;
    visited[i][j] = true;
    for (int k = 0; k < 4; k++)
      dfs(grid, i + dx[k], j + dy[k], visited);
  }

}
