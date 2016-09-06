/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class SpiralMatrix {
    /*
    Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
    
    For example,
    Given the following matrix:
    
    [
     [ 1, 2, 3 ],
     [ 4, 5, 6 ],
     [ 7, 8, 9 ]
    ]
    
    You should return [1,2,3,6,9,8,7,4,5]. 
    */

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return res;
        for (int top = 0, bottom = matrix.length - 1, left = -1, right = matrix[0].length - 1; top <= bottom
                && left <= right;) {
            for (int i = ++left; i <= right; res.add(matrix[top][i++]));
            for (int i = ++top; i <= bottom; res.add(matrix[i++][right]));
            for (int i = --right; i >= left; res.add(matrix[bottom][i--]));
            for (int i = --bottom; i >= top; res.add(matrix[i--][left]));
        }
        return res;
    }

    @Test
    public void test() {
        Assert.assertEquals(Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5),
                spiralOrder(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }));
    }
}
