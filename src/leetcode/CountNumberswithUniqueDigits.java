/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年6月15日;
//-------------------------------------------------------
public class CountNumberswithUniqueDigits {

    /*
    Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10^n.
    
    Example:
    Given n = 2, return 91. 
    The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99] 
    */

    /*
    Let f(k) = count of numbers with unique digits with length equals k.
    f(1) = 10, ..., f(k) = 9 * 9 * 8 * ... (9 - k + 2) 
    The first factor is 9 because a number cannot start with 0.
    n(k)
    */

    private static final int[] result = new int[11];

    static {
        for (int i = 0; i < 11; result[i] = countNumbersWithUniqueDigits(i++));
    }

    @Test
    public void test() {
        Assert.assertEquals(1, countNumbersWithUniqueDigitsII(0));
        Assert.assertEquals(10, countNumbersWithUniqueDigitsII(1));
        Assert.assertEquals(91, countNumbersWithUniqueDigitsII(2));
    }

    public int countNumbersWithUniqueDigitsII(int n) {
        return result[n % 11];
    }

    public static int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        int result = 10, cur = 9;
        for (int v = 9; n-- > 1 && v > 0; result += cur *= v--);
        return result;
    }
}
