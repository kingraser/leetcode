/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class RemoveElement {

    /*
    Given an array and a value, remove all instances of that value in place and return the new length.
    */
    public int removeElement(int[] nums, int val) {
        int begin = 0;
        for (int i : nums)
            if (i != val) nums[begin++] = i;
        return begin;
    }

}
