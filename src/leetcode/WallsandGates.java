package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class WallsandGates {

  /*
  You are given a m x n 2D grid initialized with these three possible values.  
    -1  - A wall or an obstacle.
    0   - A gate.
    INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
  
  Fill each empty room with the distance to its nearest gate. 
  If it is impossible to reach a gate, it should be filled with INF.
  
  For example, given the 2D grid:  
  INF  -1  0  INF
  INF INF INF  -1
  INF  -1 INF  -1
  0    -1 INF INF  
  After running your function, the 2D grid should be:  
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
  */

  @Test
  public void test() {
    int[][] actual = new int[][] { { Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE },
        { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1 },
        { Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1 }, { 0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE } };
    wallsAndGates(actual);
    assertArrayEquals(new int[][] { { 3, -1, 0, 1 }, { 2, 2, 1, -1 }, { 1, -1, 2, -1 }, { 0, -1, 3, 4 } }, actual);
  }

  public void wallsAndGates(int[][] rooms) {
    for (int i = 0; i < rooms.length; i++)
      for (int j = 0; j < rooms[0].length; j++)
        if (rooms[i][j] == 0) dfs(rooms, i, j, 0);
  }

  int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

  private void dfs(int[][] rooms, int i, int j, int d) {
    if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] < d) return;
    rooms[i][j] = d;
    for (int[] dir : dirs)
      dfs(rooms, i + dir[0], j + dir[1], d + 1);
  }

}
