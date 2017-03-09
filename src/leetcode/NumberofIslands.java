package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.BitSet;

import org.junit.Test;

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

  @Test
  public void test() {
    assertEquals(1, numIslands(
        new char[][] { "11110".toCharArray(), "11010".toCharArray(), "11000".toCharArray(), "00000".toCharArray() }));
    assertEquals(3, numIslands(
        new char[][] { "11000".toCharArray(), "11000".toCharArray(), "00100".toCharArray(), "00011".toCharArray() }));
  }

  int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
    int islands = 0, rowCount = grid.length, colCount = grid[0].length;
    BitSet visited = new BitSet(rowCount * colCount);
    for (int i = 0; i < grid.length; i++)
      for (int j = 0; j < grid[0].length; j++)
        if (grid[i][j] == '1' && !visited.get(i * colCount + j)) {
          islands++;
          dfs(grid, i, j, visited);
        }
    return islands;
  }

  private void dfs(char[][] grid, int i, int j, BitSet visited) {
    if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] == '0'
        || visited.get(i * grid[0].length + j))
      return;
    visited.set(i * grid[0].length + j);
    Arrays.stream(dirs).forEach(dir -> dfs(grid, i + dir[0], j + dir[1], visited));
  }

}
