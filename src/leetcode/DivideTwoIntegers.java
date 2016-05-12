/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class DivideTwoIntegers {

    /*
    Divide two integers without using multiplication, division and mod operator.
    If it is overflow, return MAX_INT. 
    
            不能用乘、除和取模,那剩下的,还有加、减和位运算。
            最简单的方法,是不断减去被除数。在这个基础上,可以做一点优化,每次把被除数翻倍,
            从而加速。
    */

    public static int divide(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        long beichu = Math.abs((long) dividend), chushu = Math.abs((long) divisor), res = 0, i = 1;
        while (beichu != 0)
            if (beichu >= chushu) {
                chushu <<= 1;
                i <<= 1;
            } else if (i > 1) {
                i >>= 1;
                chushu >>= 1;
                res += i;
                beichu -= chushu;
                chushu = Math.abs(divisor);
                i = 1;
            } else break;

        if (dividend >= 0 && divisor > 0 || dividend < 0 && divisor < 0)
            return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;
        return -res < Integer.MIN_VALUE ? Integer.MAX_VALUE : (int) -res;
    }
}
