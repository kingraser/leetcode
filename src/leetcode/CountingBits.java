/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年3月18日;
//-------------------------------------------------------
public class CountingBits {

    public int[] countBits(int num) {
        int[] result = new int[++num];
        for (int i = 1; i < num; i++)
            result[i] = result[i >> 1] + (i & 1);
        return result;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] { 0, 1, 1, 2, 1, 2 }, countBits(5));
    }

}
