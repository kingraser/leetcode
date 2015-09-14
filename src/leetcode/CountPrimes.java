/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.BitSet;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class CountPrimes {

    //Count the number of prime numbers less than a non-negative number, n.
    public int countPrimes(int n) {
        BitSet bs = new BitSet(n);
        bs.set(0);
        bs.set(1);
        int ind = 0, count = 0;
        while (ind < n) {
            ind = bs.nextClearBit(ind + 1);
            if (ind >= n) return count;
            count++;
            for (int i = 2 * ind; i < n; bs.set(i), i += ind);
        }
        return count;
    }

    @Test
    public void test() {
        int expected = 4, actual = countPrimes(10);
        Assert.assertEquals(expected, actual);
    }

}
