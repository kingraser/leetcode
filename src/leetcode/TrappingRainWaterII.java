package leetcode;

import static leetcode.BattleshipsinaBoard.DIRS;
import static org.junit.Assert.assertEquals;

import java.util.BitSet;
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
    PriorityQueue<Cell> queue = new PriorityQueue<>((c1, c2) -> c1.height - c2.height);
    BitSet visited = new BitSet();
    addEdges(visited, queue, heights);
    int res = 0, i, row, col;
    for (Cell cell; !queue.isEmpty();)
      for (cell = queue.poll(), i = 0; i < DIRS.size(); i++)
        if ((row = cell.row + DIRS.get(i)[0]) >= 0 && row < heights.length && (col = cell.col + DIRS.get(i)[1]) >= 0
            && col < heights[0].length && !visited.get(row * heights[0].length + col)) {
          visited.set(row * heights[0].length + col);
          res += Math.max(0, cell.height - heights[row][col]);
          queue.offer(new Cell(row, col, Math.max(heights[row][col], cell.height)));
        }
    return res;
  }

  private void addEdges(BitSet visited, PriorityQueue<Cell> queue, int[][] heights) {
    for (int height = heights.length, width = height > 0 ? heights[0].length : 0, row = 0; row < height; row++) {
      visited.set(row * width);
      visited.set(row * height + width - 1);
      queue.offer(new Cell(row, 0, heights[row][0]));
      queue.offer(new Cell(row, width - 1, heights[row][width - 1]));
    }
    for (int height = heights.length, width = height > 0 ? heights[0].length : 0, col = 1; col < width - 1; col++) {
      visited.set(col);
      visited.set((height - 1) * width + col);
      queue.offer(new Cell(0, col, heights[0][col]));
      queue.offer(new Cell(height - 1, col, heights[height - 1][col]));
    }
  }

}
