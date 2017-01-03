/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月17日<p>
//-------------------------------------------------------
public class UniquePaths {

  /*
  A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).  
  The robot can only move either down or right at any point in time. 
  The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
  How many possible unique paths are there?
  Note: m and n will be at most 100.
  */

  /*
  1 dp
          设状态为f[i][j],表示从起点 (1, 1)到达(i, j)的路线条数,
          则状态转移方程为:f[i][j]=f[i-1][j]+f[i][j-1]
  2 math 
          一个m行,n列的矩阵,机器人从左上走到右下总共需要的步数是 m+n−2,
          其中向下走的步数是m−1,因此问题变成了在m+n−2个操作中,
          选择m–1个时间点向下走,选择方式有多少种。
  C(m-1,m+n-2);
  */

  // 左边的f[j],表示更新后的f[j],与公式中的f[i][j]对应
  // 右边的f[j],表示老的f[j],与公式中的f[i-1][j]对应
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
    assertEquals(56, uniquePaths(4, 6));
    assertEquals(56, uniquePathsII(4, 6));
  }

}
