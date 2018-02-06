package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ToeplitzMatrix {

  /*
  A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.
  Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
  
  Example 1:
  Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
  Output: True
  Explanation:
  1234
  5123
  9512
  
  In the above grid, the diagonals are "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]", and in each diagonal all elements are the same, so the answer is True.
  
  Example 2:
  Input: matrix = [[1,2],[2,2]]
  Output: False
  Explanation:
  The diagonal "[1, 2]" has different elements.
  
  Note:
    matrix will be a 2D array of integers.
    matrix will have a number of rows and columns in range [1, 20].
    matrix[i][j] will be integers in range [0, 99].
  */

  @Test
  public void test() {
    assertTrue(isToeplitzMatrix(new int[][] { { 1, 2, 3, 4 }, { 5, 1, 2, 3 }, { 9, 5, 1, 2 } }));
    assertFalse(isToeplitzMatrix(new int[][] { { 1, 2 }, { 2, 2 } }));
  }

  public boolean isToeplitzMatrix(int[][] matrix) {
    for (int row = 0, col = 0; col < matrix[0].length; col++)
      if (!isToeplitzMatrix(matrix, row, col)) return false;
    for (int row = 1, col = 0; row < matrix.length; row++)
      if (!isToeplitzMatrix(matrix, row, col)) return false;
    return true;
  }

  public boolean isToeplitzMatrix(int[][] matrix, int row, int col) {
    for (int value = matrix[row][col]; ++row < matrix.length && ++col < matrix[0].length;)
      if (matrix[row][col] != value) return false;
    return true;
  }
}
