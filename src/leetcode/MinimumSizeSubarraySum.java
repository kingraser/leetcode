/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月18日<p>
//-------------------------------------------------------
public class MinimumSizeSubarraySum {
    /*
    Given an array of n positive integers and a positive integer s, 
    find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.    
    For example, given the array [2,3,1,2,4,3] and s = 7,
    the subarray [4,3] has the minimal length under the problem constraint. 
    */

    @Test
    public void test() {
        Assert.assertEquals(2, minSubArrayLen(7, new int[] { 2, 3, 1, 2, 4, 3 }));
    }

    public int minSubArrayLen(int s, int[] nums) {
        int res = 0;
        for (int i = 0, start = 0, sum = 0; i < nums.length;) {
            if ((sum += nums[i++]) < s) continue;
            for (; sum >= s; sum -= nums[start++]);
            res = res == 0 ? i - start + 1 : Math.min(res, i - start + 1);
        }
        return res;
    }
}
