/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class BestTimetoBuyandSellStockIV {

  /*    
  Say you have an array for which the ith element is the price of a given stock on day i.    
  Design an algorithm to find the maximum profit. You may complete at most k transactions.
  
  Note:
  You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
  */

  /*
  Let t(i, j) is the max profit using i transactions by time j (0<=i<=K, 0<=j<=T)
  t(i, j) = max(t(i, j - 1), max(t(i - 1, k) + prices[j] - prices[k])) i <= k < j  
          = max(t(i, j - 1), prices[j] + max(t(i - 1, k) - prices[k]))
  */

  @Test
  public void test() {
    int[] prices = new int[] { 1, 2, 3, 4, 5, 6, 7 };
    assertEquals(6, maxProfit(1, prices));
    assertEquals(6, maxProfit(2, prices));
    assertEquals(6, maxProfit(3, prices));
    assertEquals(6, maxProfit(4, prices));
    assertEquals(6, maxProfit(5, prices));
    assertEquals(6, maxProfit(6, prices));
  }

  public int maxProfit(int k, int[] prices) {
    if (k >= prices.length >> 1)
      return BestTimetoBuyandSellStockII.maxProfit(Arrays.stream(prices).boxed().collect(Collectors.toList()));
    int[][] t = new int[k + 1][prices.length];
    for (int i = 1; i <= k; i++)
      for (int j = 1, max = -prices[0]; j < prices.length; j++) {
        t[i][j] = Math.max(t[i][j - 1], max + prices[j]);
        max = Math.max(max, t[i - 1][j] - prices[j]);
      }
    return t[k][prices.length - 1];
  }

}
