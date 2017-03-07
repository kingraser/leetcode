package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class RotateImage {

  //Rotate the image by 90 degrees (clockwise).

  @Test
  public void test() {
    int[][] input = new int[][] { { 1, 2 }, { 3, 4 } };
    rotate(input);
    assertArrayEquals(new int[][] { { 3, 1 }, { 4, 2 } }, input);
  }

  public void rotate(int[][] matrix) {
    for (int i = 0, n = matrix.length, limit = (n - 1) / 2; i <= limit; i++)
      for (int j = i; j < n - 1 - i; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[n - 1 - j][i];
        matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
        matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
        matrix[j][n - 1 - i] = temp;
      }
  }
}
