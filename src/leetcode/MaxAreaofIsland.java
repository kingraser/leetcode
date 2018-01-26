package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MaxAreaofIsland {

  /*
  Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
  You may assume all four edges of the grid are surrounded by water.
  
  Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
  
  Example 1:  
  [[0,0,1,0,0,0,0,1,0,0,0,0,0],
   [0,0,0,0,0,0,0,1,1,1,0,0,0],
   [0,1,1,0,1,0,0,0,0,0,0,0,0],
   [0,1,0,0,1,1,0,0,1,0,1,0,0],
   [0,1,0,0,1,1,0,0,1,1,1,0,0],
   [0,0,0,0,0,0,0,0,0,0,1,0,0],
   [0,0,0,0,0,0,0,1,1,1,0,0,0],
   [0,0,0,0,0,0,0,1,1,0,0,0,0]]  
  Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
  
  Example 2:  
  [[0,0,0,0,0,0,0,0]]  
  Given the above grid, return 0.
  
  Note: The length of each dimension in the given grid does not exceed 50. 
  */

  @Test
  public void test() {
    assertEquals(6,
        maxAreaOfIsland(
            new int[][] { { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 } }));
    assertEquals(0, maxAreaOfIsland(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0 } }));
  }

  public int maxAreaOfIsland(int[][] grid) {
    int result = 0;
    for (int row = 0; row < grid.length; row++)
      for (int col = 0; col < grid[0].length; col++)
        if (grid[row][col] == 1) result = Integer.max(result, dfs(grid, row, col));
    return result;
  }

  public int dfs(int[][] grid, int row, int col) {
    if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1) {
      grid[row][col] = 0;
      return 1 + dfs(grid, row + 1, col) + dfs(grid, row - 1, col) + dfs(grid, row, col - 1) + dfs(grid, row, col + 1);
    } else return 0;
  }
}
