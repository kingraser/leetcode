/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

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
