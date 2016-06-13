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
            最大m子段和 
    DP: t(i,j) is the max profit for up to i transactions by time j (0<=i<=K, 0<=j<=T)
    t(i, j) = max(t(i, j-1), max(t(i-1, k) + prices[j])) i <= k < j    
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
        int[][] t = new int[k + 1][prices.length];
        for (int i = 1; i <= k; i++)
            for (int j = 1, max = -prices[0]; j < prices.length; j++) {
                t[i][j] = Math.max(t[i][j - 1], max + prices[j]);
                max = Math.max(max, t[i - 1][j - 1] - prices[j]);
            }
        return t[k][prices.length - 1];
    }

}
