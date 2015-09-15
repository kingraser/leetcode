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
public class ProductofArrayExceptSelf {

    /*
    Given an array of n integers where n > 1, nums, 
    return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].    
    Solve it without division and in O(n).
    For example, given [1,2,3,4], return [24,12,8,6]. 
    */

    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; res[i] = res[i - 1] * nums[i - 1], i++);
        for (int i = nums.length - 1, right = 1; i >= 0; res[i] *= right, right *= nums[i--]);
        return res;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] { 24, 12, 8, 6 }, productExceptSelf(new int[] { 1, 2, 3, 4 }));
    }

}
