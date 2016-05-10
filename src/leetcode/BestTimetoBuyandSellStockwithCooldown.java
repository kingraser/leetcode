/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
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
    Define buy[i] as the max profit when you buy the stock at day i. 
    sell[i] as the max profit when you sell the stock at day i. 
    
    buy[i]  = max(sell[i-2] - price, buy[i-1]) 
    sell[i] = max(buy[i-1] + price, sell[i-1])
    */

    @Test
    public void test() {
        Assert.assertEquals(3, maxProfit(new int[] { 1, 2, 3, 0, 2 }));
    }

    public int maxProfit(int[] prices) {
        int buy = Integer.MIN_VALUE, preBuy = 0, sell = 0, preSell = 0;
        for (int price : prices) {
            preBuy = buy;
            buy = Math.max(preSell - price, preBuy);
            preSell = sell;
            sell = Math.max(preBuy + price, preSell);
        }
        return sell;
    }

}
