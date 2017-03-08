package leetcode;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.Test;

public class SetMatrixZeroes {

  /*
  Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place. 
  use constant space
  */

  @Test
  public void test() {
    int[][] input = new int[][] { { 1, 2, 3 }, { 4, 0, 6 }, { 7, 8, 9 } };
    setZeroes(input);
    assertArrayEquals(new int[][] { { 1, 0, 3 }, { 0, 0, 0 }, { 7, 0, 9 } }, input);
  }

  public void setZeroes(int[][] matrix) {
    boolean firstRowHasZero = IntStream.range(0, matrix[0].length).anyMatch(col -> 0 == matrix[0][col]),
        firstColumnHasZero = IntStream.range(0, matrix.length).anyMatch(row -> 0 == matrix[row][0]);
    for (int i = 1; i < matrix.length; i++)
      for (int j = 1; j < matrix[0].length; j++)
        if (0 == matrix[i][j]) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
    for (int i = 1; i < matrix.length; i++)
      for (int j = 1; j < matrix[0].length; j++)
        if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
    if (firstRowHasZero) Arrays.fill(matrix[0], 0);
    if (firstColumnHasZero) for (int i = 0; i < matrix.length; matrix[i++][0] = 0);
  }

}
