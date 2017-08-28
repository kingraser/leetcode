package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class ImageSmoother {

  /*
  Given a 2D integer matrix M representing the gray scale of an image,
  you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8
  surrounding cells and itself.
  If a cell has less than 8 surrounding cells, then use as many as you can.
  
  Example 1:
  Input:
  [[1,1,1],
   [1,0,1],
   [1,1,1]]
  Output:
  [[0, 0, 0],
   [0, 0, 0],
   [0, 0, 0]]
  Explanation:
  For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
  For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
  For the point (1,1): floor(8/9) = floor(0.88888889) = 0
  
  Note:
      The value in the given matrix is in the range of [0, 255].
      The length and width of the given matrix are in the range of [1, 150].
  */

  @Test
  public void test() {
    assertArrayEquals(new int[][] { { 4, 4, 5 }, { 5, 6, 6 }, { 8, 9, 9 }, { 11, 12, 12 }, { 13, 13, 14 } },
        imageSmoother(new int[][] { { 2, 3, 4 }, { 5, 6, 7 }, { 8, 9, 10 }, { 11, 12, 13 }, { 14, 15, 16 } }));
    assertArrayEquals(new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } },
        imageSmoother(new int[][] { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } }));
  }

  public int[][] imageSmoother(int[][] M) {
    for (int row = 0, newRow; row < M.length; row++)
      for (int col = 0, newCol, count, sum, i; col < M[0].length; M[row][col++] |= (sum / count) << 8)
        for (i = -1, count = 0, sum = 0; i < 2; i++)
          if ((newRow = row + i) >= 0 && newRow < M.length) for (int j = -1; j < 2; j++)
            if ((newCol = col + j) >= 0 && newCol < M[0].length) {
              sum += M[newRow][newCol] & 255;
              count++;
            }
    for (int row = 0; row < M.length; row++)
      for (int col = 0; col < M[0].length; col++)
        M[row][col] >>= 8;
    return M;
  }

}