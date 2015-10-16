/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class ExcelSheetColumnTitle {
    /*
    Given a positive integer, return its corresponding column title as appear in an Excel sheet.    
    For example:
    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    */

    @Test
    public void test() {
        Assert.assertEquals("AB", convertToTitle(28));
    }

    public String convertToTitle(int n) {
        StringBuilder s = new StringBuilder("");
        for (; n-- > 0; s.append((char) ('A' + n % 26)), n /= 26);
        return s.reverse().toString();
    }
}
