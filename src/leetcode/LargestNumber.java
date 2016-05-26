/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.math.BigInteger;
import java.util.Arrays;

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
        StringBuilder sb = new StringBuilder();
        Arrays.stream(num).boxed().sorted((i1, i2) -> new BigInteger(String.valueOf(i2) + String.valueOf(i1))
                .compareTo(new BigInteger(String.valueOf(i1) + String.valueOf(i2)))).forEach(i -> sb.append(i));
        return sb.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals("9534330", largestNumber(new int[] { 3, 30, 34, 5, 9 }));
    }

}
