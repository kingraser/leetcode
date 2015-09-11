/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class LengthofLastWord {

    public int lengthOfLastWord(String s) {
        int len = s.length() - 1, lastLength = 0;
        for (; len > -1 && s.charAt(len) == ' '; len--);
        for (; len > -1 && s.charAt(len) != ' '; lastLength++, len--);
        return lastLength;
    }

    @Test
    public void test() {
        String s = "hello world";
        Assert.assertEquals(5, lengthOfLastWord(s));
    }

}
