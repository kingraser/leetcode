/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月1日<p>
//-------------------------------------------------------
public class UglyNumberII {

    /*
            找到第n个ugly number
    */

    private static final int[] primes = new int[] { 2, 3, 5 };

    public int nthUglyNumber(int n) {
        return SuperUglyNumber.nthSuperUglyNumber(n, primes);
    }

    @Test
    public void test() {
        Assert.assertEquals(1, nthUglyNumber(1));
        Assert.assertEquals(2, nthUglyNumber(2));
        Assert.assertEquals(3, nthUglyNumber(3));
        Assert.assertEquals(4, nthUglyNumber(4));
        Assert.assertEquals(5, nthUglyNumber(5));
        Assert.assertEquals(6, nthUglyNumber(6));
        Assert.assertEquals(8, nthUglyNumber(7));
        Assert.assertEquals(9, nthUglyNumber(8));
        Assert.assertEquals(10, nthUglyNumber(9));
    }
}
