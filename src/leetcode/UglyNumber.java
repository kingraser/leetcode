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
public class UglyNumber {
    /*
    ugly number：一个数的质因数只有2,3,5（1也是ugly number）
    判断一个数是否是ugly number
     */

    public boolean isUgly(int num) {
        if (num <= 0) return false;
        while (num % 2 == 0)
            num /= 2;
        while (num % 5 == 0)
            num /= 5;
        while (num % 3 == 0)
            num /= 3;
        return num == 1;
    }

    @Test
    public void test() {
        Assert.assertTrue(isUgly(8));
        Assert.assertTrue(isUgly(6));
        Assert.assertFalse(isUgly(14));
    }
}
