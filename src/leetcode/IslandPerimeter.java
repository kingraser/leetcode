package leetcode;

import org.junit.Assert;
import org.junit.Test;

public class IslandPerimeter {

  static int[] dRow = new int[] { 1, -1, 0, 0 }, dCol = new int[] { 0, 0, 1, -1 };

  public int islandPerimeter(int[][] grid) {
    for (int i = 0; i < grid.length; i++)
      for (int j = 0; j < grid[0].length; j++)
        if (grid[i][j] == 1) return dfs(grid, i, j);
    return 0;
  }

  private int dfs(int[][] grid, int i, int j) {
    if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] == 0) return 1;
    if (grid[i][j]++ > 1) return 0;
    int result = 0;
    for (int k = 0; k < 4; k++)
      result += dfs(grid, i + dRow[k], j + dCol[k]);
    return result;
  }

  @Test
  public void test() {
    Assert.assertEquals(16,
        islandPerimeter(new int[][] { { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 1, 1, 0, 0 } }));
    Assert.assertEquals(8, islandPerimeter(new int[][] { { 1, 1 }, { 1, 1 } }));
  }
}
