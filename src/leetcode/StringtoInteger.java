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
public class StringtoInteger {

    public int myAtoi(String str) {
        str = str.trim();
        for (int i = 0; i < str.length(); i++)
            if (!Character.isDigit(str.charAt(i)) && (i != 0 || (str.charAt(i) != '+' && str.charAt(i) != '-'))) {
                str = str.substring(0, i);
                break;
            }
        if (str.length() == 0 || !Character.isDigit(str.charAt(str.length() - 1))) return 0;
        java.math.BigInteger bigInteger = new java.math.BigInteger(str);
        if (bigInteger.compareTo(new java.math.BigInteger(Integer.toString(Integer.MIN_VALUE))) == -1)
            return Integer.MIN_VALUE;
        if (bigInteger.compareTo(new java.math.BigInteger(Integer.toString(Integer.MAX_VALUE))) == 1)
            return Integer.MAX_VALUE;
        return bigInteger.intValue();
    }

    public int myAtoiII(String str) {
        str = str.trim();
        if (str.length() == 0) return 0;
        long result = 0;
        if (!Character.isDigit(str.charAt(0)) && '-' != str.charAt(0) && '+' != str.charAt(0)) return 0;
        for (int i = !Character.isDigit(str.charAt(0)) ? 1 : 0; i < str.length() && Character.isDigit(str.charAt(i));) {
            result = result * 10 + str.charAt(i++) - '0';
            if (str.charAt(0) == '-' && -result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
            else if (str.charAt(0) != '-' && result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        }
        return (int) ((str.charAt(0) == '-') ? -result : result);
    }

    @Test
    public void test() {
        Assert.assertEquals(2147483647, myAtoiII("2147483648"));
    }

}
