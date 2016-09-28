/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年9月26日;
//-------------------------------------------------------
public class ConvertaNumbertoHexadecimal {

    @Test
    public void testZero() {
        Assert.assertEquals("1a", toHexZero(26));
        Assert.assertEquals("ffffffff", toHexZero(-1));
    }

    @Test
    public void test() {
        Assert.assertEquals("1a", toHex(26));
        Assert.assertEquals("ffffffff", toHex(-1));
    }

    private String toHex(int n) {
        char[] chars = new char[8];
        int idx = 7;
        for (int j; n != 0; n >>>= 4)
            chars[idx--] = (char) ((j = n & 15) < 10 ? '0' + j : 'a' + j - 10);
        return idx == 7 ? "0" : new String(chars, ++idx, 8 - idx);
    }

    public String toHexZero(int num) {
        return Integer.toHexString(num);
    }

}
