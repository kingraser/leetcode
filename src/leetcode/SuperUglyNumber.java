/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年3月3日;
//-------------------------------------------------------
public class SuperUglyNumber {

    /*
    Write a program to find the nth super ugly number.    
    Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. 
    For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence 
    of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.
    
    Note:
    (1) 1 is a super ugly number for any given primes.
    (2) The given numbers in primes are in ascending order.
    (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000. 
    */

    @Test
    public void test() {
        Assert.assertEquals(32, nthSuperUglyNumber(12, new int[] { 2, 7, 13, 19 }));
    }

    public static int nthSuperUglyNumber(int n, int[] primes) {
        int[] result = new int[n], indexes = new int[primes.length];
        result[0] = 1;
        Arrays.fill(result, 1, result.length, Integer.MAX_VALUE);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < primes.length; j++)
                result[i] = Math.min(result[i], primes[j] * result[indexes[j]]);
            for (int j = 0; j < primes.length; j++)
                if (result[i] % primes[j] == 0) indexes[j]++;
        }
        return result[n - 1];
    }

}
