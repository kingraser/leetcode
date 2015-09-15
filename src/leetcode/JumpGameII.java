/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月15日<p>
//-------------------------------------------------------
public class JumpGameII {

    /*
    Given an array of non-negative integers, you are initially positioned at the first index of the array.
    Each element in the array represents your maximum jump length at that position.
    Your goal is to reach the last index in the minimum number of jumps.
    
    For example:
    Given array A = [2,3,1,1,4] 
    The minimum number of jumps to reach the last index is 2. 
    (Jump 1 step from index 0 to 1, then 3 steps to the last index.) 
    */

    public int jump(int[] nums) {
        int step = 0, start = 0, end = 0, newEnd = 0;
        for (; end < nums.length - 1; start = end + 1, end = newEnd, ++step)
            for (int i = start; i <= end; newEnd = Math.max(newEnd, nums[i] + i), ++i);
        return step;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, jump(new int[] { 2, 3, 1, 1, 4 }));
    }

}
