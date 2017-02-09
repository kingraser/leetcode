package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class DiagonalTraverse {

  /*
  Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
  
  Example:  
  Input:
  [
   [ 1, 2, 3 ],
   [ 4, 5, 6 ],
   [ 7, 8, 9 ]
  ]
  Output:  [1,2,4,7,5,3,6,8,9]
  Explanation:
  
  Note:  
    The total number of elements of the given matrix will not exceed 10,000.
  */

  @Test
  public void test() {
    assertArrayEquals(new int[] { 1, 2, 4, 7, 5, 3, 6, 8, 9 },
        findDiagonalOrder(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }));
  }

  public int[] findDiagonalOrder(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) return new int[0];
    int[] result = new int[matrix.length * matrix[0].length];
    for (int i = 0, row = 0, col = 0, step = -1; i < result.length;) {
      result[i++] = matrix[row][col];
      if ((row += step) == matrix.length) {
        row--;
        col++;
        step = -step;
      } else if ((col -= step) == matrix[0].length) {
        col--;
        row += 2;
        step = -step;
      } else if (row < 0) {
        row = 0;
        step = -step;
      } else if (col < 0) {
        col = 0;
        step = -step;
      }
    }
    return result;
  }

}
