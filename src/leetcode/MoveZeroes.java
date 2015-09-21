/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;

import javax.print.attribute.standard.NumberUpSupported;

import org.junit.Assert;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月21日<p>
//-------------------------------------------------------
public class MoveZeroes {

    /*
    Given an array nums, 
    write a function to move all 0's to the end of it 
    while maintaining the relative order of the non-zero elements.
    
    For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
    
    Note:    
        You must do this in-place without making a copy of the array.
        Minimize the total number of operations.    
    */

    //two pointer swap法
    public void moveZeroes(int[] nums) {
        for (int nz = 0, z = 0; nz < nums.length;) {//not-zero and zero
            for (; z < nums.length && nums[z] != 0; z++);
            for (; nz <= z || nz < nums.length && nums[nz] == 0; nz++);
            if (nz < nums.length) {
                nums[z++] = nums[nz];
                nums[nz++] = 0;
            }
        }
    }

    //尾部置零法
    public void moveZeroesII(int[] nums) {
        int i = 0;
        for (int num : nums)
            if (num != 0) nums[i++] = num;
        for (; i < nums.length; nums[i++] = 0);
    }

    @Test
    public void test() {
        int[] actuals = new int[] { 0, 1, 0, 3, 12 };
        moveZeroes(actuals);
        Assert.assertArrayEquals(new int[] { 1, 3, 12, 0, 0 }, actuals);
    }

}
