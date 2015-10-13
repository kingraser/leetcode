/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class HouseRobber {

    /*
    You are a professional robber planning to rob houses along a street. 
    Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them 
    is that adjacent houses have security system connected 
    and it will automatically contact the police if two adjacent houses were broken into on the same night.
    Given a list of non-negative integers representing the amount of money of each house, 
    determine the maximum amount of money you can rob tonight without alerting the police.
    */

    public int rob(int[] nums) {
        int c = 0, p = 0, cur = 0, i = 0;
        for (; i < nums.length; cur = p + nums[i++], p = Math.max(p, c), c = cur);
        return Math.max(c, p);
    }

    @Test
    public void test() {
        int[] input = new int[] { 1, 1, 3, 4, 5, 7, 8, 9, 10, 11, 31, 32, 47 };
        Assert.assertEquals(105, rob(input));
    }
}
