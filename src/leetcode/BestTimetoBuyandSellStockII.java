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
public class BestTimetoBuyandSellStockII {

    /*
    Say you have an array for which the i-th element is the price of a given stock on day i.
    Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
    (ie, buy one and sell one share of the stock multiple times). 
    However, you may not engage in multiple transactions at the same time 
    (ie, you must sell the stock before you buy again).
    
            贪心法,低进高出,把所有正的价格差价相加起来。
            把原始价格序列变成差分序列,本题也可以做是最大m子段和,m = 数组长度。
    */

    @Test
    public void test() {
        Assert.assertEquals(6, maxProfit(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
    }

    public static int maxProfit(List<Integer> prices) {
        int sum = 0;
        for (int i = 0, len = prices.size() - 1; i < len; i++)
            if (prices.get(i++) < prices.get(i)) sum += prices.get(i--) - prices.get(i);
        return sum;
    }

}
