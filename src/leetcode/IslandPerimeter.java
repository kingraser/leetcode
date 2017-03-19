package leetcode;

import static leetcode.BattleshipsinaBoard.DIRS;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IslandPerimeter {

  /*
  You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
  
  Example:
  
  [
  [0,1,0,0],
  [1,1,1,0],
  [0,1,0,0],
  [1,1,0,0]]
  
  Answer: 16
  Explanation: The perimeter is the 16 @ stripes in the image below: 
   __ __ __ __
  |__|_@|__|__|
  |_@|_@|_@|__|
  |__|_@|__|__|
  |_@|_@|__|__|  
  */

  public int islandPerimeter(int[][] grid) {
    for (int i = 0; i < grid.length; i++)
      for (int j = 0; j < grid[0].length; j++)
        if (grid[i][j] == 1) return dfs(grid, i, j);
    return 0;
  }

  private int dfs(int[][] grid, int i, int j) {
    if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] == 0) return 1;
    if (grid[i][j]++ > 1) return 0;
    int[] result = new int[1];
    DIRS.forEach(dir -> result[0] += dfs(grid, i + dir[0], j + dir[1]));
    return result[0];
  }

  @Test
  public void test() {
    assertEquals(16, islandPerimeter(new int[][] { { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 1, 1, 0, 0 } }));
    assertEquals(8, islandPerimeter(new int[][] { { 1, 1 }, { 1, 1 } }));
  }
}
