package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import org.junit.Test;

public class ShortestDistancefromAllBuildings {

  /*
  You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. 
  You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:  
    Each 0 marks an empty land which you can pass by freely.
    Each 1 marks a building which you cannot pass through.
    Each 2 marks an obstacle which you cannot pass through.  
  For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):  
  1 - 0 - 2 - 0 - 1
  |   |   |   |   |
  0 - 0 - 0 - 0 - 0
  |   |   |   |   |
  0 - 0 - 1 - 0 - 0  
  The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.  
  Note: There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
  */

  @Test
  public void test() {
    assertEquals(7, shortestDistance(new int[][] { { 1, 0, 2, 0, 1 }, { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 } }));
  }

  int min, dRow[] = new int[] { 1, -1, 0, 0 }, dCol[] = new int[] { 0, 0, 1, -1 };

  public int shortestDistance(int[][] grid) {
    min = -1;
    for (int row = 0, building = 0, total[][] = null; row < grid.length; row++)
      for (int col = 0; col < grid[0].length; col++)
        if (grid[row][col] == 1) {
          bfs(getStartQueue(row, col), grid, total == null ? total = clone(grid) : total, building--);
          if (min == -1) return -1;
        }
    return min;
  }

  private Deque<int[]> getStartQueue(int row, int col) {
    Deque<int[]> deque = new ArrayDeque<>();
    deque.addLast(new int[] { row, col });
    return deque;
  }

  private void bfs(Deque<int[]> deque, int[][] grid, int[][] total, int building) {
    min = -1;
    for (int distance[][] = null, ROW = grid.length, COL = grid[0].length; !deque.isEmpty();)
      for (int i = 0, p[] = deque.pollFirst(), r, c; i < 4; i++)
        if ((r = p[0] + dRow[i]) >= 0 && r < ROW && (c = p[1] + dCol[i]) >= 0 && c < COL && grid[r][c] == building) {
          grid[r][c]--;
          if (distance == null) distance = clone(grid);
          total[r][c] += (distance[r][c] = distance[p[0]][p[1]] + 1) - 1;
          deque.addLast(new int[] { r, c });
          if (min == -1 || min > total[r][c]) min = total[r][c];
        }
  }

  /**
   * @param A
   * @return a deep copy of A
   */
  private int[][] clone(int[][] A) {
    return Arrays.stream(A).map(array -> Arrays.copyOf(array, array.length)).toArray(len -> new int[len][]);
  }
}
