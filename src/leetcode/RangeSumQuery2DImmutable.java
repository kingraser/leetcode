package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RangeSumQuery2DImmutable {

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
  sumRegion(1, 1, 2, 2) -> 11
  sumRegion(1, 2, 2, 4) -> 12
  
  Note:
    You may assume that the matrix does not change.
    There are many calls to sumRegion function.
    You may assume that row1 ≤ row2 and col1 ≤ col2.
  */

  @Test
  public void test() {
    NumMatrix matrix = new NumMatrix(
        new int[][] { { 3, 0, 1, 4, 2 }, { 5, 6, 3, 2, 1 }, { 1, 2, 0, 1, 5 }, { 4, 1, 0, 1, 7 }, { 1, 0, 3, 0, 5 } });
    assertEquals(8, matrix.sumRegion(2, 1, 4, 3));
    assertEquals(11, matrix.sumRegion(1, 1, 2, 2));
    assertEquals(12, matrix.sumRegion(1, 2, 2, 4));
  }

  public class NumMatrix {
    int[][] A;

    public NumMatrix(int[][] matrix) {
      A = matrix;
      if (A.length == 0) return;
      for (int i = 1; i < A.length; A[i][0] += A[i++ - 1][0]);
      for (int i = 1; i < A[0].length; A[0][i] += A[0][i++ - 1]);
      for (int i = 1; i < A.length; i++)
        for (int j = 1; j < A[0].length; A[i][j] += A[i - 1][j] + A[i][j - 1] - A[i - 1][j++ - 1]);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
      return A[row2][col2] - (row1 > 0 ? A[row1 - 1][col2] : 0) - (col1 > 0 ? A[row2][col1 - 1] : 0)
          + (row1 > 0 && col1 > 0 ? A[row1 - 1][col1 - 1] : 0);
    }
  }

}
