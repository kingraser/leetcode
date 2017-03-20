package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BombEnemy {

  /*
  Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), 
  return the maximum enemies you can kill using one bomb.
  The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
  Note that you can only put the bomb at an empty cell.
  
  Example: For the given grid
  
  0 E 0 0
  E 0 W E
  0 E 0 0
  
  return 3. (Placing a bomb at (1,1) kills 3 enemies)
  */

  @Test
  public void test() {
    assertEquals(3,
        maxKilledEnemies(new char[][] { "0E00".toCharArray(), "E0WE".toCharArray(), "0E00".toCharArray() }));
  }

  int maxKilledEnemies(char[][] grid) {
    int rowCount = grid.length, colCount = rowCount > 0 ? grid[0].length : 0, max = 0, rowhits = 0,
        colhits[] = new int[colCount];
    for (int row = 0; row < rowCount; row++)
      for (int col = 0, idx; col < colCount; col++) {
        if (col == 0 || grid[row][col - 1] == 'W') for (rowhits = 0, idx = col; idx < colCount
            && grid[row][idx] != 'W'; rowhits += grid[row][idx++] == 'E' ? 1 : 0);
        if (row == 0 || grid[row - 1][col] == 'W') for (colhits[col] = 0, idx = row; idx < rowCount
            && grid[idx][col] != 'W'; colhits[col] += grid[idx++][col] == 'E' ? 1 : 0);
        if (grid[row][col] == '0') max = Math.max(max, rowhits + colhits[col]);
      }
    return max;
  }

}
