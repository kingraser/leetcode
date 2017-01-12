/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月17日<p>
//-------------------------------------------------------
public class UniquePathsII {

  /*
  Follow up for "Unique Paths":
      Now consider if some obstacles are added to the grids. How many unique paths would there be?
      An obstacle and empty space is marked as 1 and 0 respectively in the grid.
      For example,
      There is one obstacle in the middle of a 3x3 grid as illustrated below.
  
      [
        [0,0,0],
        [0,1,0],
        [0,0,0]
      ]
  
      The total number of unique paths is 2.
      Note: m and n will be at most 100.
   */

  public int uniquePathsWithObstacles(int[][] o) {
    int[] f = new int[o[0].length];
    if (o[0][0] == 0) f[0] = 1;
    for (int i = 0; i < o.length; i++)
      for (int j = 0; j < o[0].length; f[j] = o[i][j] == 1 ? 0 : (j == 0 ? 0 : f[j - 1]) + f[j], j++);
    return f[o[0].length - 1];
  }

  @Test
  public void test() {
    assertEquals(1, uniquePathsWithObstacles(new int[][] { { 0 } }));
    assertEquals(2, uniquePathsWithObstacles(new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } }));
    assertEquals(0, uniquePathsWithObstacles(new int[][] { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } }));
    assertEquals(0, uniquePathsWithObstacles(new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } }));
  }

}
