/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月13日<p>
//-------------------------------------------------------
public class MaximalRectangle {

  /*
  Given a 2D binary matrix filled with 0's and 1's, 
  find the largest rectangle containing all ones and return its area. 
  */

  /*
          如果矩阵如下
  0010
  0001
  0111
  0011
          显然应该是左上角坐标(2,2),右下角坐标(3,3)的矩形
          判断以(i,j)为矩形左上角，能不能形成一个矩形，能不能形成多个矩形？
          那形成的矩形中，我们能不能找一个最大的呢？
  
  dp[i][j]就是当前的第j列到第i行连续1的个数
          上例的dp为
  0010
  0001
  0112
  0023
          此时问题已经转化为Largest Rectangle in Histogram
  */

  public int maximalRectangle(char[][] m) {
    int area = 0, height = m == null ? 0 : m.length, width = height == 0 ? 0 : m[0].length;
    int A[][] = new int[height][width + 1];
    for (int i = 0; i < height; area = Math.max(area, LargestRectangleinHistogram.largestRectangleArea(A[i++])))
      for (int j = 0; j < width; j++)
        if (m[i][j] == '1') A[i][j] += 1 + (i == 0 ? 0 : A[i - 1][j]);
    return area;
  }

  @Test
  public void test() {
    assertEquals(4, maximalRectangle(
        new char[][] { "0010".toCharArray(), "0001".toCharArray(), "0111".toCharArray(), "0011".toCharArray() }));
  }

}
