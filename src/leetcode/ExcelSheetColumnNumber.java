/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class ExcelSheetColumnNumber {

    /*
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    */

    public int titleToNumber(String s) {
        int n = 0;
        for (int i = s.length() - 1, radix = 1; i >= 0; n += (s.charAt(i--) - '@') * radix, radix *= 26);
        return n;
    }

    @Test
    public void test() {
        Assert.assertEquals(28, titleToNumber("AB"));
    }

}
