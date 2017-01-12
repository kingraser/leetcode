/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class Searcha2DMatrixII {
  /*
  Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:    
  Integers in each row are sorted in ascending from left to right.
  Integers in each column are sorted in ascending from top to bottom.    
  For example,  
  Consider the following matrix:
  
  [
      [1,   4,  7, 11, 15],
      [2,   5,  8, 12, 19],
      [3,   6,  9, 16, 22],
      [10, 13, 14, 17, 24],
      [18, 21, 23, 26, 30]
  ]    
  Given target = 5, return true.
  Given target = 20, return false.
  */

  //O(n+m)
  public boolean searchMatrix(int[][] matrix, int target) {
    for (int i = 0, j = matrix[0].length - 1; i < matrix.length && j >= 0;)
      if (target == matrix[i][j]) return true;
      else if (target < matrix[i][j]) --j;
      else++i;
    return false;
  }

  @Test
  public void test() {
    int[][] matrix = new int[][] { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 }, { 3, 6, 9, 16, 22 },
        { 10, 13, 14, 17, 24 }, { 18, 21, 23, 26, 30 } };
    assertTrue(searchMatrix(matrix, 10));
    assertFalse(searchMatrix(new int[][] { { 1 } }, 2));
  }
}
