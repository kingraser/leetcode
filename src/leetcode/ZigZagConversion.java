/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月7日<p>
//-------------------------------------------------------
public class ZigZagConversion {

    /*
            给定字符串PAYPALISHIRING 和行数3
    P       A       H       N
    A   P   L   S   I   I   G
    Y       I       R
    And then read line by line: "PAHNAPLSIIGYIR"
                               
    Δ=2n-2    1                           2n-1                         4n-3
    Δ=        2                     2n-2  2n                    4n-4   4n-2
    Δ=        3               2n-3        2n+1              4n-5       .
    Δ=        .           .               .               .            .
    Δ=        .       n+2                 .           3n               .
    Δ=        n-1 n+1                     3n-3    3n-1                 5n-5
    Δ=2n-2    n                           3n-2                         5n-4
    */

    public String convert(String s, int row) {
        if (row == 1) return s;
        char[] result = new char[s.length()];
        for (int i = 0, k = 0, step = (row - 1) << 1; i < row; i++)
            for (int j = i, i2 = i << 1; j < s.length();) {
                result[k++] = s.charAt(j);
                if ((j += step) - i2 < s.length() && i > 0 && i < row - 1) result[k++] = s.charAt(j - i2);
            }
        return new String(result);
    }

    @Test
    public void test() {
        Assert.assertEquals("PAHNAPLSIIGYIR", convert("PAYPALISHIRING", 3));
    }

}
