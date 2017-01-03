/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.PriorityQueue;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年9月28日;
//-------------------------------------------------------
public class TrappingRainWaterII {

  static final int[] dx = new int[] { -1, 1, 0, 0 }, dy = new int[] { 0, 0, -1, 1 };

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
    int row = heights.length, col = heights[0].length;
    boolean[][] visited = new boolean[row][col];
    addEdges(row, col, visited, queue, heights);
    int res = 0, i, r, c;
    for (Cell cell; !queue.isEmpty();)
      for (cell = queue.poll(), i = 0; i < 4; i++)
        if ((r = cell.row + dx[i]) >= 0 && r < row && (c = cell.col + dy[i]) >= 0 && c < col && !visited[r][c]) {
          visited[r][c] = true;
          res += Math.max(0, cell.height - heights[r][c]);
          queue.offer(new Cell(r, c, Math.max(heights[r][c], cell.height)));
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

  @Test
  public void test() {
    assertEquals(14, trapRainWater(new int[][] { { 12, 13, 1, 12 }, { 13, 4, 13, 12 }, { 13, 8, 10, 12 },
        { 12, 13, 12, 12 }, { 13, 13, 13, 13 } }));
    assertEquals(4, trapRainWater(new int[][] { { 1, 4, 3, 1, 3, 2 }, { 3, 2, 1, 3, 2, 4 }, { 2, 3, 3, 2, 3, 1 } }));
  }
}
