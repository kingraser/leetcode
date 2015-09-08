/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月1日<p>
//-------------------------------------------------------
public class UglyNumberII {

    /*
    找到第n个ugly number
     */

    private static ArrayList<Integer> list = new ArrayList<>(10000);

    static int index2 = 0, index3 = 0, index5 = 0, factor2 = 2, factor3 = 3, factor5 = 5, min = 2;

    static {
        list.add(1);
    }

    public int nthUglyNumber(int n) {
        if (list.size() >= n) return list.get(n - 1);
        for (int i = list.size(); i < n; min = Math.min(Math.min(factor2, factor3), factor5), i++) {
            list.add(min);
            if (factor2 == min) factor2 = 2 * list.get(++index2);
            if (factor3 == min) factor3 = 3 * list.get(++index3);
            if (factor5 == min) factor5 = 5 * list.get(++index5);
        }
        return list.get(list.size() - 1);
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
