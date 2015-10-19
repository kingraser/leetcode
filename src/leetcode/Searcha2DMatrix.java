/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
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

    //O(n+m)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col > -1) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) col--;
            else row++;
        }
        return false;
    }

    //O(log(n*m)) 可将数组视为一维
    public boolean searchMatrixII(int[][] A, int target) {
        if (A == null || A.length == 0 || A[0].length == 0) return false;
        int r = A.length, c = A[0].length, first = 0, last = r * c;
        while (first < last) {
            int mid = (first + last) >> 1, value = A[mid / c][mid % c];
            if (value == target) return true;
            else if (value < target) first = mid + 1;
            else last = mid;
        }
        return false;
    }

    @Test
    public void test() {
        int[][] matrix = new int[][] { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 50 } };
        Assert.assertTrue(searchMatrix(matrix, 10));
        Assert.assertTrue(searchMatrixII(matrix, 10));
        Assert.assertFalse(searchMatrixII(new int[][] { { 1 } }, 2));
    }
}
