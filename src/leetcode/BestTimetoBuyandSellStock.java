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
public class BestTimetoBuyandSellStock {

  /*   
  Say you have an array for which the i-th element is the price of a given stock on day i.
  If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the
  stock), design an algorithm to find the maximum profit.
  */

  @Test
  public void test() {
    assertEquals(6, maxProfit(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
  }

  public int maxProfit(List<Integer> prices) {
    int profit = 0;
    for (int i = 1, min = prices.get(0); i < prices.size(); min = Math.min(min, prices.get(i++)))
      profit = Math.max(profit, prices.get(i) - min);
    return profit;
  }
}
