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
        for (int left = 0, right = n - 1, top = 0, bottom = n - 1, count = 1; left <= right && top <= bottom;) {
            for (int i = left; i <= right; ret[top][i++] = count++);
            top++;
            for (int i = top; i <= bottom; ret[i++][right] = count++);
            right--;
            for (int i = right; i >= left; ret[bottom][i--] = count++);
            bottom--;
            for (int i = bottom; i >= top; ret[i--][left] = count++);
            left++;
        }
        return ret;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[][] { { 1, 2, 3 }, { 8, 9, 4 }, { 7, 6, 5 } }, generateMatrix(3));
    }
}
