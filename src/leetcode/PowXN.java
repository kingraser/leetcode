/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月17日<p>
//-------------------------------------------------------
public class PowXN {
    
    /*
    1作弊
    2二分查找
    */

    public double pow(double x, int n) {
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        double result = 1;
        for (; n > 0; n = n >> 1, x *= x)
            if ((n & 1) == 1) result *= x;
        return result;
    }
}
