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
public class BestTimetoBuyandSellStock {

    /*   
    Say you have an array for which the i-th element is the price of a given stock on day i.
    If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the
    stock), design an algorithm to find the maximum profit.
    
    贪心法,分别找到价格最低和最高的一天,低进高出,注意最低的一天要在最高的一天之前。
    把原始价格序列变成差分序列,本题也可以做是最大 m 子段和,m = 1。
    */

    public int maxProfit(List<Integer> prices) {
        if (prices.size() < 2) return 0;
        int profit = 0; // 差价,也就是利润
        for (int i = 1, curMin = prices.get(0); i < prices.size(); profit = Math.max(profit,
                prices.get(i) - curMin), curMin = Math.min(curMin, prices.get(i)), i++);
        return profit;
    }

}
