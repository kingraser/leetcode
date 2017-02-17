package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.util.MathUtil;

public class PaintHouse {

  /*
  There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. 
  The cost of painting each house with a certain color is different. 
  You have to paint all the houses such that no two adjacent houses have the same color.
  The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
  For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... 
  Find the minimum cost to paint all houses.
  
  Note:
  All costs are positive integers.
  */
  @Test
  public void test() {
    assertEquals(4, minCost(new int[][] { { 1, 2, 3 }, { 1, 2, 3 }, { 1, 2, 3 } }));
  }

  public static int minCost(int[][] costs) {
    if (costs == null || costs.length == 0 || costs[0].length == 0) return 0;
    int[][] dp = new int[costs.length + 1][costs[0].length];// dp[i][j] represents min cost of the i-th house is painted with color j 
    for (int i = 1; i < dp.length; i++)
      for (int j = 0; j < costs[0].length; j++)
        dp[i][j] = costs[i - 1][j] + Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]);
    return MathUtil.min(dp[costs.length][0], dp[costs.length][1], dp[costs.length][2]);
  }

}
