/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class BestTimetoBuyandSellStockIII {

  /* 
  Say you have an array for which the i-th element is the price of a given stock on day i.
  Design an algorithm to find the maximum profit. You may complete at most two transactions.
  Note: You may not engage in multiple transactions at the same time 
  (ie, you must sell the stock before you buy again).
  
  let f(i) represents max profit in [0, i](0≤i≤n−1)
      s(i) represents max profit in [i, n − 1](0≤i≤n−1)
  The answer is max{f(i)+g(i)}(0≤i≤n−1)
  */

  @Test
  public void test() {
    assertEquals(6, maxProfit(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
  }

  public int maxProfit(List<Integer> prices) {
    if (prices.size() < 2) return 0;
    int[] f = new int[prices.size()], s = new int[prices.size()];
    for (int i = 1, min = prices.get(0); i < prices.size(); i++) {
      min = Math.min(min, prices.get(i));
      f[i] = Math.max(f[i - 1], prices.get(i) - min);
    }
    for (int i = prices.size() - 2, max = prices.get(i + 1); i >= 0; i--) {
      max = Math.max(max, prices.get(i));
      s[i] = Math.max(s[i], max - prices.get(i));
    }
    int maxProfit = 0;
    for (int i = 0; i < prices.size(); i++)
      maxProfit = Math.max(maxProfit, f[i] + s[i]);
    return maxProfit;
  }

}
