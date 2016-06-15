/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Sets;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class HappyNumber {

    /*
    Example: 19 is a happy number
    
    pow(1,2) + pow(9,2) = 82
    pow(8,2) + pow(2,2) = 68
    pow(6,2) + pow(8,2) = 100
    pow(1,2) + pow(0,2) + pow(0,2) = 1
    */

    public boolean isHappy(int n) {
        Set<Integer> set = Sets.newHashSet(n);
        for (int result = 0; n != 1; n = result, result = 0) {
            for (int radix; n != 0; result += (radix = n % 10) * radix, n /= 10);
            if (!set.add(result)) return false;
        }
        return true;
    }

    @Test
    public void test() {
        Assert.assertTrue(isHappy(19));
    }
}
