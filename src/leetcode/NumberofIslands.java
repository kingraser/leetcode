package leetcode;

import static leetcode.BattleshipsinaBoard.DIRS;
import static org.junit.Assert.assertEquals;

import java.util.BitSet;

import org.junit.Test;

public class NumberofIslands {

  /*
  Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
  An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
  You may assume all four edges of the grid are all surrounded by water.
  
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

  // dfs
  public int numIslands(char[][] grid) {
    int islands = 0, rowCount = grid.length, colCount = rowCount > 0 ? grid[0].length : 0;
    BitSet visited = new BitSet();
    for (int row = 0; row < rowCount; row++)
      for (int col = 0; col < colCount; col++)
        if (grid[row][col] == '1' && !visited.get(row * colCount + col)) {
          islands++;
          dfs(grid, row, col, visited);
        }
    return islands;
  }

  private void dfs(char[][] grid, int row, int col, BitSet visited) {
    if (row < 0 || row == grid.length || col < 0 || col == grid[0].length || grid[row][col] == '0'
        || visited.get(row * grid[0].length + col))
      return;
    visited.set(row * grid[0].length + col);
    DIRS.forEach(dir -> dfs(grid, row + dir[0], col + dir[1], visited));
  }

}
