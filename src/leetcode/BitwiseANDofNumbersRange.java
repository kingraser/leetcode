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
public class BitwiseANDofNumbersRange {

    /*
    Given a range [m, n] where 0 <= m <= n <= 2147483647, 
    return the bitwise AND of all numbers in this range, inclusive.    
    For example, given the range [5, 7], you should return 4.
    
            等价于求m与n二进制编码中同为1的前缀.
    */

    @Test
    public void test() {
        Assert.assertEquals(4, rangeBitwiseAnd(5, 7));
    }

    public int rangeBitwiseAnd(int m, int n) {
        int bit = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            bit++;
        }
        return m << bit;
    }
}
