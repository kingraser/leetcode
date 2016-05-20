/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class SingleNumber {

    //Given an array of integers, every element appears twice except for one. Find that single one.
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; res = res ^ nums[i++]);
        return res;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, singleNumber(new int[] { 1, 1, 2, 3, 2 }));
    }

}
