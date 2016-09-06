/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class SetMatrixZeroes {
    /*
    Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place. 
    use constant space
    */

    @Test
    public void test() {
        int[][] expected = new int[][] { { 1, 0, 3 }, { 0, 0, 0 }, { 7, 0, 9 } },
                input = new int[][] { { 1, 2, 3 }, { 4, 0, 6 }, { 7, 8, 9 } };
        setZeroes(input);
        Assert.assertArrayEquals(expected, input);
    }

    public void setZeroes(int[][] matrix) {
        boolean firstrow = false, firstcolumn = false;//第一行/列是否存在0
        for (int i = 0; i < matrix.length && !(firstcolumn = 0 == matrix[i++][0]););
        for (int j = 0; j < matrix[0].length && !(firstrow = 0 == matrix[0][j++]););
        for (int i = 1; i < matrix.length; i++)
            for (int j = 1; j < matrix[0].length; j++)
                if (0 == matrix[i][j]) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
        for (int i = 1; i < matrix.length; i++)
            for (int j = 1; j < matrix[0].length; j++)
                if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
        if (firstrow) Arrays.fill(matrix[0], 0);
        if (firstcolumn) for (int i = 0; i < matrix.length; matrix[i++][0] = 0);
    }

}
