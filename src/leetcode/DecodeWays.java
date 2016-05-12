/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月14日<p>
//-------------------------------------------------------
public class DecodeWays {
    /*
    A message containing letters from A-Z is being encoded to numbers using the following mapping:
    
    'A' -> 1
    'B' -> 2
    ...
    'Z' -> 26
    
    Given an encoded message containing digits, determine the total number of ways to decode it.
    
    For example,
    Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
    
    The number of ways decoding "12" is 2.   
    */

    /*
            跟Climbing Stairs很类似,不过多加一些判断逻辑。   
    */

    @Test
    public void test() {
        Assert.assertEquals(2, numDecodings("12"));
    }

    public int numDecodings(String s) {
        if (StringUtils.isBlank(s) || s.charAt(0) == '0') return 0;
        int prev = 0, cur = 1;
        for (int i = 1; i <= s.length(); ++i) {
            if (s.charAt(i - 1) == '0') cur = 0;
            if (i < 2 || !(s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6'))) prev = 0;
            int tmp = cur;
            cur = prev + cur;
            prev = tmp;
        }
        return cur;
    }
}
