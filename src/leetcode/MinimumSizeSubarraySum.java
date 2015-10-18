/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

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

    public int minSubArrayLen(int s, int[] nums) {
        int start = 0, sum = 0, res = 0, len;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum < s) continue;
            for (; sum >= s; sum -= nums[start++]);
            len = i - start + 2;
            if (res > len || res == 0) res = len;
        }
        return res;
    }
}
