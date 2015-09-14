/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.List;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class BestTimetoBuyandSellStockIII {

    /* 
    Say you have an array for which the i-th element is the price of a given stock on day i.
    Design an algorithm to find the maximum profit. You may complete at most two transactions.
    Note: You may not engage in multiple transactions at the same time 
    (ie, you must sell the stock before you buy again).
    
    设状态 f (i),表示区间 [0, i](0 ≤ i ≤ n − 1) 的最大利润,
    状态 g(i),表示区间 [i, n − 1](0 ≤ i ≤ n − 1) 的最大利润,
    则最终答案为 max {f (i) + g(i)} , 0 ≤ i ≤ n − 1。
    允许在一天内买进又卖出,相当于不交易,因为题目的规定是最多两次,而不是一定要两次。
    将原数组变成差分数组, 本题也可以看做是最大m子段和,m = 2
    */

    public int maxProfit(List<Integer> prices) {
        if (prices.size() < 2) return 0;
        int[] f = new int[prices.size()], g = new int[prices.size()];
        for (int i = 1, valley = prices.get(0); i < prices.size(); valley = Math.min(valley, prices.get(i)), f[i] = Math
                .max(f[i - 1], prices.get(i) - valley), ++i);
        for (int i = prices.size() - 2, peak = prices.get(prices.size() - 1); i >= 0; peak = Math.max(peak,
                prices.get(i)), g[i] = Math.max(g[i], peak - prices.get(i)), --i);
        int max_profit = 0;
        for (int i = 0; i < prices.size(); max_profit = Math.max(max_profit, f[i] + g[i]), ++i);
        return max_profit;
    }

}
