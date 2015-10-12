/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

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
        List<Integer> res = Lists.newArrayList();
        if (matrix == null || matrix.length == 0) return res;
        for (int rowBegin = 0, rowEnd = matrix.length - 1, colBegin = 0, colEnd = matrix[0].length
                - 1; rowBegin <= rowEnd && colBegin <= colEnd;) {
            for (int i = colBegin; i <= colEnd; res.add(matrix[rowBegin][i++]));
            rowBegin++;
            for (int i = rowBegin; i <= rowEnd; res.add(matrix[i++][colEnd]));
            colEnd--;
            for (int i = colEnd; i >= colBegin; res.add(matrix[rowEnd][i--]));
            rowEnd--;
            for (int i = rowEnd; i >= rowBegin; res.add(matrix[i--][colBegin]));
            colBegin++;
        }
        return res;
    }

    @Test
    public void test() {
        Assert.assertEquals(Lists.newArrayList(1, 2, 3, 6, 9, 8, 7, 4, 5),
                spiralOrder(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }));
    }
}
