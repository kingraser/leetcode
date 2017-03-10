package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.TreeSet;

import org.junit.Test;

public class MaxSumofSubMatrixNoLargerThanK {

  /*
  Given a non-empty 2D matrix matrix and an integer k, 
  find the max sum of a rectangle in the matrix such that its sum is no larger than k.
  
  Example:  
  Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
  ]
  k = 2
  
  The answer is 2. 
  Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).
  
  Note:
    The rectangle inside the matrix must have an area > 0.
    What if the number of rows is much larger than the number of columns? 
  */

  @Test
  public void test() {
    assertEquals(2, maxSumSubmatrix(new int[][] { { 1, 0, 1 }, { 0, -2, 3 } }, 2));
  }

  public int maxSumSubmatrix(int[][] matrix, int k) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
    int row = matrix.length, col = matrix[0].length, result = Integer.MIN_VALUE;
    for (int left = 0; left < col; left++) {
      int[] sums = new int[row];
      for (int right = left; right < col; right++) {
        for (int i = 0; i < row; i++)
          sums[i] += matrix[i][right];
        TreeSet<Integer> set = new TreeSet<Integer>();
        set.add(0);
        int currSum = 0;
        for (int sum : sums) {
          currSum += sum;
          Integer num = set.ceiling(currSum - k);
          if (num != null) result = Math.max(result, currSum - num);
          set.add(currSum);
        }
      }
    }
    return result;
  }

}
