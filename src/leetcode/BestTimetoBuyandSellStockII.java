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
public class BestTimetoBuyandSellStockII {

    /*
    Say you have an array for which the i-th element is the price of a given stock on day i.
    Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
    (ie, buy one and sell one share of the stock multiple times). 
    However, you may not engage in multiple transactions at the same time 
    (ie, you must sell the stock before you buy again).
    
    贪心法,低进高出,把所有正的价格差价相加起来。
    把原始价格序列变成差分序列,本题也可以做是最大 m 子段和,m = 数组长度。
    */

    public int maxProfit(List<Integer> prices) {
        int sum = 0;
        for (int i = 1; i < prices.size(); sum += Math.max(0, prices.get(i) - prices.get(i - 1)), i++);
        return sum;
    }

}
