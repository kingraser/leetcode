package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RangeSumQuery2DMutable {
  /*
  Given a 2D matrix matrix, 
  find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
    
  Example:
  
  Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
  ]
  
  sumRegion(2, 1, 4, 3) -> 8
  update(3, 2, 2)
  sumRegion(2, 1, 4, 3) -> 10
  
  Note:  
    The matrix is only modifiable by the update function.
    You may assume the number of calls to update and sumRegion function is distributed evenly.
    You may assume that row1 ≤ row2 and col1 ≤ col2.
  */

  @Test
  public void test() {
    NumMatrix matrix = new NumMatrix(
        new int[][] { { 3, 0, 1, 4, 2 }, { 5, 6, 3, 2, 1 }, { 1, 2, 0, 1, 5 }, { 4, 1, 0, 1, 7 }, { 1, 0, 3, 0, 5 } });
    assertEquals(8, matrix.sumRegion(2, 1, 4, 3));
    matrix.update(3, 2, 2);
    assertEquals(10, matrix.sumRegion(2, 1, 4, 3));
  }

  class NumMatrix {
    private int[][] matrix, sum;

    public NumMatrix(int[][] A) {
      matrix = new int[A.length][A.length];
      sum = new int[A.length + 1][A.length + 1];
      for (int i = 0; i < A.length; i++)
        for (int j = 0; j < A[i].length; j++)
          update(i, j, A[i][j]);
    }

    void update(int row, int col, int val) {
      for (int i = row + 1, diff = val - matrix[row][col]; i < sum.length; i += (i & -i))
        for (int j = col + 1; j < sum[i].length; j += (j & -j))
          sum[i][j] += diff;
      matrix[row][col] = val;
    }

    int sumRegion(int row1, int col1, int row2, int col2) {
      return getSum(++row2, ++col2) - getSum(row1, col2) - getSum(row2, col1) + getSum(row1, col1);
    }

    int getSum(int row, int col) {
      int result = 0;
      for (int i = row; i > 0; i -= (i & -i))
        for (int j = col; j > 0; j -= (j & -j))
          result += sum[i][j];
      return result;
    }
  }
}
