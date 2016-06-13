/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class FindPeakElement {

    /*
    For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
    */

    @Test
    public void test() {
        Assert.assertEquals(2, findPeakElement(new int[] { 1, 2, 3, 1 }));
    }

    public int findPeakElement(int[] num) {
        int l = 0, r = num.length - 1, mid;
        while (l < r)
            if (num[mid = (l + r) >> 1] < num[mid + 1]) l = mid + 1;
            else r = mid;
        return l;
    }
}
