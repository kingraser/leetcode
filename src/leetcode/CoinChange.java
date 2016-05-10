/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2015年12月28日;
//-------------------------------------------------------
public class CoinChange {

    /*
    You are given coins of different denominations and a total amount of money amount. 
    Write a function to compute the fewest number of coins that you need to make up that amount. 
    If that amount of money cannot be made up by any combination of the coins, return -1.
    
    Example 1:
    coins = [1, 2, 5], amount = 11
    return 3 (11 = 5 + 5 + 1)
    
    Example 2:
    coins = [2], amount = 3
    return -1.
    
    Note:
    You may assume that you have an infinite number of each kind of coin. 
    */

    @Test
    public void test() {
        Assert.assertEquals(3, coinChange(new int[] { 1, 2, 5 }, 11));
        Assert.assertEquals(-1, coinChange(new int[] { 2 }, 3));
    }

    public int coinChange(int[] coins, int amount) {
        long[] result = new long[amount + 1];
        Arrays.fill(result, 1, result.length, Integer.MAX_VALUE);
        for (int i = 1; i < result.length; i++)
            for (int j = 0; j < coins.length; j++)
                if (i - coins[j] >= 0) result[i] = Math.min(result[i], result[i - coins[j]] + 1);
        return Integer.MAX_VALUE == result[amount] ? -1 : (int) result[amount];
    }

}
