/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
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
    
            设状态f(i),表示区间[0, i](0≤i≤n−1)的最大利润,
            状态g(i),表示区间[i, n − 1](0≤i≤n−1)的最大利润,
            则最终答案为 max{f(i)+g(i)},0≤i≤n−1。
            允许在一天内买进又卖出,相当于不交易,因为题目的规定是最多两次,而不是一定要两次。
            将原数组变成差分数组,本题也可以看做是最大m子段和,m=2
    */

    @Test
    public void test() {
        Assert.assertEquals(6, maxProfit(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
    }

    public int maxProfit(List<Integer> prices) {
        if (prices.size() < 2) return 0;
        int[] f = new int[prices.size()], g = new int[prices.size()];
        for (int i = 1, valley = prices.get(0), len = prices.size(); i < len; i++) {
            valley = Math.min(valley, prices.get(i));
            f[i] = Math.max(f[i - 1], prices.get(i) - valley);
        }
        for (int len = prices.size(), i = len - 2, peak = prices.get(i + 1); i > -1; i--) {
            peak = Math.max(peak, prices.get(i));
            g[i] = Math.max(g[i], peak - prices.get(i));
        }
        int maxProfit = 0;
        for (int i = 0, len = prices.size(); i < len; i++)
            maxProfit = Math.max(maxProfit, f[i] + g[i]);
        return maxProfit;
    }

}
