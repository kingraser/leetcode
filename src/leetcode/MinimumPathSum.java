/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class MinimumPathSum {
    /*
    Given a m x n grid filled with non-negative numbers, 
    find a path from top left to bottom right which minimizes the sum of all numbers along its path.
    
    Note: You can only move either down or right at any point in time.
    */

    public int minPathSum(int[][] grid) {
        for (int i = 1; i < grid[0].length; grid[0][i] += grid[0][i - 1], i++);
        for (int i = 1; i < grid.length; grid[i][0] += grid[i - 1][0], i++);
        for (int i = 1; i < grid.length; i++)
            for (int j = 1; j < grid[0].length; grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]), j++);
        return grid[grid.length - 1][grid[0].length - 1];
    }
}
