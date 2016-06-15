/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月6日<p>
//-------------------------------------------------------
public class HIndexII {

    //详见HIndex，区别在输入数组已经排序为升序

    public int hIndex(int[] citations) {
        for (int i = 0; i < citations.length; i++)
            if (citations[i] >= citations.length - i) return citations.length - i;
        return 0;
    }

    public int hIndexII(int[] citations) {
        int l = 0, r = citations.length - 1;
        for (int m = (l + r) / 2; l <= r; m = (l + r) / 2)
            if (citations[m] < citations.length - m) l = m + 1;
            else r = m - 1;
        return citations.length - l;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, hIndex(new int[] { 0, 1, 3, 5, 6 }));
        Assert.assertEquals(1, hIndex(new int[] { 100 }));
    }

    @Test
    public void test2() {
        Assert.assertEquals(3, hIndexII(new int[] { 0, 1, 3, 5, 6 }));
        Assert.assertEquals(1, hIndexII(new int[] { 100 }));
        Assert.assertEquals(0, hIndexII(new int[] { 0 }));
        Assert.assertEquals(1, hIndexII(new int[] { 1 }));
        Assert.assertEquals(0, hIndexII(new int[] {}));
    }

}
