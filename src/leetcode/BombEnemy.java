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
    int m = grid.length, n = m > 0 ? grid[0].length : 0;
    int result = 0, rowhits = 0, colhits[] = new int[n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (j == 0 || grid[i][j - 1] == 'W') {
          rowhits = 0;
          for (int k = j; k < n && grid[i][k] != 'W'; k++)
            rowhits += grid[i][k] == 'E' ? 1 : 0;
        }
        if (i == 0 || grid[i - 1][j] == 'W') {
          colhits[j] = 0;
          for (int k = i; k < m && grid[k][j] != 'W'; k++)
            colhits[j] += grid[k][j] == 'E' ? 1 : 0;
        }
        if (grid[i][j] == '0') result = Math.max(result, rowhits + colhits[j]);
      }
    }
    return result;
  }

}
