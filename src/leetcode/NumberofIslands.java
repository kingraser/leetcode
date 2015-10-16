/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class NumberofIslands {
    /*
    Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
    An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
    Example 1:    
    11110
    11010
    11000
    00000    
    Answer: 1    
    Example 2:    
    11000
    11000
    00100
    00011    
    Answer: 3
    */

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int islands = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == '1' && !visited[i][j]) {
                    islands++;
                    check(grid, i, j, visited);
                }
        return islands;
    }

    private void check(char[][] grid, int i, int j, boolean[][] visited) {
        if (i > -1 && i < grid.length && j > -1 && j < grid[0].length && grid[i][j] == '1') {
            visited[i][j] = true;
            if (!visited[i + 1][j]) check(grid, i + 1, j, visited);
            if (!visited[i - 1][j]) check(grid, i - 1, j, visited);
            if (!visited[i][j + 1]) check(grid, i, j + 1, visited);
            if (!visited[i][j - 1]) check(grid, i, j - 1, visited);
        }
    }

}
