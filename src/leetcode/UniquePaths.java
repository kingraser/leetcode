package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UniquePaths {

  /*
  A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).  
  The robot can only move either down or right at any point in time. 
  The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
  How many possible unique paths are there?
  Note: m and n will be at most 100.
  */

  /*
  A matrix with m rows and n columns, we need m+n−2 steps to get to right bottom from left top.
  We need m-1 step down, so is C(m-1,m+n-2).
  */

  public int uniquePaths(int m, int n) {
    double result = 1;
    int a = Math.min(m, n) - 1, b = m + n - 2;
    for (; a > 0; result *= (double) b / a, a--, b--);
    return (int) Math.ceil(result);
  }

  @Test
  public void test() {
    assertEquals(56, uniquePaths(4, 6));
  }

}
