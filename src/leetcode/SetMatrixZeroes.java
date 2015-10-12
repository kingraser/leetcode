/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class SetMatrixZeroes {
    /*
    Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place. 
    use constant space
    */

    public void setZeroes(int[][] matrix) {
        boolean firstrow = false, firstcolumn = false;//第一行/列是否存在0
        for (int i = 0; i < matrix.length; i++)
            if (0 == matrix[i][0]) {
                firstcolumn = true;
                break;
            }
        for (int j = 0; j < matrix[0].length; j++)
            if (0 == matrix[0][j]) {
                firstrow = true;
                break;
            }
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
