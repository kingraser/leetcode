/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
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
            最大m子段和 m=k
    DP: t(i,j) is the max profit for up to i transactions by time j (0<=i<=K, 0<=j<=T).
    tmpMax means the maximum profit of just doing at most i-1 transactions, 
    using at most first j-1 prices, and buying the stock at price[j] 
    which is used for the next loop.
    */

    @Test
    public void test() {
        int[] prices = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        Assert.assertEquals(6, maxProfit(1, prices));
        Assert.assertEquals(6, maxProfit(2, prices));
        Assert.assertEquals(6, maxProfit(3, prices));
        Assert.assertEquals(6, maxProfit(4, prices));
        Assert.assertEquals(6, maxProfit(5, prices));
        Assert.assertEquals(6, maxProfit(6, prices));
    }

    public int maxProfit(int k, int[] prices) {
        if (k >= (prices.length >> 1)) return quickSolve(prices);
        int[][] t = new int[k + 1][prices.length];
        for (int i = 1; i <= k; i++) {
            int tmpMax = -prices[0];
            for (int j = 1, len = prices.length; j < len; j++) {
                t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
                tmpMax = Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
            }
        }
        return t[k][prices.length - 1];
    }

    //BestTimetoBuyandSellStockII
    private int quickSolve(int[] prices) {
        int profit = 0;
        for (int i = 1, len = prices.length; i < len; i++)
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }

}
