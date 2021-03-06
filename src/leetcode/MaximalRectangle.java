package leetcode;

import static leetcode.LargestRectangleinHistogram.largestRectangleArea;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MaximalRectangle {

  /*
  Given a 2D binary matrix filled with 0's and 1's, 
  find the largest rectangle containing all ones and return its area. 
  */

  /*
  If matrix like below
  0010
  0001
  0111
  0011
  It is obvious that left top(2,2) and right bottom(3,3) is max
  
  Let dp[i][j] represents successive 1 counts for (i,j) to top
  For example
  0010
  0001
  0112
  0023
  Then the problem is transfered to Largest Rectangle in Histogram
  */

  public int maximalRectangle(char[][] matrix) {
    int area = 0, height = matrix == null ? 0 : matrix.length, width = height == 0 ? 0 : matrix[0].length;
    int A[][] = new int[height][width + 1];
    for (int i = 0; i < height; area = Math.max(area, largestRectangleArea(A[i++])))
      for (int j = 0; j < width; j++)
        if (matrix[i][j] == '1') A[i][j] += 1 + (i == 0 ? 0 : A[i - 1][j]);
    return area;
  }

  @Test
  public void test() {
    assertEquals(4, maximalRectangle(
        new char[][] { "0010".toCharArray(), "0001".toCharArray(), "0111".toCharArray(), "0011".toCharArray() }));
  }

}
