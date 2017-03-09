package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SparseMatrixMultiplication {

  /*
  Given two sparse matrices A and B, return the result of AB.  
  You may assume that A's column number is equal to B's row number.
  
  Example:
  
  A = [
  [ 1, 0, 0],
  [-1, 0, 3]
  ]
  
  B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
  ]
  
  
       |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
  AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                    | 0 0 1 |
  */

  @Test
  public void test() {
    assertArrayEquals(new int[][] { { 7, 0, 0 }, { -7, 0, 3 } },
        multiply(new int[][] { { 1, 0, 0 }, { -1, 0, 3 } }, new int[][] { { 7, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 } }));
  }

  public int[][] multiply(int[][] A, int[][] B) {
    int aHeight = A.length, aWidth = A[0].length, bWidth = B[0].length, result[][] = new int[aHeight][bWidth];
    for (int i = 0; i < aHeight; i++)
      for (int k = 0; k < aWidth; k++)
        if (A[i][k] != 0) for (int j = 0; j < bWidth; j++)
          if (B[k][j] != 0) result[i][j] += A[i][k] * B[k][j];
    return result;
  }

}
