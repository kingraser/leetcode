package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Searcha2DMatrix {

  /*
  Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
  
  Integers in each row are sorted from left to right.
  The first integer of each row is greater than the last integer of the previous row.
  
  For example,
  
  Consider the following matrix:
  
  [
    [1,   3,  5,  7],
    [10, 11, 16, 20],
    [23, 30, 34, 50]
  ]
  
  Given target = 3, return true.
  */

  public boolean searchMatrix(int[][] A, int target) {
    for (int rowCount = A.length, colCount = rowCount > 0 ? A[0].length : 0, start = 0, end = rowCount * colCount
        - 1, mid, value; start <= end;)
      if ((value = A[(mid = (start + end) >> 1) / colCount][mid % colCount]) == target) return true;
      else if (value < target) start = mid + 1;
      else end = mid - 1;
    return false;
  }

  @Test
  public void test() {
    assertTrue(searchMatrix(new int[][] { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 50 } }, 50));
    assertFalse(searchMatrix(new int[][] { { 1 } }, 2));
  }
}
