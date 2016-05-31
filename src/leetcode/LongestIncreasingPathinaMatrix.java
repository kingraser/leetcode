/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年1月20日;
//-------------------------------------------------------
public class LongestIncreasingPathinaMatrix {

    /*
    Given an integer matrix, find the length of the longest increasing path.    
    From each cell, you can either move to four directions: left, right, up or down. 
    You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
    
    Example 1:    
    nums = [
        [9,9,4],
        [6,6,8],
        [2,1,1]
    ]    
    Return 4
    The longest increasing path is [1, 2, 6, 9].
    
    Example 2:    
    nums = [
        [3,4,5],
        [3,2,6],
        [2,2,1]
    ]    
    Return 4
    The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
    */

    @Test
    public void test() {
        Assert.assertEquals(4, longestIncreasingPath(new int[][] { { 3, 4, 5 }, { 3, 2, 6 }, { 2, 2, 1 } }));
        Assert.assertEquals(4, longestIncreasingPath(new int[][] { { 9, 9, 4 }, { 6, 6, 8 }, { 2, 1, 1 } }));
    }

    int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        int[][] dis = new int[matrix.length][matrix[0].length];
        int ans = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                ans = Math.max(ans, dfs(i, j, matrix.length, matrix[0].length, matrix, dis));
        return ans;
    }

    int dfs(int x, int y, int m, int n, int[][] matrix, int[][] dis) {
        if (dis[x][y] != 0) return dis[x][y];
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < m && ny < n && matrix[nx][ny] > matrix[x][y])
                dis[x][y] = Math.max(dis[x][y], dfs(nx, ny, m, n, matrix, dis));
        }
        return ++dis[x][y];
    }

}
