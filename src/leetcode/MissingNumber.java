/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月2日<p>
//-------------------------------------------------------
public class MissingNumber {

    public int missingNumber(int[] nums) {
        int check = 0;
        for (int i = 0; i < nums.length; i++)
            check ^= nums[i] ^ i + 1;
        return check;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, missingNumber(new int[] { 0, 1, 3 }));
    }
}
