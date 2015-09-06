/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月6日<p>
//-------------------------------------------------------
public class HIndex {

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        for (int i = 0; i < citations.length; i++)
            if (citations[i] >= citations.length - i) return citations.length - i;
        return 0;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, hIndex(new int[] { 3, 0, 6, 1, 5 }));
        Assert.assertEquals(1, hIndex(new int[] { 100 }));
    }
}
