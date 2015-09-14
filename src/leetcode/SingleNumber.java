/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class SingleNumber {

    //Given an array of integers, every element appears twice except for one. Find that single one.
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; res = res ^ nums[i], i++);
        return res;
    }

}
