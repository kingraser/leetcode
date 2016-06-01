/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年1月25日;
//-------------------------------------------------------
public class PowerofThree {

    /*
    Given an integer, write a function to determine if it is a power of three. 
    */
    static long maxPowerOfThree = 3;

    static {
        while (maxPowerOfThree < Integer.MAX_VALUE)
            maxPowerOfThree *= 3;
        maxPowerOfThree /= 3;
    }

    @Test
    public void test() {
        Assert.assertTrue(IsPowerOfThree(3));
        Assert.assertTrue(IsPowerOfThree(9));
    }

    public boolean IsPowerOfThree(int n) {
        return n > 0 && (maxPowerOfThree % n == 0);
    }
}
