/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

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

    public int maxProfit(int k, int[] prices) {
        if (k >= prices.length / 2) return quickSolve(prices);
        int[][] t = new int[k + 1][prices.length];
        for (int i = 1, tmpMax = -prices[0]; i <= k; tmpMax = -prices[0], i++)
            for (int j = 1; j < prices.length; t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax), tmpMax = Math
                    .max(tmpMax, t[i - 1][j - 1] - prices[j]), j++);
        return t[k][prices.length - 1];
    }

    //BestTimetoBuyandSellStockII
    private int quickSolve(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; profit += Math.max(0, prices[i] - prices[i - 1]), i++);
        return profit;
    }

}
