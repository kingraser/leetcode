package leetcode;

import static leetcode.BattleshipsinaBoard.DIRS;
import static org.junit.Assert.assertEquals;

import java.util.PriorityQueue;

import org.junit.Test;

public class TrappingRainWaterII {

  /*
  Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, 
  compute the volume of water it is able to trap after raining.  
  Note:
  Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.
  
  Example:  
  Given the following 3x6 height map:
  [
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
  ]  
  Return 4.
  */

  @Test
  public void test() {
    assertEquals(14, trapRainWater(new int[][] { { 12, 13, 1, 12 }, { 13, 4, 13, 12 }, { 13, 8, 10, 12 },
        { 12, 13, 12, 12 }, { 13, 13, 13, 13 } }));
    assertEquals(4, trapRainWater(new int[][] { { 1, 4, 3, 1, 3, 2 }, { 3, 2, 1, 3, 2, 4 }, { 2, 3, 3, 2, 3, 1 } }));
  }

  public class Cell {
    public int row, col, height;

    public Cell(int row, int col, int height) {
      this.row = row;
      this.col = col;
      this.height = height;
    }
  }

  public int trapRainWater(int[][] heights) {
    if (heights == null || heights.length == 0 || heights[0].length == 0) return 0;
    PriorityQueue<Cell> queue = new PriorityQueue<>((c1, c2) -> c1.height - c2.height);
    int rowCount = heights.length, colCount = heights[0].length;
    boolean[][] visited = new boolean[rowCount][colCount];
    addEdges(rowCount, colCount, visited, queue, heights);
    int res = 0, i, row, col;
    for (Cell cell; !queue.isEmpty();)
      for (cell = queue.poll(), i = 0; i < 4; i++)
        if ((row = cell.row + DIRS.get(i)[0]) >= 0 && row < rowCount && (col = cell.col + DIRS.get(i)[1]) >= 0
            && col < colCount && !visited[row][col]) {
          visited[row][col] = true;
          res += Math.max(0, cell.height - heights[row][col]);
          queue.offer(new Cell(row, col, Math.max(heights[row][col], cell.height)));
        }
    return res;
  }

  private void addEdges(int row, int col, boolean[][] visited, PriorityQueue<Cell> queue, int[][] heights) {
    for (int i = 0; i < row; i++) {
      visited[i][0] = true;
      visited[i][col - 1] = true;
      queue.offer(new Cell(i, 0, heights[i][0]));
      queue.offer(new Cell(i, col - 1, heights[i][col - 1]));
    }
    for (int i = 1; i < col - 1; i++) {
      visited[0][i] = true;
      visited[row - 1][i] = true;
      queue.offer(new Cell(0, i, heights[0][i]));
      queue.offer(new Cell(row - 1, i, heights[row - 1][i]));
    }
  }

}
