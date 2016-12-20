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
public class BestTimetoBuyandSellStockII {

  /*
  Say you have an array for which the i-th element is the price of a given stock on day i.
  Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
  (ie, buy one and sell one share of the stock multiple times). 
  However, you may not engage in multiple transactions at the same time 
  (ie, you must sell the stock before you buy again).
  */

  @Test
  public void test() {
    assertEquals(6, maxProfit(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
  }

  public static int maxProfit(List<Integer> prices) {
    int sum = 0;
    for (int i = 1; i < prices.size(); i++)
      sum += Math.max(0, prices.get(i) - prices.get(i - 1));
    return sum;
  }

}
