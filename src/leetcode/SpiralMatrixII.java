package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SpiralMatrixII {

  /*
  Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
  
  For example,
  Given n = 3,
  You should return the following matrix:
  
  [
   [ 1, 2, 3 ],
   [ 8, 9, 4 ],
   [ 7, 6, 5 ]
  ]
  */

  public int[][] generateMatrix(int n) {
    int[][] ret = new int[n][n];
    for (int left = -1, right = n - 1, top = 0, bottom = n - 1, count = 1; left <= right && top <= bottom;) {
      for (int i = ++left; i <= right; ret[top][i++] = count++);
      for (int i = ++top; i <= bottom; ret[i++][right] = count++);
      for (int i = --right; i >= left; ret[bottom][i--] = count++);
      for (int i = --bottom; i >= top; ret[i--][left] = count++);
    }
    return ret;
  }

  @Test
  public void test() {
    assertArrayEquals(new int[][] { { 1, 2, 3 }, { 8, 9, 4 }, { 7, 6, 5 } }, generateMatrix(3));
  }

}
