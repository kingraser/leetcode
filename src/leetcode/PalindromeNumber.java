/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月8日<p>
//-------------------------------------------------------
public class PalindromeNumber {

    /*
    解法一：转字符串，翻转匹配
    解法二：按位匹配
    */

    public boolean isPalindrome(int x) {
        return String.valueOf(x).equals(new StringBuilder().append(x).reverse().toString());
    }

    public boolean isPalindromeII(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        int n = (int) Math.log10(x);
        for (int i = n, a = (int) Math.pow(10, i), b = (int) Math.pow(10, n - i); i > n / 2; i--, a /= 10, b *= 10)
            if (x / a % 10 != x / b % 10) return false;
        return true;
    }

    @Test
    public void test() {
        Assert.assertTrue(isPalindrome(123321));
        Assert.assertTrue(isPalindromeII(123321));
    }

}
