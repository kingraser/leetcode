/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年3月25日;
//-------------------------------------------------------
public class BestTimetoBuyandSellStockwithCooldown {
  /*
  Say you have an array for which the ith element is the price of a given stock on day i.    
  Design an algorithm to find the maximum profit. 
  You may complete as many transactions as you like 
  (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
  You may not engage in multiple transactions at the same time 
  (ie, you must sell the stock before you buy again).
  After you sell your stock, you cannot buy stock on next day. 
  (ie, cooldown 1 day)    
  Example:    
  prices = [1, 2, 3, 0, 2]
  maxProfit = 3
  transactions = [buy, sell, cooldown, buy, sell]  
  */

  /*
  There are 3 states full, empty, free.
  
  The route graph is
    
    full + no action -> full
    full + sell -> empty
    
    free + buy -> full
    free + no action -> free
    
    empty + no action -> free
  
  Let full[i] represents max profit in full state at day i.
  Let empty[i] represents max profit in empty state at day i.
  Let free[i] represents max profit in free state at day i.
  
  free[i] = max(free[i-1], empty[i-1])
  full[i] = max(full[i-1], free[i-1] - prices[i]);
  empty[i] = full[i-1] + price[i]
  */

  @Test
  public void test() {
    assertEquals(3, maxProfit(new int[] { 1, 2, 3, 0, 2 }));
  }

  public int maxProfit(int[] prices) {
    int l = prices.length, i = 1;
    int[] free = new int[l], empty = new int[l], full = new int[l];
    for (full[0] = -prices[0]; i < l; i++) {
      free[i] = Math.max(free[i - 1], empty[i - 1]);
      full[i] = Math.max(full[i - 1], free[i - 1] - prices[i]);
      empty[i] = full[i - 1] + prices[i];
    }
    return Math.max(free[l - 1], Math.max(full[l - 1], empty[l - 1]));
  }

}
