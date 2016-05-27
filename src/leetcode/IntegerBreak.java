/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年4月22日;
//-------------------------------------------------------
public class IntegerBreak {
    /*
    Given a positive integer n, break it into the sum of at least two positive integers,
    and maximize the product of those integers. 
    Return the maximum product you can get.
    
    For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
    
    Note: you may assume that n is not less than 2. 
    */

    @Test
    public void test() {
        Assert.assertEquals(1, integerBreak(2));
        Assert.assertEquals(2, integerBreak(3));
        Assert.assertEquals(4, integerBreak(4));
        Assert.assertEquals(6, integerBreak(5));
        Assert.assertEquals(9, integerBreak(6));
        Assert.assertEquals(12, integerBreak(7));
        Assert.assertEquals(18, integerBreak(8));
        Assert.assertEquals(27, integerBreak(9));
        Assert.assertEquals(36, integerBreak(10));
    }

    public int integerBreak(int n) {
        if (n < 4) return --n;
        int x = (int) Math.pow(3, n / 3), y = n % 3;
        return y == 0 ? x : y == 1 ? x / 3 * 4 : x * y;
    }

}
