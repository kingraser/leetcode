package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Objects;
import java.util.TreeSet;

import org.junit.Test;

public class MaxSumofRectangleNoLargerThanK {

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

  public int maxSumSubmatrix(int[][] matrix, int limit) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
    int result = Integer.MIN_VALUE, rowCount = matrix.length, colCount = matrix[0].length;
    for (int left = 0; left < colCount; left++)
      for (int right = left, rowSums[] = new int[rowCount]; right < colCount; right++) {
        for (int i = 0; i < rowCount; rowSums[i] += matrix[i++][right]);
        if ((result = maxSumSubmatrix(rowSums, new TreeSet<>(), result, limit)) == limit) return limit;
      }
    return result;
  }

  private int maxSumSubmatrix(int[] rows, TreeSet<Integer> set, int res, int k) {
    set.add(0);
    Integer num;
    for (int i = 0, sum = 0; i < rows.length; set.add(sum), i++)
      if (Objects.nonNull(num = set.ceiling((sum += rows[i]) - k)) && (res = Math.max(res, sum - num)) == k) return k;
    return res;
  }

}
