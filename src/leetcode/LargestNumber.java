/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月15日<p>
//-------------------------------------------------------
public class LargestNumber {

    /* 
    Given a list of non negative integers, arrange them such that they form the largest number.    
    For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.    
    Note: The result may be very large, so you need to return a string instead of an integer.
    */

    public String largestNumber(int[] num) {
        Integer[] nums = new Integer[num.length];
        for (int i = 0; i < num.length; nums[i] = num[i], i++);
        Arrays.sort(nums, new Comparator<Integer>() {

            @Override
            public int compare(Integer i1, Integer i2) {
                return new BigInteger(new StringBuilder().append(i2).append(i1).toString())
                        .compareTo(new BigInteger(new StringBuilder().append(i1).append(i2).toString()));
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; sb.append(nums[i++]));
        return new BigInteger(sb.toString()).toString();
    }

    @Test
    public void test() {
        Assert.assertEquals("9534330", largestNumber(new int[] { 3, 30, 34, 5, 9 }));
    }

}
