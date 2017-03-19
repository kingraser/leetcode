package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import org.junit.Test;

public class ZeroOneMatrix {

  /*
  Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
  The distance between two adjacent cells is 1.
  
  Example 1:
  Input:  
  0 0 0
  0 1 0
  0 0 0  
  Output:  
  0 0 0
  0 1 0
  0 0 0
  
  Example 2:
  Input:  
  0 0 0
  0 1 0
  1 1 1  
  Output:  
  0 0 0
  0 1 0
  1 2 1
  
  Note:  
    The number of elements of the given matrix will not exceed 10,000.
    There are at least one 0 in the given matrix.
    The cells are adjacent in only four directions: up, down, left and right.  
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(0, 1, 0), Arrays.asList(0, 0, 0)),
        updateMatrix(Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(0, 1, 0), Arrays.asList(0, 0, 0))));

    assertEquals(Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(0, 1, 0), Arrays.asList(1, 2, 1)),
        updateMatrix(Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(0, 1, 0), Arrays.asList(1, 1, 1))));
  }

  private static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

  public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
    Deque<int[]> queue = new ArrayDeque<>();
    for (int row = 0, rowCount = matrix.size(); row < rowCount; row++)
      for (int col = 0, colCount = matrix.get(0).size(); col < colCount; col++)
        if (matrix.get(row).get(col) == 0) queue.addLast(new int[] { row, col });
        else matrix.get(row).set(col, Integer.MAX_VALUE);
    for (int cell[], row, col, i; !queue.isEmpty();)
      for (cell = queue.pollFirst(), i = 0; i < 4; i++)
        if ((row = cell[0] + DIRS[i][0]) >= 0 && row < matrix.size() && (col = cell[1] + DIRS[i][1]) >= 0
            && col < matrix.get(0).size() && matrix.get(row).get(col) > matrix.get(cell[0]).get(cell[1]) + 1) {
          queue.addLast(new int[] { row, col });
          matrix.get(row).set(col, matrix.get(cell[0]).get(cell[1]) + 1);
        }
    return matrix;
  }

}
