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
// <p>@author wit Initial Created at 2015年9月17日<p>
//-------------------------------------------------------
@SuppressWarnings("unused")
public class UniquePaths {

    /*
    A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).  
    The robot can only move either down or right at any point in time. 
    The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
    How many possible unique paths are there?
    Note: m and n will be at most 100.
    */

    /*
    0 dfs O(n^4)
    1 dp
        设状态为 f[i][j],表示从起点 (1, 1) 到达 (i, j) 的路线条数,
        则状态转移方程为:f[i][j]=f[i-1][j]+f[i][j-1]
    2 math 
        一个 m 行,n 列的矩阵,机器人从左上走到右下总共需要的步数是 m + n − 2,
        其中向下走的步数是 m − 1,因此问题变成了在 m + n − 2 个操作中,
        选择 m–1 个时间点向下走,选择方式有多少种。
        C(m-1,m+n-2);
    */

    public int uniquePathsZero(int m, int n) {
        if (m < 1 || n < 1) return 0; // 终止条件
        if (m == 1 && n == 1) return 1; // 收敛条件
        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }

    // 左边的 f[j],表示更新后的 f[j],与公式中的 f[i[[j] 对应
    // 右边的 f[j],表示老的 f[j],与公式中的 f[i-1][j] 对应
    public int uniquePaths(int m, int n) {
        int[] f = new int[n];
        for (f[0] = 1; m-- > 0;)
            for (int j = 1; j < n; f[j] += f[j - 1], j++);
        return f[n - 1];
    }

    public int uniquePathsII(int m, int n) {
        double a = Math.min(m, n) - 1, b = m + n - 2, result = 1;
        for (; a > 0; result *= (b / a), a -= 1, b -= 1);
        return (int) (result + 0.1);
    }

    @Test
    public void test() {
        Assert.assertEquals(56, uniquePaths(4, 6));
    }

}
