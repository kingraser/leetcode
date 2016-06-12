/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年3月7日;
//-------------------------------------------------------
public class AdditiveNumber {

    /*
    Additive number is a string whose digits can form additive sequence.    
    A valid additive sequence should contain at least three numbers. 
    Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
    
    For example:
    "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
    
    1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
    
    "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
    
    1 + 99 = 100, 99 + 100 = 199
    
    Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.    
    Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
    */

    @Test
    public void test() {
        Assert.assertTrue(isAdditiveNumber("112358"));
        Assert.assertTrue(isAdditiveNumber("199100199"));
        Assert.assertFalse(isAdditiveNumber("1203"));
        Assert.assertFalse(isAdditiveNumber("1023"));
    }

    public boolean isAdditiveNumber(String num) {
        if (num.charAt(0) == '0') return isValid(BigInteger.ZERO, 1, num);
        for (int i = 1, half = (num.length() >> 1); i <= half; i++) 
            if(isValid(new BigInteger(num.substring(0, i)), i, num)) return true;
        return false;
    }

    private boolean isValid(BigInteger a, int start, String num) {
        if (num.charAt(start) == '0') return isValid(a, BigInteger.ZERO, start + 1, num);
        for (int i = 1; Math.max(start, i) <= num.length() - start - i; i++)
            if (isValid(a, new BigInteger(num.substring(start, start + i)), start + i, num)) return true;
        return false;
    }

    private boolean isValid(BigInteger a, BigInteger b, int start, String num) {
        if (start == num.length()) return true;
        BigInteger aPlusb = a.add(b);
        String sum = aPlusb.toString();
        return num.startsWith(sum, start) && isValid(b, aPlusb, start + sum.length(), num);
    }
}
